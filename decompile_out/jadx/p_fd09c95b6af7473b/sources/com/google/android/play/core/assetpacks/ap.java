package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class ap extends al {
    final /* synthetic */ aw c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ap(aw awVar, TaskCompletionSource taskCompletionSource) {
        super(awVar, taskCompletionSource);
        this.c = awVar;
    }

    @Override // com.google.android.play.core.assetpacks.al, com.google.android.play.core.assetpacks.internal.h
    public final void d(Bundle bundle) {
        this.c.g.u(this.a);
        int i = bundle.getInt("error_code");
        aw.a.b("onError(%d)", Integer.valueOf(i));
        this.a.trySetException(new AssetPackException(i));
    }

    @Override // com.google.android.play.core.assetpacks.al, com.google.android.play.core.assetpacks.internal.h
    public final void h(Bundle bundle, Bundle bundle2) {
        super.h(bundle, bundle2);
        if (!this.c.h.compareAndSet(true, false)) {
            aw.a.e("Expected keepingAlive to be true, but was false.", new Object[0]);
        }
        if (bundle.getBoolean("keep_alive")) {
            this.c.f();
        }
    }
}
