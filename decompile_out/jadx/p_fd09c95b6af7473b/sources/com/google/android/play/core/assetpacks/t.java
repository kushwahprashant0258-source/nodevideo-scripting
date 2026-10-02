package com.google.android.play.core.assetpacks;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class t implements com.google.android.play.core.assetpacks.internal.as {
    private final com.google.android.play.core.assetpacks.internal.as a;
    private final com.google.android.play.core.assetpacks.internal.as b;
    private final com.google.android.play.core.assetpacks.internal.as c;

    public t(com.google.android.play.core.assetpacks.internal.as asVar, com.google.android.play.core.assetpacks.internal.as asVar2, com.google.android.play.core.assetpacks.internal.as asVar3) {
        this.a = asVar;
        this.b = asVar2;
        this.c = asVar3;
    }

    @Override // com.google.android.play.core.assetpacks.internal.as
    public final /* bridge */ /* synthetic */ Object a() {
        y yVar = p.b(((u) this.a).b()) == null ? (y) com.google.android.play.core.assetpacks.internal.aq.c(this.b).a() : (y) com.google.android.play.core.assetpacks.internal.aq.c(this.c).a();
        com.google.android.play.core.assetpacks.internal.ar.a(yVar);
        return yVar;
    }
}
