package com.unity3d.player;

import android.view.SurfaceHolder;
import android.widget.FrameLayout;

/* JADX INFO: renamed from: com.unity3d.player.y0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class SurfaceHolderCallbackC0061y0 implements SurfaceHolder.Callback {
    final /* synthetic */ C0063z0 a;

    SurfaceHolderCallbackC0061y0(C0063z0 c0063z0) {
        this.a = c0063z0;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.a.b.updateGLDisplay(0, surfaceHolder.getSurface());
        this.a.b.sendSurfaceChangedEvent();
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.a.b.updateGLDisplay(0, surfaceHolder.getSurface());
        C0063z0 c0063z0 = this.a;
        J j = c0063z0.c;
        FrameLayout frameLayout = c0063z0.b.getFrameLayout();
        I i = j.b;
        if (i == null || i.getParent() != null) {
            return;
        }
        frameLayout.addView(j.b);
        frameLayout.bringChildToFront(j.b);
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        C0063z0 c0063z0 = this.a;
        J j = c0063z0.c;
        C0008c c0008c = c0063z0.a;
        j.getClass();
        if (PlatformSupport.NOUGAT_SUPPORT && j.a != null) {
            if (j.b == null) {
                j.b = new I(j, j.a);
            }
            j.b.a(c0008c);
        }
        this.a.b.updateGLDisplay(0, null);
    }
}
