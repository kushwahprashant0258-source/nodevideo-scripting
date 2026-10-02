package com.unity3d.player;

import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
final class Y implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ C0006b0 a;

    Y(C0006b0 c0006b0) {
        this.a = c0006b0;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        this.a.reportSoftInputArea();
        this.a.i.b();
    }
}
