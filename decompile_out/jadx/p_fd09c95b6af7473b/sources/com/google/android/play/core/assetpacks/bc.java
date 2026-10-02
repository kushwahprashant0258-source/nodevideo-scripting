package com.google.android.play.core.assetpacks;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class bc implements com.google.android.play.core.assetpacks.internal.as {
    private final com.google.android.play.core.assetpacks.internal.as a;
    private final com.google.android.play.core.assetpacks.internal.as b;
    private final com.google.android.play.core.assetpacks.internal.as c;
    private final com.google.android.play.core.assetpacks.internal.as d;
    private final com.google.android.play.core.assetpacks.internal.as e;
    private final com.google.android.play.core.assetpacks.internal.as f;
    private final com.google.android.play.core.assetpacks.internal.as g;
    private final com.google.android.play.core.assetpacks.internal.as h;
    private final com.google.android.play.core.assetpacks.internal.as i;

    public bc(com.google.android.play.core.assetpacks.internal.as asVar, com.google.android.play.core.assetpacks.internal.as asVar2, com.google.android.play.core.assetpacks.internal.as asVar3, com.google.android.play.core.assetpacks.internal.as asVar4, com.google.android.play.core.assetpacks.internal.as asVar5, com.google.android.play.core.assetpacks.internal.as asVar6, com.google.android.play.core.assetpacks.internal.as asVar7, com.google.android.play.core.assetpacks.internal.as asVar8, com.google.android.play.core.assetpacks.internal.as asVar9) {
        this.a = asVar;
        this.b = asVar2;
        this.c = asVar3;
        this.d = asVar4;
        this.e = asVar5;
        this.f = asVar6;
        this.g = asVar7;
        this.h = asVar8;
        this.i = asVar9;
    }

    @Override // com.google.android.play.core.assetpacks.internal.as
    public final /* bridge */ /* synthetic */ Object a() {
        Context contextB = ((u) this.a).b();
        Object objA = this.b.a();
        Object objA2 = this.c.a();
        com.google.android.play.core.assetpacks.internal.aq aqVarC = com.google.android.play.core.assetpacks.internal.aq.c(this.d);
        Object objA3 = this.e.a();
        return new bb(contextB, (de) objA, (cl) objA2, aqVarC, (co) objA3, (bx) this.f.a(), com.google.android.play.core.assetpacks.internal.aq.c(this.g), com.google.android.play.core.assetpacks.internal.aq.c(this.h), (eb) this.i.a());
    }
}
