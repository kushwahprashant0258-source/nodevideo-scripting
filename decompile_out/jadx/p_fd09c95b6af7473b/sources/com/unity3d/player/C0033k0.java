package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0033k0 extends R0 {
    final /* synthetic */ int b;
    final /* synthetic */ C0039n0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0033k0(C0039n0 c0039n0, UnityPlayer unityPlayer, int i) {
        super(unityPlayer);
        this.c = c0039n0;
        this.b = i;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        if (UnityAccessibilityDelegate.onNodeSelected(this.b)) {
            this.c.a.sendEventForVirtualViewId(this.b, 1);
        }
    }
}
