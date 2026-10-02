package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class E1 implements Runnable {
    final /* synthetic */ I1 a;

    E1(I1 i1) {
        this.a = i1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.a.onPause();
    }
}
