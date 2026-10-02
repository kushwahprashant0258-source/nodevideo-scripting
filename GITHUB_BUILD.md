# Node Video 7.7.1 — Scripting Build Package

This package contains the v7 scripting/decompilation work plus a GitHub Actions
build workflow.

## Important

The supplied v7 archive is a decompiled IL2CPP application, not a complete
Android Studio/Gradle source project. Therefore the included GitHub workflow
**does not magically convert the decompile into an APK**. It first checks for
`gradlew`; if no rebuildable Gradle project exists, the workflow stops instead
of producing a misleading or broken APK.

## Intended build pipeline

1. Create/restore the Android Gradle project around the decompiled application.
2. Build the ARM64 native scripting bridge with the Android NDK.
3. Package the existing Node Video resources and IL2CPP library.
4. Build/sign the APK.
5. Install and test the live IL2CPP scripting bridge.

## Tablet workflow

Upload this repository to GitHub, then open:

Actions → Build Node Video Scripting APK → Run workflow.

If the workflow reports that no Gradle project exists, that is the remaining
rebuild-system step; do not rename the ZIP to APK.
