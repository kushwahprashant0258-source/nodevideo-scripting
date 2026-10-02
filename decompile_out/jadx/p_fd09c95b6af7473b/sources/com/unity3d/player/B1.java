package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class B1 implements Runnable {
    final /* synthetic */ C1 a;

    B1(C1 c1) {
        this.a = c1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        I1 i1 = this.a.a.h;
        A1 a1 = i1.f;
        if (a1 != null) {
            i1.a.removeViewFromPlayer(a1);
            i1.i = false;
            i1.f.destroyPlayer();
            i1.f = null;
            H1 h1 = i1.c;
            if (h1 != null) {
                ((J0) h1).a();
            }
        }
        this.a.a.h.a.onResume();
    }
}
