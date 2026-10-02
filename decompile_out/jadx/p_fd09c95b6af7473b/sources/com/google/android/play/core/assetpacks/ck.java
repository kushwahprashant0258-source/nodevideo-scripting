package com.google.android.play.core.assetpacks;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class ck extends RuntimeException {
    final int a;

    ck(String str) {
        super(str);
        this.a = -1;
    }

    ck(String str, int i) {
        super(str);
        this.a = i;
    }

    ck(String str, Exception exc) {
        super(str, exc);
        this.a = -1;
    }

    ck(String str, Exception exc, int i) {
        super(str, exc);
        this.a = i;
    }
}
