package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class au extends al {
    private final co c;
    private final eb d;
    private final be e;

    au(aw awVar, TaskCompletionSource taskCompletionSource, co coVar, eb ebVar, be beVar) {
        super(awVar, taskCompletionSource);
        this.c = coVar;
        this.d = ebVar;
        this.e = beVar;
    }

    @Override // com.google.android.play.core.assetpacks.al, com.google.android.play.core.assetpacks.internal.h
    public final void m(Bundle bundle, Bundle bundle2) {
        super.m(bundle, bundle2);
        this.a.trySetResult(AssetPackStates.a(bundle, this.c, this.d, this.e));
    }
}
