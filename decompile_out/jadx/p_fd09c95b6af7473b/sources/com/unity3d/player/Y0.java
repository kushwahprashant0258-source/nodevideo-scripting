package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class Y0 implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    Y0(UnityPlayerForActivityOrService unityPlayerForActivityOrService, boolean z) {
        this.b = unityPlayerForActivityOrService;
        this.a = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        S s = this.b.mSoftInput;
        if (s != null) {
            s.a(this.a);
        }
    }
}
