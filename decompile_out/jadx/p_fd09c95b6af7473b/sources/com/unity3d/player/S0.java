package com.unity3d.player;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
final class S0 extends R0 {
    final /* synthetic */ WindowInsets b;
    final /* synthetic */ ViewOnApplyWindowInsetsListenerC0019f1 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    S0(ViewOnApplyWindowInsetsListenerC0019f1 viewOnApplyWindowInsetsListenerC0019f1, WindowInsets windowInsets) {
        super(viewOnApplyWindowInsetsListenerC0019f1.a);
        this.c = viewOnApplyWindowInsetsListenerC0019f1;
        this.b = windowInsets;
    }

    @Override // com.unity3d.player.R0
    public final void a() {
        this.c.a.nativeOnApplyWindowInsets(this.b);
    }
}
