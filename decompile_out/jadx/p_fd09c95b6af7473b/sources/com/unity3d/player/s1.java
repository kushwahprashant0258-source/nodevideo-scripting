package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class s1 extends R0 {
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ UnityPlayerForGameActivity d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    s1(UnityPlayerForGameActivity unityPlayerForGameActivity, int i, int i2) {
        super(unityPlayerForGameActivity);
        this.d = unityPlayerForGameActivity;
        this.b = i;
        this.c = i2;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        this.d.nativeOrientationChanged(this.b, this.c);
    }
}
