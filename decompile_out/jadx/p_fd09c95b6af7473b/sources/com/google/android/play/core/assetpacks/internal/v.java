package com.google.android.play.core.assetpacks.internal;

import android.os.IBinder;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class v extends p {
    final /* synthetic */ IBinder a;
    final /* synthetic */ y b;

    v(y yVar, IBinder iBinder) {
        this.b = yVar;
        this.a = iBinder;
    }

    @Override // com.google.android.play.core.assetpacks.internal.p
    public final void a() {
        this.b.a.n = e.b(this.a);
        z.q(this.b.a);
        this.b.a.h = false;
        Iterator it = this.b.a.e.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.b.a.e.clear();
    }
}
