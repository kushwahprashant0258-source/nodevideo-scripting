package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0035l0 extends R0 {
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ C0039n0 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0035l0(C0039n0 c0039n0, UnityPlayer unityPlayer, int i, int i2) {
        super(unityPlayer);
        this.d = c0039n0;
        this.b = i;
        this.c = i2;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        if (this.b == 4096) {
            UnityAccessibilityDelegate.onNodeIncremented(this.c);
        } else {
            UnityAccessibilityDelegate.onNodeDecremented(this.c);
        }
        this.d.a.sendEventForVirtualViewId(this.c, 4);
    }
}
