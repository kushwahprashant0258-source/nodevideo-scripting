package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class n1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    n1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0063z0 view = this.a.getView();
        if (view != null) {
            view.c();
        }
    }
}
