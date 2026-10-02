package com.unity3d.player;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
final class Z implements DialogInterface.OnCancelListener {
    final /* synthetic */ C0006b0 a;

    Z(C0006b0 c0006b0) {
        this.a = c0006b0;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        E e = this.a.g;
        if (e != null) {
            ((p1) e).a();
        }
    }
}
