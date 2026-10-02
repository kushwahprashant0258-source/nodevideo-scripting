package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class F1 implements Runnable {
    final /* synthetic */ I1 a;

    F1(I1 i1) {
        this.a = i1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        I1 i1 = this.a;
        A1 a1 = i1.f;
        if (a1 != null) {
            i1.a.addViewToPlayer(a1, true);
            I1 i2 = this.a;
            i2.i = true;
            i2.f.requestFocus();
        }
    }
}
