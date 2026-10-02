package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class I0 implements Runnable {
    final /* synthetic */ String a;

    I0(String str) {
        this.a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityPlayer.nativeSetLaunchURL(this.a);
    }
}
