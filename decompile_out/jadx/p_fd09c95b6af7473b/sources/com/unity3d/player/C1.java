package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class C1 implements y1 {
    final /* synthetic */ D1 a;

    C1(D1 d1) {
        this.a = d1;
    }

    public final void a(int i) {
        this.a.h.e.lock();
        I1 i1 = this.a.h;
        i1.g = i;
        if (i == 3 && i1.i) {
            i1.runOnUiThread(new B1(this));
        }
        if (i != 0) {
            this.a.h.d.release();
        }
        this.a.h.e.unlock();
    }
}
