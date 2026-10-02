package com.unity3d.player;

import android.view.View;

/* JADX INFO: renamed from: com.unity3d.player.v0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0055v0 implements Runnable {
    final /* synthetic */ C0057w0 a;

    RunnableC0055v0(C0057w0 c0057w0) {
        this.a = c0057w0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        View view = this.a.a.getView();
        view.releasePointerCapture();
        if (this.a.a.getContextType() == EnumC0052u.c) {
            view.setOnCapturedPointerListener(null);
            view.setClickable(this.a.b);
        }
    }
}
