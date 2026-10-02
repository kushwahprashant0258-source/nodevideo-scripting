package com.unity3d.player;

import android.os.Build;

/* JADX INFO: renamed from: com.unity3d.player.w0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0057w0 {
    private UnityPlayer a;
    private boolean b;

    public C0057w0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    final void a() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        this.a.runOnUiThread(new RunnableC0055v0(this));
    }

    final void b() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        this.a.runOnUiThread(new RunnableC0053u0(this));
    }
}
