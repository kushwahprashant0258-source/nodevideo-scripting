package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class eg {
    private static final com.google.android.play.core.assetpacks.internal.o a = new com.google.android.play.core.assetpacks.internal.o("PatchSliceTaskHandler");
    private final bh b;
    private final com.google.android.play.core.assetpacks.internal.aq c;

    eg(bh bhVar, com.google.android.play.core.assetpacks.internal.aq aqVar) {
        this.b = bhVar;
        this.c = aqVar;
    }

    public final void a(ef efVar) {
        File fileH = this.b.h(efVar.l, efVar.a, efVar.b);
        File file = new File(this.b.i(efVar.l, efVar.a, efVar.b), efVar.f);
        try {
            InputStream gZIPInputStream = efVar.h;
            if (efVar.e == 2) {
                gZIPInputStream = new GZIPInputStream(gZIPInputStream, 8192);
            }
            try {
                bk bkVar = new bk(fileH, file);
                File fileP = this.b.p(efVar.l, efVar.c, efVar.d, efVar.f);
                if (!fileP.exists()) {
                    fileP.mkdirs();
                }
                eo eoVar = new eo(this.b, efVar.l, efVar.c, efVar.d, efVar.f);
                com.google.android.play.core.assetpacks.internal.am.a(bkVar, gZIPInputStream, new cn(fileP, eoVar), efVar.g);
                eoVar.i(0);
                gZIPInputStream.close();
                a.d("Patching and extraction finished for slice %s of pack %s.", efVar.f, efVar.l);
                ((y) this.c.a()).g(efVar.k, efVar.l, efVar.f, 0);
                try {
                    efVar.h.close();
                } catch (IOException unused) {
                    a.e("Could not close file for slice %s of pack %s.", efVar.f, efVar.l);
                }
            } catch (Throwable th) {
                try {
                    gZIPInputStream.close();
                } catch (Throwable th2) {
                    try {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
        } catch (IOException e) {
            a.b("IOException during patching %s.", e.getMessage());
            throw new ck(String.format("Error patching slice %s of pack %s.", efVar.f, efVar.l), e, efVar.k);
        }
    }
}
