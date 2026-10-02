package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class ar extends al {
    final int c;
    final String d;
    final int e;
    final /* synthetic */ aw f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ar(aw awVar, TaskCompletionSource taskCompletionSource, int i, String str, int i2) {
        super(awVar, taskCompletionSource);
        this.f = awVar;
        this.c = i;
        this.d = str;
        this.e = i2;
    }

    @Override // com.google.android.play.core.assetpacks.al, com.google.android.play.core.assetpacks.internal.h
    public final void d(Bundle bundle) {
        this.f.f.u(this.a);
        aw.a.b("onError(%d), retrying notifyModuleCompleted...", Integer.valueOf(bundle.getInt("error_code")));
        int i = this.e;
        if (i > 0) {
            this.f.D(this.c, this.d, i - 1);
        }
    }
}
