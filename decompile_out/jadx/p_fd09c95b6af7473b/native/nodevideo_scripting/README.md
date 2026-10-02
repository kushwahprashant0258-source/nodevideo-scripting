# Node Video scripting bridge — Stage 2

The supplied IL2CPP metadata contains real NodeVideo namespaces and scripting-related symbols including `GetProperty`, `SetProperty`, `AddKeyFrame`, `ForceAddKeyFrame`, `DeleteKeyFrames`, `GetKeyFrames`, `PKeyFrame`, and `KeyFrame`.

The JavaScript editor now has an AE-style adapter surface (`app.project`, property search, value changes, keyframes, undo/redo) while remaining file-safe.

A live bridge must not use guessed addresses. The next binary-analysis step is to resolve the metadata method/type records against `libil2cpp.so`, then expose only verified methods through JNI/native code.
