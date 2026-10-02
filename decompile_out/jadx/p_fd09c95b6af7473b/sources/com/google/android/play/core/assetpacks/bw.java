package com.google.android.play.core.assetpacks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class bw extends FilterInputStream {
    private final ds a;
    private byte[] b;
    private long c;
    private boolean d;
    private boolean e;

    bw(InputStream inputStream) {
        super(inputStream);
        this.a = new ds();
        this.b = new byte[4096];
        this.d = false;
        this.e = false;
    }

    private final int e(byte[] bArr, int i, int i2) throws IOException {
        return Math.max(0, super.read(bArr, i, i2));
    }

    private final boolean f(int i) throws IOException {
        int iE = e(this.b, 0, i);
        if (iE != i) {
            int i2 = i - iE;
            if (e(this.b, iE, i2) != i2) {
                this.a.b(this.b, 0, iE);
                return false;
            }
        }
        this.a.b(this.b, 0, i);
        return true;
    }

    final long a() {
        return this.c;
    }

    final eu b() throws IOException {
        byte[] bArr;
        if (this.c > 0) {
            do {
                bArr = this.b;
            } while (read(bArr, 0, bArr.length) != -1);
        }
        if (this.d || this.e) {
            return new bq(null, -1L, -1, false, false, null);
        }
        if (!f(30)) {
            this.d = true;
            return this.a.c();
        }
        eu euVarC = this.a.c();
        if (euVarC.d()) {
            this.e = true;
            return euVarC;
        }
        if (euVarC.b() == 4294967295L) {
            throw new ck("Files bigger than 4GiB are not supported.");
        }
        int iA = this.a.a() - 30;
        int length = this.b.length;
        long j = iA;
        if (j > length) {
            do {
                length += length;
            } while (length < j);
            this.b = Arrays.copyOf(this.b, length);
        }
        if (!f(iA)) {
            this.d = true;
            return this.a.c();
        }
        eu euVarC2 = this.a.c();
        this.c = euVarC2.b();
        return euVarC2;
    }

    final boolean c() {
        return this.e;
    }

    final boolean d() {
        return this.d;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.c;
        if (j <= 0 || this.d) {
            return -1;
        }
        int iE = e(bArr, i, (int) Math.min(j, i2));
        this.c -= (long) iE;
        if (iE != 0) {
            return iE;
        }
        this.d = true;
        return 0;
    }
}
