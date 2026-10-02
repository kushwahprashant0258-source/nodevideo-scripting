# nvscriptbridge

Android ARM64 JNI bridge for the Node Video scripting layer.

Build with an Android NDK toolchain and CMake. The bridge intentionally resolves IL2CPP exports at runtime because this decompile does not provide a stable generated C++ API surface.

Before invoking live methods, call `nativeDescribeClass(namespace, className)` and use its reported parameter/return types to select the correct wrapper.
