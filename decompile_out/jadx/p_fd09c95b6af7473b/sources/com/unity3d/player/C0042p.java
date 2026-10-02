package com.unity3d.player;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;

/* JADX INFO: renamed from: com.unity3d.player.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0042p extends CameraCaptureSession.StateCallback {
    final /* synthetic */ C0050t a;

    C0042p(C0050t c0050t) {
        this.a = c0050t;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        AbstractC0060y.Log(6, "Camera2: CaptureSession configuration failed.");
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
        StringBuilder sbAppend;
        C0050t c0050t = this.a;
        if (c0050t.b == null) {
            return;
        }
        synchronized (c0050t.s) {
            C0050t c0050t2 = this.a;
            c0050t2.r = cameraCaptureSession;
            try {
                try {
                    c0050t2.q = c0050t2.b.createCaptureRequest(1);
                    C0050t c0050t3 = this.a;
                    c0050t3.q.addTarget(c0050t3.v);
                    C0050t c0050t4 = this.a;
                    c0050t4.q.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, c0050t4.n);
                    this.a.g();
                } catch (IllegalStateException e) {
                    sbAppend = new StringBuilder("Camera2: IllegalStateException ").append(e);
                    AbstractC0060y.Log(6, sbAppend.toString());
                }
            } catch (CameraAccessException e2) {
                sbAppend = new StringBuilder("Camera2: CameraAccessException ").append(e2);
                AbstractC0060y.Log(6, sbAppend.toString());
            }
        }
    }
}
