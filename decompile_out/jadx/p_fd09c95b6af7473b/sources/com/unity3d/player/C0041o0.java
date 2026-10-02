package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0041o0 extends R0 {
    final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0041o0(UnityPlayer unityPlayer, boolean z) {
        super(unityPlayer);
        this.b = z;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        UnityAccessibilityDelegate.sendScreenReaderStatusChangedNotification(this.b);
    }
}
