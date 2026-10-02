# Node Video scripting v4 — live project bridge

This stage extends v3 with a live object facade over verified Node Video IL2CPP classes found in the 7.7.1 metadata.

Verified path: `NodeVideo.App.get_Instance()` -> `NodeVideo.App.get_Root()` -> `NodeVideo.Logic.Node` methods including `GetAllChildren`, `GetProp`, `GetEffect`, `GetAllParams`, `GetKeyFramesRecursive`; parameter classes expose `Set`, `AddKeyFrame`, `GetKeyFrames`, and related methods.

The native invocation path now passes reference-type arguments correctly to `il2cpp_runtime_invoke`. The JS API exposes `app.project.liveRoot`, `app.project.live.children`, `app.project.live.prop`, `app.project.live.effect`, plus generic `app.live.callStatic()` / `app.live.call()`.

Important: the exact argument signatures for every Node Video method still need runtime verification. The bridge deliberately does not hard-code native addresses. A rebuilt APK with the native library loaded is required to exercise the live path.
