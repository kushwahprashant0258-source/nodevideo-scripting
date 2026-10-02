package com.unity3d.player;

import android.window.OnBackInvokedCallback;

/* JADX INFO: renamed from: com.unity3d.player.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0002a implements OnBackInvokedCallback {
    final /* synthetic */ com.unity3d.player.a.e a;

    C0002a(com.unity3d.player.a.e eVar) {
        this.a = eVar;
    }

    @Override // android.window.OnBackInvokedCallback
    public final void onBackInvoked() {
        Runnable runnable = ((A) this.a).a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
