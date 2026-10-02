package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.e1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0016e1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    RunnableC0016e1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.destroy();
    }
}
