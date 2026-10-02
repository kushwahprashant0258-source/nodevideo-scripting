package com.google.android.play.core.assetpacks;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class co {
    private final Map a = new HashMap();

    co() {
    }

    final synchronized double a(String str) {
        Double d = (Double) this.a.get(str);
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    final synchronized double b(String str, dg dgVar) {
        double d;
        d = (((double) ((ce) dgVar).f) + 1.0d) / ((double) ((ce) dgVar).g);
        this.a.put(str, Double.valueOf(d));
        return d;
    }

    final synchronized void c(String str) {
        this.a.put(str, Double.valueOf(0.0d));
    }
}
