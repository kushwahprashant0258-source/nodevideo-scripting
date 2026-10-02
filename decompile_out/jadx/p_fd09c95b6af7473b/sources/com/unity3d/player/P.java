package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class P implements Runnable {
    final /* synthetic */ S a;

    P(S s) {
        this.a = s;
    }

    @Override // java.lang.Runnable
    public final void run() {
        S s = this.a;
        s.a(s.a(), true);
    }
}
