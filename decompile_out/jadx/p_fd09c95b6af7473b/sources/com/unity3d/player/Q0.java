package com.unity3d.player;

import android.telephony.PhoneStateListener;

/* JADX INFO: loaded from: classes.dex */
final class Q0 extends PhoneStateListener {
    final /* synthetic */ UnityPlayer a;

    private Q0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    @Override // android.telephony.PhoneStateListener
    public final void onCallStateChanged(int i, String str) {
        this.a.nativeMuteMasterAudio(i == 1);
    }
}
