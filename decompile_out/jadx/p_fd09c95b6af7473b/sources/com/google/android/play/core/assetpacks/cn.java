package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class cn extends OutputStream {
    private final ds a = new ds();
    private final File b;
    private final eo c;
    private long d;
    private long e;
    private FileOutputStream f;
    private eu g;

    cn(File file, eo eoVar) {
        this.b = file;
        this.c = eoVar;
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IllegalAccessException, IOException, InvocationTargetException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) throws IllegalAccessException, IOException, InvocationTargetException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IllegalAccessException, IOException, InvocationTargetException {
        int iMin;
        while (i2 > 0) {
            if (this.d == 0 && this.e == 0) {
                int iB = this.a.b(bArr, i, i2);
                if (iB == -1) {
                    return;
                }
                i += iB;
                i2 -= iB;
                eu euVarC = this.a.c();
                this.g = euVarC;
                if (euVarC.d()) {
                    this.d = 0L;
                    this.c.l(this.g.f(), 0, this.g.f().length);
                    this.e = this.g.f().length;
                } else if (!this.g.h() || this.g.g()) {
                    byte[] bArrF = this.g.f();
                    this.c.l(bArrF, 0, bArrF.length);
                    this.d = this.g.b();
                } else {
                    this.c.j(this.g.f());
                    File file = new File(this.b, this.g.c());
                    file.getParentFile().mkdirs();
                    this.d = this.g.b();
                    this.f = new FileOutputStream(file);
                }
            }
            if (!this.g.g()) {
                if (this.g.d()) {
                    this.c.e(this.e, bArr, i, i2);
                    this.e += (long) i2;
                    iMin = i2;
                } else if (!this.g.h()) {
                    iMin = (int) Math.min(i2, this.d);
                    this.c.e((((long) this.g.f().length) + this.g.b()) - this.d, bArr, i, iMin);
                    this.d -= (long) iMin;
                } else {
                    iMin = (int) Math.min(i2, this.d);
                    this.f.write(bArr, i, iMin);
                    long j = this.d - ((long) iMin);
                    this.d = j;
                    if (j == 0) {
                        this.f.close();
                    }
                }
                i += iMin;
                i2 -= iMin;
            }
        }
    }
}
