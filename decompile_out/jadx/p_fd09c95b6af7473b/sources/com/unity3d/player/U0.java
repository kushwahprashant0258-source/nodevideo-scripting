package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class U0 implements F {
    final /* synthetic */ V0 a;

    U0(V0 v0) {
        this.a = v0;
    }

    public final void a() {
        V0 v0 = this.a;
        v0.a = true;
        if (v0.b) {
            v0.c.release();
        }
    }
}
