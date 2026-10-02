package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class du {
    private static final com.google.android.play.core.assetpacks.internal.o a = new com.google.android.play.core.assetpacks.internal.o("MergeSliceTaskHandler");
    private final bh b;

    du(bh bhVar) {
        this.b = bhVar;
    }

    private static void b(File file, File file2) {
        if (!file.isDirectory()) {
            if (file2.exists()) {
                throw new ck("File clashing with existing file from other slice: ".concat(file2.toString()));
            }
            if (!file.renameTo(file2)) {
                throw new ck("Unable to move file: ".concat(String.valueOf(String.valueOf(file))));
            }
            return;
        }
        file2.mkdirs();
        for (File file3 : file.listFiles()) {
            b(file3, new File(file2, file3.getName()));
        }
        if (!file.delete()) {
            throw new ck("Unable to delete directory: ".concat(String.valueOf(String.valueOf(file))));
        }
    }

    public final void a(dt dtVar) {
        File fileQ = this.b.q(dtVar.l, dtVar.a, dtVar.b, dtVar.c);
        if (!fileQ.exists()) {
            throw new ck(String.format("Cannot find verified files for slice %s.", dtVar.c), dtVar.k);
        }
        File fileJ = this.b.j(dtVar.l, dtVar.a, dtVar.b);
        if (!fileJ.exists()) {
            fileJ.mkdirs();
        }
        b(fileQ, fileJ);
        try {
            this.b.A(dtVar.l, dtVar.a, dtVar.b, this.b.b(dtVar.l, dtVar.a, dtVar.b) + 1);
        } catch (IOException e) {
            a.b("Writing merge checkpoint failed with %s.", e.getMessage());
            throw new ck("Writing merge checkpoint failed.", e, dtVar.k);
        }
    }
}
