package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class t1 extends R0 {
    final /* synthetic */ UnityPlayerForGameActivity b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    t1(UnityPlayerForGameActivity unityPlayerForGameActivity) {
        super(unityPlayerForGameActivity);
        this.b = unityPlayerForGameActivity;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        this.b.nativeUnityPlayerSetRunning(false);
    }
}
