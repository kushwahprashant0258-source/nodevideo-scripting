package com.unity3d.player;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import np.dcc.protect.EntryPoint;

/* JADX INFO: loaded from: classes.dex */
public class UnityPlayerActivity extends Activity implements IUnityPlayerLifecycleEvents, IUnityPermissionRequestSupport, IUnityPlayerSupport {
    protected UnityPlayerForActivityOrService mUnityPlayer;

    static {
        EntryPoint.stub(20);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public native boolean dispatchKeyEvent(KeyEvent keyEvent);

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public native void onConfigurationChanged(Configuration configuration);

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);

    @Override // android.app.Activity
    protected native void onDestroy();

    @Override // android.app.Activity
    public native boolean onGenericMotionEvent(MotionEvent motionEvent);

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public native boolean onKeyDown(int i, KeyEvent keyEvent);

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public native boolean onKeyUp(int i, KeyEvent keyEvent);

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public native void onLowMemory();

    @Override // android.app.Activity
    protected native void onNewIntent(Intent intent);

    @Override // android.app.Activity
    protected native void onPause();

    @Override // android.app.Activity
    public native void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    @Override // android.app.Activity
    protected native void onResume();

    @Override // android.app.Activity
    protected native void onStart();

    @Override // android.app.Activity
    protected native void onStop();

    @Override // android.app.Activity
    public native boolean onTouchEvent(MotionEvent motionEvent);

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public native void onTrimMemory(int i);

    @Override // com.unity3d.player.IUnityPlayerLifecycleEvents
    public native void onUnityPlayerQuitted();

    @Override // com.unity3d.player.IUnityPlayerLifecycleEvents
    public native void onUnityPlayerUnloaded();

    @Override // android.app.Activity, android.view.Window.Callback
    public native void onWindowFocusChanged(boolean z);

    @Override // com.unity3d.player.IUnityPermissionRequestSupport
    public native void requestPermissions(PermissionRequest permissionRequest);

    protected native String updateUnityCommandLineArguments(String str);

    @Override // com.unity3d.player.IUnityPlayerSupport
    public UnityPlayerForActivityOrService getUnityPlayerConnection() {
        return this.mUnityPlayer;
    }
}
