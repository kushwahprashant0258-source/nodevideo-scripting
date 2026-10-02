package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class eb {
    private static final com.google.android.play.core.assetpacks.internal.o a = new com.google.android.play.core.assetpacks.internal.o("PackMetadataManager");
    private final bh b;
    private final ed c;

    eb(bh bhVar, ed edVar) {
        this.b = bhVar;
        this.c = edVar;
    }

    final String a(String str) throws IllegalAccessException, InvocationTargetException {
        if (!this.b.G(str)) {
            return "";
        }
        int iA = this.c.a();
        bh bhVar = this.b;
        File fileK = bhVar.k(str, iA, bhVar.c(str));
        try {
            if (!fileK.exists()) {
                return String.valueOf(iA);
            }
            FileInputStream fileInputStream = new FileInputStream(fileK);
            try {
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                String property = properties.getProperty("moduleVersionTag");
                return property == null ? String.valueOf(iA) : property;
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            a.b("Failed to read pack version tag for pack %s", str);
            return "";
        }
    }

    final void b(String str, int i, long j, String str2) throws IllegalAccessException, IOException, InvocationTargetException {
        if (str2 == null || str2.isEmpty()) {
            str2 = String.valueOf(i);
        }
        Properties properties = new Properties();
        properties.put("moduleVersionTag", str2);
        File fileK = this.b.k(str, i, j);
        fileK.getParentFile().mkdirs();
        fileK.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(fileK);
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }
}
