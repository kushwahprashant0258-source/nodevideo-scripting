// Node Video 7.7.1 scripting bridge - Stage 2 native scaffold.
// This file intentionally contains no guessed function addresses. Runtime mapping must be
// generated from the supplied global-metadata.dat + libil2cpp.so before enabling live calls.
#include <jni.h>
#include <android/log.h>
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, "NVScript", __VA_ARGS__)
extern "C" JNIEXPORT jstring JNICALL Java_com_shallwaystudio_nodevideo_NodeVideoNative_getBridgeInfo(JNIEnv* env, jclass) {
    return env->NewStringUTF("NodeVideo IL2CPP bridge scaffold; live mapping not enabled");
}
