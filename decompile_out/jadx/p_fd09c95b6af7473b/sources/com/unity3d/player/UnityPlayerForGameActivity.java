package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceView;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class UnityPlayerForGameActivity extends UnityPlayer {
    Thread m_MainThread;
    private J m_PersistentUnitySurface;
    SurfaceView m_SurfaceView;

    public UnityPlayerForGameActivity(Activity activity, FrameLayout frameLayout, SurfaceView surfaceView) {
        this(activity, frameLayout, surfaceView, null);
    }

    public UnityPlayerForGameActivity(Activity activity, FrameLayout frameLayout, SurfaceView surfaceView, IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents) {
        super(activity, EnumC0052u.c, iUnityPlayerLifecycleEvents);
        this.m_SurfaceView = surfaceView;
        initialize(frameLayout);
        this.m_MainThread = null;
        nativeUnityPlayerForGameActivityInitialized(!w1.d() ? 1 : 0);
        this.m_PersistentUnitySurface = new J(activity);
        this.m_SurfaceView.getHolder().addCallback(new r1(this));
    }

    public static int getUnityViewIdentifier(Context context) {
        return context.getResources().getIdentifier("unitySurfaceView", "id", context.getPackageName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeOrientationChanged(int i, int i2);

    private final native void nativeUnityPlayerForGameActivityInitialized(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeUnityPlayerSetRunning(boolean z);

    @Override // com.unity3d.player.UnityPlayer
    protected void cleanupResourcesForDestroy() {
    }

    @Override // com.unity3d.player.UnityPlayer
    public SurfaceView getSurfaceView() {
        return this.m_SurfaceView;
    }

    @Override // com.unity3d.player.UnityPlayer
    public SurfaceView getView() {
        return getSurfaceView();
    }

    @Override // com.unity3d.player.UnityPlayer
    boolean handleFocus(boolean z) {
        return true;
    }

    @Override // com.unity3d.player.UnityPlayer
    void hidePreservedContent() {
        runOnUiThread(new v1(this));
    }

    @Override // com.unity3d.player.UnityPlayer
    void onOrientationChanged(int i, int i2) {
        invokeOnMainThread((Runnable) new s1(this, i, i2));
    }

    @Override // com.unity3d.player.UnityPlayer
    void pauseUnity() {
        super.pauseUnity();
        invokeOnMainThread((Runnable) new t1(this));
    }

    @Override // com.unity3d.player.UnityPlayer
    void resumeUnity() {
        super.resumeUnity();
        invokeOnMainThread((Runnable) new u1(this));
    }

    @Override // com.unity3d.player.UnityPlayer
    public boolean runningOnMainThread() {
        if (this.m_MainThread != null) {
            return Thread.currentThread() == this.m_MainThread;
        }
        throw new RuntimeException("Main Thread was not yet set.");
    }

    @Override // com.unity3d.player.UnityPlayer
    public void setMainSurfaceViewAspectRatio(float f) {
        AbstractC0060y.Log(6, "setMainSurfaceViewAspectRatio is not supported for GameActivity");
    }

    protected void setMainThread() {
        if (this.m_MainThread != null) {
            throw new RuntimeException("Main Thread was already set.");
        }
        this.m_MainThread = Thread.currentThread();
    }
}
