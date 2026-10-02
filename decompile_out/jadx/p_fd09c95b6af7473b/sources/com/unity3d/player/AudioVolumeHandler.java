package com.unity3d.player;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class AudioVolumeHandler implements InterfaceC0036m {
    private C0038n a;

    AudioVolumeHandler(Context context) {
        C0038n c0038n = new C0038n(context);
        this.a = c0038n;
        c0038n.a(this);
    }

    public final void a() {
        this.a.a();
        this.a = null;
    }

    @Override // com.unity3d.player.InterfaceC0036m
    public final native void onAudioVolumeChanged(int i);
}
