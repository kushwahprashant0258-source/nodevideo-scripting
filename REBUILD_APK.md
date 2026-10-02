# Node Video 7.7.1 — reconstructed APK build layer

This revision packages the supplied Unity/IL2CPP decompile into an installable ARM64
APK build using Android build-tools, D8, CMake/NDK, zipalign and apksigner.

## Included

- Original `classes.dex`
- Original Unity/IL2CPP ARM64 native libraries
- Unity `data.unity3d`
- `global-metadata.dat`
- Original Android resources/assets
- Node Video scripting Java classes as a second DEX
- `libnvscriptbridge.so`
- Existing manifest with the scripting activity launcher

## Build

GitHub Actions installs/verifies the required SDK/NDK packages and runs:

    gradle buildRelease

Output:

    build/apk/NodeVideo-Scripting-v7.7.1.apk

The APK uses a locally generated test keystore.

## Runtime caveat

An APK build succeeding proves packaging/signing only. The IL2CPP scripting bridge and
the reconstructed launcher must still be tested on an ARM64 Android device/emulator.
