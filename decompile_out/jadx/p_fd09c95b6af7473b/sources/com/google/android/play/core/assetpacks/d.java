package com.google.android.play.core.assetpacks;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    private static a a;

    static synchronized a a(Context context) {
        if (a == null) {
            cd cdVar = new cd(null);
            cdVar.b(new p(com.google.android.play.core.assetpacks.internal.ag.a(context)));
            a = cdVar.a();
        }
        return a;
    }
}
