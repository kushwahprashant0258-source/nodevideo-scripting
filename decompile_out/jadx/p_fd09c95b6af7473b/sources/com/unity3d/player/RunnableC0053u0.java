package com.unity3d.player;

import android.view.View;

/* JADX INFO: renamed from: com.unity3d.player.u0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0053u0 implements Runnable {
    final /* synthetic */ C0057w0 a;

    RunnableC0053u0(C0057w0 c0057w0) {
        this.a = c0057w0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        View view = this.a.a.getView();
        if (this.a.a.getContextType() == EnumC0052u.c) {
            this.a.b = view.isClickable();
            view.setClickable(true);
            view.setOnCapturedPointerListener(new ViewOnCapturedPointerListenerC0051t0(this));
        }
        view.requestPointerCapture();
    }
}
