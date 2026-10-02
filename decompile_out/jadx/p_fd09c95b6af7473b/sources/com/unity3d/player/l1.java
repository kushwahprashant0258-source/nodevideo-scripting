package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class l1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    l1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.a;
        if (unityPlayerForActivityOrService.mMainDisplayOverride) {
            unityPlayerForActivityOrService.getFrameLayout().removeView(this.a.getView());
        } else if (unityPlayerForActivityOrService.getView().getParent() == null) {
            this.a.getFrameLayout().addView(this.a.getView());
        } else {
            AbstractC0060y.Log(5, "Couldn't add view, because it's already assigned to another parent");
        }
    }
}
