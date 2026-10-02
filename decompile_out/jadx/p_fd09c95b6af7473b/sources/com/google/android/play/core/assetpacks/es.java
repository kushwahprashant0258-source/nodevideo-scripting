package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class es {
    private static final com.google.android.play.core.assetpacks.internal.o a = new com.google.android.play.core.assetpacks.internal.o("VerifySliceTaskHandler");
    private final bh b;

    es(bh bhVar) {
        this.b = bhVar;
    }

    private final void b(er erVar, File file) {
        try {
            File fileO = this.b.o(erVar.l, erVar.a, erVar.b, erVar.c);
            if (!fileO.exists()) {
                throw new ck(String.format("Cannot find metadata files for slice %s.", erVar.c), erVar.k);
            }
            try {
                if (!dq.a(eq.a(file, fileO)).equals(erVar.d)) {
                    throw new ck(String.format("Verification failed for slice %s.", erVar.c), erVar.k);
                }
                a.d("Verification of slice %s of pack %s successful.", erVar.c, erVar.l);
            } catch (IOException e) {
                throw new ck(String.format("Could not digest file during verification for slice %s.", erVar.c), e, erVar.k);
            } catch (NoSuchAlgorithmException e2) {
                throw new ck("SHA256 algorithm not supported.", e2, erVar.k);
            }
        } catch (IOException e3) {
            throw new ck(String.format("Could not reconstruct slice archive during verification for slice %s.", erVar.c), e3, erVar.k);
        }
    }

    public final void a(er erVar) {
        File fileP = this.b.p(erVar.l, erVar.a, erVar.b, erVar.c);
        if (!fileP.exists()) {
            throw new ck(String.format("Cannot find unverified files for slice %s.", erVar.c), erVar.k);
        }
        b(erVar, fileP);
        File fileQ = this.b.q(erVar.l, erVar.a, erVar.b, erVar.c);
        if (!fileQ.exists()) {
            fileQ.mkdirs();
        }
        if (!fileP.renameTo(fileQ)) {
            throw new ck(String.format("Failed to move slice %s after verification.", erVar.c), erVar.k);
        }
    }
}
