package com.unity3d.player;

import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
final class D1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ boolean e;
    final /* synthetic */ long f;
    final /* synthetic */ long g;
    final /* synthetic */ I1 h;

    D1(I1 i1, String str, int i, int i2, int i3, boolean z, long j, long j2) {
        this.h = i1;
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = z;
        this.f = j;
        this.g = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        I1 i1 = this.h;
        if (i1.f != null) {
            AbstractC0060y.Log(5, "Video already playing");
            I1 i2 = this.h;
            i2.g = 2;
            i2.d.release();
            return;
        }
        I1 i3 = this.h;
        i1.f = new A1(i3.b, i3.a, this.a, this.b, this.c, this.d, this.e, this.f, this.g, new C1(this));
        I1 i4 = this.h;
        if (i4.f != null) {
            FrameLayout frameLayout = i4.a.getFrameLayout();
            frameLayout.bringToFront();
            frameLayout.addView(this.h.f);
        }
    }
}
