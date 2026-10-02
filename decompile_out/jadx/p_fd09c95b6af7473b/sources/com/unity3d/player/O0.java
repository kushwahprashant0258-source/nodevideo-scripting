package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class O0 extends R0 {
    final /* synthetic */ boolean b;
    final /* synthetic */ P0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    O0(P0 p0, boolean z) {
        super(p0.b);
        this.c = p0;
        this.b = z;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        UnityPlayer.permissionResponseToNative(this.c.a, this.b);
    }
}
