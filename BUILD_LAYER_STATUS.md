# Build-layer status

This package contains the reconstructed Android packaging/build layer for the supplied
Node Video 7.7.1 Unity/IL2CPP decompile.

The GitHub workflow now avoids `android-actions/setup-android`, which was the source of
the previous `sdkmanager`/`tools` failure, and uses the Android SDK already provisioned
on the GitHub runner.

The build is an actual packaging attempt, not a Gradle-project claim: it preserves the
original `classes.dex`, Unity/IL2CPP ARM64 libraries and Unity data, adds the scripting
DEX/native bridge, then aligns and signs the resulting APK.

A successful GitHub run is still required to prove that the reconstructed package is
accepted by the Android build tools. Device/runtime testing is required afterward.
