# Node Video Scripting v1

This decompile patch adds an AE-style scripting entry point around the existing Unity/IL2CPP app.

## What works in this v1
- Script button over the Unity activity.
- JavaScript editor running inside Android WebView.
- Load a Node Video `.nv` project as JSON.
- Search properties by `Name`.
- Search objects by `$type`.
- Modify `Value` fields with JavaScript.
- Inspect layers/objects recursively.
- Export a new `.nv` project.

## Important limitation
Node Video 7.7.1 is an IL2CPP build. The original editor's live in-memory Project/Layer objects are compiled into `libil2cpp.so`; this patch therefore does NOT yet inject a live `app.project` API into the Unity runtime.

The next stage is the native bridge: map the IL2CPP project/property methods and replace the file-based adapter with a live adapter. That is what will make scripts behave like After Effects scripts against the open composition.
