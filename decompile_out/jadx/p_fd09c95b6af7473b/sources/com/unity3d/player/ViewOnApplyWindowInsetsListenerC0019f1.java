package com.unity3d.player;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: renamed from: com.unity3d.player.f1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class ViewOnApplyWindowInsetsListenerC0019f1 implements View.OnApplyWindowInsetsListener {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    ViewOnApplyWindowInsetsListenerC0019f1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.a.invokeOnMainThread((Runnable) new S0(this, windowInsets));
        return windowInsets;
    }
}
