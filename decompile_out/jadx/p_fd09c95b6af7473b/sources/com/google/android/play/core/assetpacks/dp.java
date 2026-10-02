package com.google.android.play.core.assetpacks;

import android.content.Context;
import java.io.File;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class dp implements com.google.android.play.core.assetpacks.internal.as {
    private final com.google.android.play.core.assetpacks.internal.as a;
    private final com.google.android.play.core.assetpacks.internal.as b;
    private final com.google.android.play.core.assetpacks.internal.as c;
    private final com.google.android.play.core.assetpacks.internal.as d;
    private final com.google.android.play.core.assetpacks.internal.as e;
    private final com.google.android.play.core.assetpacks.internal.as f;
    private final com.google.android.play.core.assetpacks.internal.as g;

    public dp(com.google.android.play.core.assetpacks.internal.as asVar, com.google.android.play.core.assetpacks.internal.as asVar2, com.google.android.play.core.assetpacks.internal.as asVar3, com.google.android.play.core.assetpacks.internal.as asVar4, com.google.android.play.core.assetpacks.internal.as asVar5, com.google.android.play.core.assetpacks.internal.as asVar6, com.google.android.play.core.assetpacks.internal.as asVar7) {
        this.a = asVar;
        this.b = asVar2;
        this.c = asVar3;
        this.d = asVar4;
        this.e = asVar5;
        this.f = asVar6;
        this.g = asVar7;
    }

    @Override // com.google.android.play.core.assetpacks.internal.as
    public final /* bridge */ /* synthetic */ Object a() {
        String str = (String) this.a.a();
        Object objA = this.b.a();
        Object objA2 = this.c.a();
        Context contextB = ((u) this.d).b();
        Object objA3 = this.e.a();
        return new Cdo(str != null ? new File(contextB.getExternalFilesDir(null), str) : contextB.getExternalFilesDir(null), (bb) objA, (co) objA2, contextB, (ed) objA3, com.google.android.play.core.assetpacks.internal.aq.c(this.f), (eb) this.g.a());
    }
}
