package com.shallwaystudio.nodevideo;

import android.webkit.JavascriptInterface;
import org.json.JSONArray;
import org.json.JSONException;

/** Runtime bridge for the Node Video IL2CPP scripting layer.
 * The native side resolves exported il2cpp_* functions at runtime instead of
 * embedding version-specific absolute addresses. File mode remains available
 * when the native bridge is not loaded.
 */
public final class NodeVideoScriptBridge {
    private static boolean nativeLoaded;
    static {
        try { System.loadLibrary("nvscriptbridge"); nativeLoaded = true; }
        catch (Throwable ignored) { nativeLoaded = false; }
    }
    private static native boolean nativeAvailable();
    private static native String nativeListClasses();
    private static native String nativeDescribeHandle(long handle);
    private static native String nativeInvoke0(long handle, String method);
    private static native String nativeFindClass(String namespace, String name);
    private static native String nativeInvokeStatic(String namespace, String clazz, String method, String[] args);
    private static native String nativeInvoke(long handle, String method, String[] args);
    private static native String nativeDescribeClass(String namespace, String name);
    private static native boolean nativeRelease(long handle);

    @JavascriptInterface public String getMode() { return nativeLoaded && nativeAvailable() ? "live-runtime" : "file-adapter"; }
    @JavascriptInterface public String getVersion() { return "NodeVideo scripting v7"; }
    @JavascriptInterface public boolean isLiveAvailable() { return nativeLoaded && nativeAvailable(); }
    @JavascriptInterface public String findClass(String namespace, String name) {
        return nativeLoaded ? nativeFindClass(namespace, name) : "ERROR: native bridge unavailable";
    }
    @JavascriptInterface public String invokeStatic0(String namespace, String clazz, String method) {
        return invokeStatic(namespace, clazz, method, new String[0]);
    }
    @JavascriptInterface public String invokeStatic(String namespace, String clazz, String method, String[] args) {
        return nativeLoaded ? nativeInvokeStatic(namespace, clazz, method, args) : "ERROR: native bridge unavailable";
    }
    @JavascriptInterface public String invoke(long handle, String method, String[] args) {
        return nativeLoaded ? nativeInvoke(handle, method, args) : "ERROR: native bridge unavailable";
    }
    @JavascriptInterface public String invokeStaticJson(String namespace, String clazz, String method, String argsJson) {
        return nativeLoaded ? nativeInvokeStatic(namespace, clazz, method, parseArgs(argsJson)) : "ERROR: native bridge unavailable";
    }
    @JavascriptInterface public String invokeJson(long handle, String method, String argsJson) {
        return nativeLoaded ? nativeInvoke(handle, method, parseArgs(argsJson)) : "ERROR: native bridge unavailable";
    }
    private static String[] parseArgs(String json) {
        if (json == null || json.trim().isEmpty() || "null".equals(json.trim())) return new String[0];
        try {
            JSONArray a = new JSONArray(json);
            String[] out = new String[a.length()];
            for (int i = 0; i < a.length(); i++) out[i] = a.optString(i, "");
            return out;
        } catch (JSONException e) {
            return new String[0];
        }
    }
    @JavascriptInterface public String listClasses() {
        return nativeLoaded ? nativeListClasses() : "{\"error\":\"native bridge unavailable\"}";
    }
    @JavascriptInterface public String describeHandle(long handle) {
        return nativeLoaded ? nativeDescribeHandle(handle) : "{\"error\":\"native bridge unavailable\"}";
    }
    @JavascriptInterface public String invoke0(long handle, String method) {
        return nativeLoaded ? nativeInvoke0(handle, method) : "ERROR: native bridge unavailable";
    }
    @JavascriptInterface public String describeClass(String namespace, String name) {
        return nativeLoaded ? nativeDescribeClass(namespace, name) : "{\"error\":\"native bridge unavailable\"}";
    }
    @JavascriptInterface public boolean release(long handle) {
        return nativeLoaded && nativeRelease(handle);
    }
}
