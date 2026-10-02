# Node Video 7.7.1 scripting v6

This stage continues v5.

## Added
- Runtime `Assembly-CSharp` class enumeration.
- Runtime handle description.
- Zero-argument instance invocation.
- JS `app.live` object adapter.
- `app.project` live wrapper using the previously mapped `NodeVideo.App.get_Instance()` -> `get_Root()` path.
- `LiveObject.call()`, `call0()`, `describe()`, and `release()`.
- Runtime discovery remains address-free; no guessed native addresses are embedded.

## Important
This is source-level runtime integration. A rebuild must compile `decompile_out/native/nvscriptbridge/nvscriptbridge.cpp` into `libnvscriptbridge.so` and package it into the APK. The decompile archive itself is not a complete Gradle/NDK project.

## Example

```js
const root = app.project?.root;
if (root) {
  log(root.describe());
  const children = root.children();
  log(JSON.stringify(children));
}
```

The wrapper returns `LiveObject` handles for managed objects and exposes explicit `release()` so scripts can avoid retaining native handles unnecessarily.
