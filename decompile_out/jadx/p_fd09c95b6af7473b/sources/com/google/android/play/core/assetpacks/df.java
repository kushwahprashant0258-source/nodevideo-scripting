package com.google.android.play.core.assetpacks;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class df implements com.google.android.play.core.assetpacks.internal.as {
    private final com.google.android.play.core.assetpacks.internal.as a;
    private final com.google.android.play.core.assetpacks.internal.as b;
    private final com.google.android.play.core.assetpacks.internal.as c;
    private final com.google.android.play.core.assetpacks.internal.as d;

    public df(com.google.android.play.core.assetpacks.internal.as asVar, com.google.android.play.core.assetpacks.internal.as asVar2, com.google.android.play.core.assetpacks.internal.as asVar3, com.google.android.play.core.assetpacks.internal.as asVar4) {
        this.a = asVar;
        this.b = asVar2;
        this.c = asVar3;
        this.d = asVar4;
    }

    @Override // com.google.android.play.core.assetpacks.internal.as
    public final /* bridge */ /* synthetic */ Object a() {
        Object objA = this.a.a();
        return new de((bh) objA, com.google.android.play.core.assetpacks.internal.aq.c(this.b), (co) this.c.a(), com.google.android.play.core.assetpacks.internal.aq.c(this.d));
    }
}
