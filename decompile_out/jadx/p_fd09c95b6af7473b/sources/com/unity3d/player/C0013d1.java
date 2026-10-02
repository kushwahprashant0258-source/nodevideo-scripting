package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.d1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0013d1 extends R0 {
    final /* synthetic */ boolean b;
    final /* synthetic */ UnityPlayerForActivityOrService c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0013d1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, boolean z) {
        super(unityPlayerForActivityOrService);
        this.c = unityPlayerForActivityOrService;
        this.b = z;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        this.c.nativeSetKeyboardIsVisible(this.b);
    }
}
