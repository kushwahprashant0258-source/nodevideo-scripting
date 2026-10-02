package com.google.android.play.core.assetpacks.internal;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ao extends an {
    private final an a;
    private final long b;
    private final long c;

    public ao(an anVar, long j, long j2) {
        this.a = anVar;
        long jD = d(j);
        this.b = jD;
        this.c = d(jD + j2);
    }

    private final long d(long j) {
        if (j < 0) {
            return 0L;
        }
        return j > this.a.a() ? this.a.a() : j;
    }

    @Override // com.google.android.play.core.assetpacks.internal.an
    public final long a() {
        return this.c - this.b;
    }

    @Override // com.google.android.play.core.assetpacks.internal.an
    protected final InputStream b(long j, long j2) throws IOException {
        long jD = d(this.b);
        return this.a.b(jD, d(j2 + jD) - jD);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }
}
