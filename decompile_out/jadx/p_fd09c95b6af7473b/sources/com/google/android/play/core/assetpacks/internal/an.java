package com.google.android.play.core.assetpacks.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class an implements Closeable {
    public abstract long a();

    protected abstract InputStream b(long j, long j2) throws IOException;

    public final synchronized InputStream c() throws IOException {
        return b(0L, a());
    }
}
