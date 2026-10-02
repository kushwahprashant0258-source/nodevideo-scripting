package com.google.android.play.core.assetpacks;

import java.io.InputStream;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class ce extends dg {
    final int a;
    final long b;
    final String c;
    final String d;
    final int e;
    final int f;
    final int g;
    final long h;
    final int i;
    final InputStream j;

    ce(int i, String str, int i2, long j, String str2, String str3, int i3, int i4, int i5, long j2, int i6, InputStream inputStream) {
        super(i, str);
        this.a = i2;
        this.b = j;
        this.c = str2;
        this.d = str3;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        this.h = j2;
        this.i = i6;
        this.j = inputStream;
    }

    final boolean a() {
        return this.f + 1 == this.g;
    }
}
