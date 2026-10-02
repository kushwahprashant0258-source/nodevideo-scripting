package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class u1 extends R0 {
    final /* synthetic */ UnityPlayerForGameActivity b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    u1(UnityPlayerForGameActivity unityPlayerForGameActivity) {
        super(unityPlayerForGameActivity);
        this.b = unityPlayerForGameActivity;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        this.b.nativeUnityPlayerSetRunning(true);
    }
}
