package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class x1 implements Runnable {
    final /* synthetic */ A1 a;

    x1(A1 a1) {
        this.a = a1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.destroyPlayer();
        this.a.a(3);
    }
}
