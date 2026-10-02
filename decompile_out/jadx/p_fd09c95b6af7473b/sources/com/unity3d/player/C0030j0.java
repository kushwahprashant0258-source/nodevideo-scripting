package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0030j0 extends R0 {
    final /* synthetic */ int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0030j0(UnityPlayer unityPlayer, int i) {
        super(unityPlayer);
        this.b = i;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        UnityAccessibilityDelegate.onNodeFocusChanged(this.b, false);
    }
}
