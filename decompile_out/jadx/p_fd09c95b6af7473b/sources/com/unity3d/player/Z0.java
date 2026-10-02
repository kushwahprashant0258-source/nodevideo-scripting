package com.unity3d.player;

import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
final class Z0 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ UnityPlayerForActivityOrService c;

    Z0(UnityPlayerForActivityOrService unityPlayerForActivityOrService, int i, int i2) {
        this.c = unityPlayerForActivityOrService;
        this.a = i;
        this.b = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        S s = this.c.mSoftInput;
        if (s != null) {
            int i2 = this.a;
            int i3 = this.b;
            EditText editText = s.c;
            if (editText == null || editText.getText().length() < (i = i3 + i2)) {
                return;
            }
            s.c.setSelection(i2, i);
        }
    }
}
