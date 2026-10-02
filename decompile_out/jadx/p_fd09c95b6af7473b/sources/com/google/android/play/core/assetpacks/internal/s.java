package com.google.android.play.core.assetpacks.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class s extends p {
    final /* synthetic */ TaskCompletionSource a;
    final /* synthetic */ p b;
    final /* synthetic */ z c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    s(z zVar, TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, p pVar) {
        super(taskCompletionSource);
        this.c = zVar;
        this.a = taskCompletionSource2;
        this.b = pVar;
    }

    @Override // com.google.android.play.core.assetpacks.internal.p
    public final void a() {
        synchronized (this.c.g) {
            z.n(this.c, this.a);
            if (this.c.l.getAndIncrement() > 0) {
                this.c.c.d("Already connected to the service.", new Object[0]);
            }
            z.p(this.c, this.b);
        }
    }
}
