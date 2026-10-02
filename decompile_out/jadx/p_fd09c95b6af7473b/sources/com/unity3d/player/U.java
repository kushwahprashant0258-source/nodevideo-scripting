package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class U implements Runnable {
    final /* synthetic */ W a;

    U(W w) {
        this.a = w;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.c.requestFocus();
        this.a.g();
    }
}
