# Node Video 7.7.1 scripting v7

This archive contains the v7 source patch and native bridge. The original decompile is not a complete Gradle project, so an APK cannot be honestly produced from this archive alone.

## Native bridge

`native/nvscriptbridge/` contains a CMake Android shared-library target named `nvscriptbridge`. Build it for `arm64-v8a` with Android NDK and package the resulting `libnvscriptbridge.so` under:

`lib/arm64-v8a/`

The existing app already contains the original `libil2cpp.so` in that ABI.

## Runtime verification

The bridge dynamically resolves exported `il2cpp_*` functions from the loaded `libil2cpp.so`; it does not embed guessed absolute addresses.

The scripting editor now exposes `scriptingSelfTest()` to report:
- bridge mode
- native availability
- whether the live root can be resolved
- runtime description errors

## Important boundary

Source-level completion is not the same as runtime completion. The exact live object methods must be tested inside the rebuilt 7.7.1 process. Any method signature that differs at runtime must be adjusted from the runtime `describeClass()` output rather than guessed.
