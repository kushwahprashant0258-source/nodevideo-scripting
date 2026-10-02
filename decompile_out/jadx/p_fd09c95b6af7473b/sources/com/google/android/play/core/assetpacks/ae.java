package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class ae extends com.google.android.play.core.assetpacks.internal.p {
    final /* synthetic */ Map a;
    final /* synthetic */ TaskCompletionSource b;
    final /* synthetic */ aw c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ae(aw awVar, TaskCompletionSource taskCompletionSource, Map map, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.c = awVar;
        this.a = map;
        this.b = taskCompletionSource2;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.os.IInterface, com.google.android.play.core.assetpacks.internal.f] */
    @Override // com.google.android.play.core.assetpacks.internal.p
    protected final void a() {
        try {
            this.c.f.e().e(this.c.c, aw.n(this.a), new ao(this.c, this.b));
        } catch (RemoteException e) {
            aw.a.c(e, "syncPacks", new Object[0]);
            this.b.trySetException(new RuntimeException(e));
        }
    }
}
