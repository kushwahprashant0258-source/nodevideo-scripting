package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.view.Surface;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class Camera2Wrapper implements com.unity3d.player.a.b {
    private Context a;
    private C0050t b = null;

    public Camera2Wrapper(Context context) {
        this.a = context;
        initCamera2Jni();
    }

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i, int i2, int i3);

    private final native void nativeSurfaceTextureReady(Object obj);

    public final void a(Object obj) {
        nativeSurfaceTextureReady(obj);
    }

    public final void a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i, int i2, int i3) {
        nativeFrameReady(byteBuffer, byteBuffer2, byteBuffer3, i, i2, i3);
    }

    protected void closeCamera2() {
        C0050t c0050t = this.b;
        if (c0050t != null) {
            c0050t.a();
        }
        this.b = null;
    }

    protected int getCamera2Count() {
        return C0050t.a(this.a);
    }

    protected int getCamera2FocalLengthEquivalent(int i) {
        return C0050t.a(this.a, i);
    }

    protected int[] getCamera2Resolutions(int i) {
        return C0050t.b(this.a, i);
    }

    protected int getCamera2SensorOrientation(int i) {
        return C0050t.c(this.a, i);
    }

    protected Rect getFrameSizeCamera2() {
        C0050t c0050t = this.b;
        return c0050t != null ? c0050t.c() : new Rect();
    }

    protected boolean initializeCamera2(int i, int i2, int i3, int i4, int i5, Surface surface) {
        if (this.b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        C0050t c0050t = new C0050t(this);
        this.b = c0050t;
        return c0050t.a(this.a, i, i2, i3, i4, i5, surface);
    }

    protected boolean isCamera2AutoFocusPointSupported(int i) {
        return C0050t.d(this.a, i);
    }

    protected boolean isCamera2FrontFacing(int i) {
        return C0050t.e(this.a, i);
    }

    protected void pauseCamera2() {
        C0050t c0050t = this.b;
        if (c0050t != null) {
            c0050t.d();
        }
    }

    protected boolean setAutoFocusPoint(float f, float f2) {
        C0050t c0050t = this.b;
        if (c0050t != null) {
            return c0050t.a(f, f2);
        }
        return false;
    }

    protected void startCamera2() {
        C0050t c0050t = this.b;
        if (c0050t != null) {
            c0050t.h();
        }
    }

    protected void stopCamera2() {
        C0050t c0050t = this.b;
        if (c0050t != null) {
            c0050t.i();
        }
    }
}
