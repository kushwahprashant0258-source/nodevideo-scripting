#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
RESROOT="$ROOT/decompile_out/jadx/p_fd09c95b6af7473b/resources"
SRCDIR="$ROOT/decompile_out/jadx/p_fd09c95b6af7473b/sources"
NATIVE="$ROOT/decompile_out/native/nvscriptbridge"

ANDROID_HOME="${ANDROID_HOME:?ANDROID_HOME is not set}"
export ANDROID_SDK_ROOT="$ANDROID_HOME"
BT="$ANDROID_HOME/build-tools/35.0.0"
PLATFORM="$ANDROID_HOME/platforms/android-35/android.jar"
NDK="$ANDROID_HOME/ndk/27.2.12479018"

OUT="$ROOT/build/apk-work"
FINAL="$ROOT/build/apk"
rm -rf "$OUT" "$FINAL"
mkdir -p "$OUT" "$FINAL" "$OUT/classes" "$OUT/stub" "$OUT/dex" "$OUT/cmake"

echo "[1/8] Checking source archive..."
test -f "$RESROOT/AndroidManifest.xml"
test -f "$RESROOT/classes.dex"
test -f "$RESROOT/lib/arm64-v8a/libil2cpp.so"
test -f "$RESROOT/lib/arm64-v8a/libunity.so"
test -f "$RESROOT/assets/bin/Data/data.unity3d"
test -f "$RESROOT/assets/bin/Data/Managed/Metadata/global-metadata.dat"
test -f "$ROOT/decompile_out/native/nvscriptbridge/nvscriptbridge.cpp"

echo "[2/8] Generating stable Android resource IDs..."
python3 "$ROOT/scripts/make_stable_ids.py" "$SRCDIR" "$OUT/stable_ids.txt"

echo "[3/8] Compiling and linking Android resources..."
"$BT/aapt2" compile --dir "$RESROOT/res" -o "$OUT/compiled-res.zip"

"$BT/aapt2" link \
  -o "$OUT/base.apk" \
  -I "$PLATFORM" \
  --manifest "$RESROOT/AndroidManifest.xml" \
  --auto-add-overlay \
  --stable-ids "$OUT/stable_ids.txt" \
  --min-sdk-version 24 \
  --target-sdk-version 34 \
  --version-code 622 \
  --version-name 7.7.1 \
  -A "$RESROOT/assets" \
  "$OUT/compiled-res.zip"

echo "[4/8] Compiling the v7 scripting Java bridge..."
cat > "$OUT/UnityPlayerActivity.java" <<'EOF'
package com.unity3d.player;
import android.app.Activity;
public class UnityPlayerActivity extends Activity {
}
EOF

javac -source 8 -target 8 \
  -cp "$PLATFORM" \
  -d "$OUT/stub" \
  "$OUT/UnityPlayerActivity.java"

javac -source 8 -target 8 \
  -cp "$PLATFORM:$OUT/stub" \
  -d "$OUT/classes" \
  "$SRCDIR/com/shallwaystudio/nodevideo/NodeVideoScriptActivity.java" \
  "$SRCDIR/com/shallwaystudio/nodevideo/NodeVideoScriptEditorActivity.java" \
  "$SRCDIR/com/shallwaystudio/nodevideo/NodeVideoScriptBridge.java"

echo "[5/8] Converting scripting classes to classes2.dex..."
"$BT/d8" \
  --min-api 24 \
  --lib "$PLATFORM" \
  --output "$OUT/dex" \
  $(find "$OUT/classes" -name '*.class' -print)

mv "$OUT/dex/classes.dex" "$OUT/dex/classes2.dex"

echo "[6/8] Building the native IL2CPP scripting bridge..."
cmake -S "$NATIVE" -B "$OUT/cmake" \
  -G Ninja \
  -DANDROID_ABI=arm64-v8a \
  -DANDROID_PLATFORM=android-24 \
  -DCMAKE_TOOLCHAIN_FILE="$NDK/build/cmake/android.toolchain.cmake" \
  -DCMAKE_BUILD_TYPE=Release

cmake --build "$OUT/cmake" --target nvscriptbridge --parallel 2

mkdir -p "$OUT/native/arm64-v8a"
BRIDGE_SO="$(find "$OUT/cmake" -type f -name "libnvscriptbridge.so" -print -quit)"
test -n "$BRIDGE_SO"
cp "$BRIDGE_SO" "$OUT/native/arm64-v8a/"

echo "[7/8] Injecting original Unity/Node Video runtime and v7 bridge..."
cp "$RESROOT/classes.dex" "$OUT/classes.dex"

(
  cd "$OUT"
  zip -q -j base.apk classes.dex dex/classes2.dex
)

# Stage every original arm64-v8a library plus the newly built scripting bridge.
mkdir -p "$OUT/all-libs/arm64-v8a"
cp "$RESROOT"/lib/arm64-v8a/*.so "$OUT/all-libs/arm64-v8a/"
cp "$OUT/native/arm64-v8a/libnvscriptbridge.so" "$OUT/all-libs/arm64-v8a/"

# Put native libraries at the Android-required lib/arm64-v8a/ path.
python3 "$ROOT/scripts/zip_native.py"   "$OUT/base.apk"   "$OUT/all-libs/arm64-v8a"   "$OUT/base_with_libs.apk"
mv "$OUT/base_with_libs.apk" "$OUT/base.apk"

echo "[8/8] Aligning and signing APK..."
mkdir -p "$FINAL"
"$BT/zipalign" -f -p 4 "$OUT/base.apk" "$OUT/aligned.apk"

KEY="$OUT/nodevideo-debug.keystore"
if [ ! -f "$KEY" ]; then
  keytool -genkeypair -v \
    -keystore "$KEY" \
    -storepass android \
    -keypass android \
    -alias nodevideo \
    -keyalg RSA \
    -keysize 2048 \
    -validity 10000 \
    -dname "CN=NodeVideo Scripting,O=Local Build,C=IN"
fi

"$BT/apksigner" sign \
  --ks "$KEY" \
  --ks-pass pass:android \
  --key-pass pass:android \
  --out "$FINAL/NodeVideo-Scripting-v7.7.1.apk" \
  "$OUT/aligned.apk"

"$BT/apksigner" verify --verbose "$FINAL/NodeVideo-Scripting-v7.7.1.apk"

echo
echo "BUILD SUCCESSFUL"
echo "APK: $FINAL/NodeVideo-Scripting-v7.7.1.apk"
