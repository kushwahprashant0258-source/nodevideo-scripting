package com.unity3d.player;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
final class X implements DialogInterface.OnDismissListener {
    final /* synthetic */ C0006b0 a;

    X(C0006b0 c0006b0) {
        this.a = c0006b0;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.a.invokeOnClose();
    }
}
