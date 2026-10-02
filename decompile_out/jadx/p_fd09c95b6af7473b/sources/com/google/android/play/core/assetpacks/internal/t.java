package com.google.android.play.core.assetpacks.internal;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class t extends p {
    final /* synthetic */ z a;

    t(z zVar) {
        this.a = zVar;
    }

    @Override // com.google.android.play.core.assetpacks.internal.p
    public final void a() {
        synchronized (this.a.g) {
            if (this.a.l.get() > 0 && this.a.l.decrementAndGet() > 0) {
                this.a.c.d("Leaving the connection open for other ongoing calls.", new Object[0]);
                return;
            }
            z zVar = this.a;
            if (zVar.n != null) {
                zVar.c.d("Unbind from service.", new Object[0]);
                z zVar2 = this.a;
                zVar2.b.unbindService(zVar2.m);
                this.a.h = false;
                this.a.n = null;
                this.a.m = null;
            }
            this.a.w();
        }
    }
}
