package com.unity3d.player;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
final class r1 implements SurfaceHolder.Callback {
    final /* synthetic */ UnityPlayerForGameActivity a;

    r1(UnityPlayerForGameActivity unityPlayerForGameActivity) {
        this.a = unityPlayerForGameActivity;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        UnityPlayerForGameActivity unityPlayerForGameActivity = this.a;
        J j = unityPlayerForGameActivity.m_PersistentUnitySurface;
        FrameLayout frameLayout = unityPlayerForGameActivity.getFrameLayout();
        I i = j.b;
        if (i == null || i.getParent() != null) {
            return;
        }
        frameLayout.addView(j.b);
        frameLayout.bringChildToFront(j.b);
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        UnityPlayerForGameActivity unityPlayerForGameActivity = this.a;
        J j = unityPlayerForGameActivity.m_PersistentUnitySurface;
        SurfaceView surfaceView = unityPlayerForGameActivity.m_SurfaceView;
        j.getClass();
        if (!PlatformSupport.NOUGAT_SUPPORT || j.a == null) {
            return;
        }
        if (j.b == null) {
            j.b = new I(j, j.a);
        }
        j.b.a(surfaceView);
    }
}
