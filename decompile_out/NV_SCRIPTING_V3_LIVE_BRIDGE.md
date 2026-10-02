# Node Video scripting v3 — live IL2CPP bridge

This revision replaces the previous placeholder bridge with a runtime-capable JNI/IL2CPP adapter.

## What is now implemented
- Loads `nvscriptbridge` when present.
- Resolves exported `il2cpp_*` runtime functions from `libil2cpp.so` dynamically (no hard-coded ASLR addresses).
- Finds classes in `Assembly-CSharp` by namespace/name.
- Invokes static methods by method name and argument count.
- Invokes instance methods through opaque runtime handles.
- Supports primitive/string argument boxing for `int`, `float`, `double`, `bool`, `string`, and existing object handles.
- Returns primitive results or opaque handles for reference objects.
- JavaScript exposes this through `app.live`.
- File-adapter scripting remains available as a fallback.

## Important build/runtime requirement
The decompiled archive does not contain the original Android Gradle/NDK build system. The native bridge source therefore lives under:
`native/nvscriptbridge/`

A rebuild must compile it as `libnvscriptbridge.so` for `arm64-v8a` and package it in the APK. The existing `libil2cpp.so` already exports the runtime functions required by the bridge.

## Current live API
```js
app.live.available
app.live.findClass('NodeVideo.UI', 'UIProjectManager')
app.live.invokeStatic('NodeVideo.UI', 'UIProjectManager', 'get_Instance', [])
app.live.invoke(handle, 'get_Current', [])
```

The bridge intentionally does not invent a current-project pointer or hard-code method addresses. The remaining runtime-specific task is resolving the exact project-manager/property object chain on a running 7.7.1 instance and then exposing friendly AE-style wrappers over those verified objects.


## v4 update
Verified NodeVideo.App -> Root -> Node traversal was added, with live project helpers and corrected reference-argument marshalling.
