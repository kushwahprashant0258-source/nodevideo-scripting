package com.unity3d.player;

import android.content.Context;
import android.widget.EditText;

/* JADX INFO: renamed from: com.unity3d.player.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0006b0 extends S {
    T i;

    public C0006b0(Context context, UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        super(context, unityPlayerForActivityOrService);
    }

    @Override // com.unity3d.player.S
    public final void a(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i2, boolean z5, boolean z6) {
        T t = new T(this.a, this.b);
        this.i = t;
        t.a(this, z5, z6);
        this.i.setOnDismissListener(new X(this));
        super.a(str, i, z, z2, z3, z4, str2, i2, z5, z6);
        this.b.getFrameLayout().getViewTreeObserver().addOnGlobalLayoutListener(new Y(this));
        this.c.requestFocus();
        this.i.setOnCancelListener(new Z(this));
    }

    @Override // com.unity3d.player.S
    public final void a(boolean z) {
        this.e = z;
        this.i.a(z);
    }

    @Override // com.unity3d.player.S
    public final void b() {
        this.i.dismiss();
    }

    @Override // com.unity3d.player.S
    protected EditText createEditText(S s) {
        return new C0003a0(this.a, s);
    }

    @Override // com.unity3d.player.S
    public final void f() {
        this.i.show();
    }

    protected void reportSoftInputArea() {
        if (this.i.isShowing()) {
            this.b.reportSoftInputArea(this.i.a());
        }
    }
}
