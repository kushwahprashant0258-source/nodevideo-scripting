package com.google.android.play.core.assetpacks;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class cl {
    private static final com.google.android.play.core.assetpacks.internal.o a = new com.google.android.play.core.assetpacks.internal.o("ExtractorLooper");
    private final de b;
    private final cf c;
    private final es d;
    private final du e;
    private final dy f;
    private final eg g;
    private final ek h;
    private final dh i;
    private final AtomicBoolean j = new AtomicBoolean(false);
    private final com.google.android.play.core.assetpacks.internal.aq k;

    cl(de deVar, com.google.android.play.core.assetpacks.internal.aq aqVar, cf cfVar, es esVar, du duVar, dy dyVar, eg egVar, ek ekVar, dh dhVar) {
        this.b = deVar;
        this.k = aqVar;
        this.c = cfVar;
        this.d = esVar;
        this.e = duVar;
        this.f = dyVar;
        this.g = egVar;
        this.h = ekVar;
        this.i = dhVar;
    }

    private final void b(int i, Exception exc) {
        try {
            this.b.m(i, 5);
            this.b.n(i);
        } catch (ck unused) {
            a.b("Error during error handling: %s", exc.getMessage());
        }
    }

    final void a() {
        dg dgVarA;
        com.google.android.play.core.assetpacks.internal.o oVar = a;
        oVar.a("Run extractor loop", new Object[0]);
        if (!this.j.compareAndSet(false, true)) {
            oVar.e("runLoop already looping; return", new Object[0]);
            return;
        }
        while (true) {
            try {
                dgVarA = this.i.a();
            } catch (ck e) {
                a.b("Error while getting next extraction task: %s", e.getMessage());
                if (e.a >= 0) {
                    ((y) this.k.a()).i(e.a);
                    b(e.a, e);
                }
                dgVarA = null;
            }
            if (dgVarA == null) {
                this.j.set(false);
                return;
            }
            try {
                if (dgVarA instanceof ce) {
                    this.c.a((ce) dgVarA);
                } else if (dgVarA instanceof er) {
                    this.d.a((er) dgVarA);
                } else if (dgVarA instanceof dt) {
                    this.e.a((dt) dgVarA);
                } else if (dgVarA instanceof dw) {
                    this.f.a((dw) dgVarA);
                } else if (dgVarA instanceof ef) {
                    this.g.a((ef) dgVarA);
                } else if (dgVarA instanceof ei) {
                    this.h.a((ei) dgVarA);
                } else {
                    a.b("Unknown task type: %s", dgVarA.getClass().getName());
                }
            } catch (Exception e2) {
                a.b("Error during extraction task: %s", e2.getMessage());
                ((y) this.k.a()).i(dgVarA.k);
                b(dgVarA.k, e2);
            }
        }
    }
}
