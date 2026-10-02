package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.q0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0045q0 extends R0 {
    final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0045q0(UnityPlayer unityPlayer, boolean z) {
        super(unityPlayer);
        this.b = z;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        UnityAccessibilityDelegate.sendClosedCaptioningChangedNotification(this.b);
    }
}
