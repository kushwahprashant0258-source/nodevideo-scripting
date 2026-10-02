package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class o1 implements Runnable {
    final /* synthetic */ float a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    o1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, float f) {
        this.b = unityPlayerForActivityOrService;
        this.a = f;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0063z0 view = this.b.getView();
        if (view != null) {
            view.a(this.a);
        }
    }
}
