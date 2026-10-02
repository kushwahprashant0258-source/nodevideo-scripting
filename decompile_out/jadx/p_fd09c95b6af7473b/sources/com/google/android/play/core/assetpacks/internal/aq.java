package com.google.android.play.core.assetpacks.internal;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class aq implements as {
    private static final Object a = new Object();
    private volatile as b;
    private volatile Object c = a;

    private aq(as asVar) {
        this.b = asVar;
    }

    public static as b(as asVar) {
        asVar.getClass();
        return asVar instanceof aq ? asVar : new aq(asVar);
    }

    public static aq c(as asVar) {
        return asVar instanceof aq ? (aq) asVar : new aq(asVar);
    }

    @Override // com.google.android.play.core.assetpacks.internal.as
    public final Object a() {
        Object objA = this.c;
        Object obj = a;
        if (objA == obj) {
            synchronized (this) {
                objA = this.c;
                if (objA == obj) {
                    objA = this.b.a();
                    Object obj2 = this.c;
                    if (obj2 != obj && obj2 != objA) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj2 + " & " + objA + ". This is likely due to a circular dependency.");
                    }
                    this.c = objA;
                    this.b = null;
                }
            }
        }
        return objA;
    }
}
