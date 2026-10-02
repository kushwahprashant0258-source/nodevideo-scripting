package com.unity3d.player;

import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
final class W0 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    W0(UnityPlayerForActivityOrService unityPlayerForActivityOrService, String str) {
        this.b = unityPlayerForActivityOrService;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        EditText editText;
        S s = this.b.mSoftInput;
        if (s == null || (str = this.a) == null || (editText = s.c) == null) {
            return;
        }
        editText.setText(str);
        s.c.setSelection(str.length());
    }
}
