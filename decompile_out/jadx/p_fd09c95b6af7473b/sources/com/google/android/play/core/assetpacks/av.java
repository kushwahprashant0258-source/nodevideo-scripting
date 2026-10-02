package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class av extends al {
    private final co c;
    private final eb d;

    av(aw awVar, TaskCompletionSource taskCompletionSource, co coVar, eb ebVar) {
        super(awVar, taskCompletionSource);
        this.c = coVar;
        this.d = ebVar;
    }

    @Override // com.google.android.play.core.assetpacks.al, com.google.android.play.core.assetpacks.internal.h
    public final void n(int i, Bundle bundle) {
        super.n(i, bundle);
        this.a.trySetResult(AssetPackStates.a(bundle, this.c, this.d, bf.a));
    }
}
