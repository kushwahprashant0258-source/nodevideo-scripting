package com.unity3d.player;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
final class E0 implements DialogInterface.OnClickListener {
    final /* synthetic */ UnityPlayer a;

    E0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.finish();
    }
}
