# Node Video scripting v5 — live bridge hardening

This stage continues v4 rather than replacing it.

## Added
- Runtime IL2CPP method enumeration through `il2cpp_class_get_methods`.
- Runtime method parameter/return type discovery where the Unity runtime exports the corresponding metadata APIs.
- GC-safe handles using `il2cpp_gchandle_new/get_target/free` instead of retaining raw managed pointers.
- JavaScript `live.describeClass(namespace, className)`.
- JavaScript `live.release(handle)`.
- Safer detection of the injected `NodeVideoNative` interface.
- Live diagnostics: `inspectLiveClass()` and `inspectLiveRoot()`.
- Version updated to `7.7.1-script-v5`.

## What this accomplishes
The script engine can now discover the actual methods and runtime signatures exposed by the running IL2CPP build before calling them. This removes a major dependency on guessed method signatures.

## Remaining verification
The native library still must be compiled and loaded into a rebuilt APK. The live calls themselves must then be exercised against the real running Node Video 7.7.1 process. No guessed native addresses are used.
