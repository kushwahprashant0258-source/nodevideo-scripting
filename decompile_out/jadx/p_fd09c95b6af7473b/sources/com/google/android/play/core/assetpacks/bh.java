package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class bh {
    private static final com.google.android.play.core.assetpacks.internal.o a = new com.google.android.play.core.assetpacks.internal.o("AssetPackStorage");
    private static final long b = TimeUnit.DAYS.toMillis(14);
    private static final long c = TimeUnit.DAYS.toMillis(28);
    private final Context d;
    private final ed e;

    bh(Context context, ed edVar) {
        this.d = context;
        this.e = edVar;
    }

    private static long H(File file, boolean z) {
        if (!file.exists()) {
            return -1L;
        }
        ArrayList arrayList = new ArrayList();
        if (z && file.listFiles().length > 1) {
            a.e("Multiple pack versions found, using highest version code.", new Object[0]);
        }
        try {
            for (File file2 : file.listFiles()) {
                if (!file2.getName().equals("stale.tmp")) {
                    arrayList.add(Long.valueOf(file2.getName()));
                }
            }
        } catch (NumberFormatException e) {
            a.c(e, "Corrupt asset pack directories.", new Object[0]);
        }
        if (arrayList.isEmpty()) {
            return -1L;
        }
        Collections.sort(arrayList);
        return ((Long) arrayList.get(arrayList.size() - 1)).longValue();
    }

    private final File I(String str) {
        return new File(L(), str);
    }

    private final File J(String str, int i, long j) {
        return new File(j(str, i, j), "merge.tmp");
    }

    private final File K(String str, int i, long j) {
        return new File(new File(new File(M(), str), String.valueOf(i)), String.valueOf(j));
    }

    private final File L() {
        return new File(this.d.getFilesDir(), "assetpacks");
    }

    private final File M() {
        return new File(L(), "_tmp");
    }

    private static List N(PackageInfo packageInfo, String str) {
        ArrayList arrayList = new ArrayList();
        if (packageInfo.splitNames == null) {
            return arrayList;
        }
        for (int i = (-Arrays.binarySearch(packageInfo.splitNames, str)) - 1; i < packageInfo.splitNames.length && packageInfo.splitNames[i].startsWith(str); i++) {
            arrayList.add(packageInfo.applicationInfo.splitSourceDirs[i]);
        }
        return arrayList;
    }

    private final List O() {
        ArrayList arrayList = new ArrayList();
        try {
            if (L().exists() && L().listFiles() != null) {
                for (File file : L().listFiles()) {
                    if (!file.getCanonicalPath().equals(M().getCanonicalPath())) {
                        arrayList.add(file);
                    }
                }
                return arrayList;
            }
            return arrayList;
        } catch (IOException e) {
            a.b("Could not process directory while scanning installed packs. %s", e);
        }
    }

    private static void P(File file) {
        if (file.listFiles() == null || file.listFiles().length <= 1) {
            return;
        }
        long jH = H(file, false);
        for (File file2 : file.listFiles()) {
            if (!file2.getName().equals(String.valueOf(jH)) && !file2.getName().equals("stale.tmp")) {
                Q(file2);
            }
        }
    }

    private static boolean Q(File file) {
        File[] fileArrListFiles = file.listFiles();
        boolean zQ = true;
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                zQ &= Q(file2);
            }
        }
        if (file.delete()) {
            return zQ;
        }
        return false;
    }

    final void A(String str, int i, long j, int i2) throws IOException {
        File fileJ = J(str, i, j);
        Properties properties = new Properties();
        properties.put("numberOfMerges", String.valueOf(i2));
        fileJ.getParentFile().mkdirs();
        fileJ.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(fileJ);
        properties.store(fileOutputStream, (String) null);
        fileOutputStream.close();
    }

    final void B(String str, int i, long j) {
        File fileI = I(str);
        if (fileI.exists()) {
            for (File file : fileI.listFiles()) {
                if (!file.getName().equals(String.valueOf(i)) && !file.getName().equals("stale.tmp")) {
                    Q(file);
                } else if (file.getName().equals(String.valueOf(i))) {
                    for (File file2 : file.listFiles()) {
                        if (!file2.getName().equals(String.valueOf(j))) {
                            Q(file2);
                        }
                    }
                }
            }
        }
    }

    final void C(List list) {
        int iA = this.e.a();
        for (File file : O()) {
            if (!list.contains(file.getName()) && H(file, true) != iA) {
                Q(file);
            }
        }
    }

    final boolean D(String str) {
        if (I(str).exists()) {
            return Q(I(str));
        }
        return true;
    }

    final boolean E(String str, int i, long j) {
        if (K(str, i, j).exists()) {
            return Q(K(str, i, j));
        }
        return true;
    }

    final boolean F(String str, int i, long j) {
        if (h(str, i, j).exists()) {
            return Q(h(str, i, j));
        }
        return true;
    }

    final boolean G(String str) {
        try {
            return r(str) != null;
        } catch (IOException unused) {
        }
    }

    final int a(String str) {
        return (int) H(I(str), true);
    }

    final int b(String str, int i, long j) throws IOException {
        File fileJ = J(str, i, j);
        if (!fileJ.exists()) {
            return 0;
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(fileJ);
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
            if (properties.getProperty("numberOfMerges") == null) {
                throw new ck("Merge checkpoint file corrupt.");
            }
            try {
                return Integer.parseInt(properties.getProperty("numberOfMerges"));
            } catch (NumberFormatException e) {
                throw new ck("Merge checkpoint file corrupt.", e);
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                try {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                } catch (Exception unused) {
                }
            }
            throw th;
        }
    }

    final long c(String str) {
        return H(g(str, (int) H(I(str), true)), true);
    }

    final AssetLocation d(String str, String str2, List list) {
        if (list == null) {
            return null;
        }
        String path = new File("assets", str2).getPath();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            try {
                AssetLocation assetLocationA = bt.a(str3, path);
                if (assetLocationA != null) {
                    return assetLocationA;
                }
            } catch (IOException e) {
                a.c(e, "Failed to parse APK file '%s' looking for asset '%s'.", str3, str2);
                return null;
            }
        }
        a.a("The asset %s is not present in Asset Pack %s. Searched in APKs: %s", str2, str, list);
        return null;
    }

    final AssetLocation e(String str, String str2, AssetPackLocation assetPackLocation) {
        File file = new File(assetPackLocation.assetsPath(), str2);
        if (file.exists()) {
            return new bl(file.getPath(), 0L, file.length());
        }
        a.a("The asset %s is not present in Asset Pack %s. Searched in folder: %s", str2, str, assetPackLocation.assetsPath());
        return null;
    }

    final AssetPackLocation f(String str) throws IOException {
        String strR = r(str);
        if (strR == null) {
            return null;
        }
        File file = new File(strR, "assets");
        if (file.isDirectory()) {
            return new bm(0, strR, file.getCanonicalPath());
        }
        a.b("Failed to find assets directory: %s", file);
        return null;
    }

    final File g(String str, int i) {
        return new File(I(str), String.valueOf(i));
    }

    final File h(String str, int i, long j) {
        return new File(g(str, i), String.valueOf(j));
    }

    final File i(String str, int i, long j) {
        return new File(h(str, i, j), "_metadata");
    }

    final File j(String str, int i, long j) {
        return new File(K(str, i, j), "_packs");
    }

    final File k(String str, int i, long j) {
        return new File(i(str, i, j), "properties.dat");
    }

    final File l(String str, int i, long j) {
        return new File(new File(K(str, i, j), "_slices"), "_metadata");
    }

    final File m(String str, int i, long j, String str2) {
        return new File(o(str, i, j, str2), "checkpoint_ext.dat");
    }

    final File n(String str, int i, long j, String str2) {
        return new File(o(str, i, j, str2), "checkpoint.dat");
    }

    final File o(String str, int i, long j, String str2) {
        return new File(l(str, i, j), str2);
    }

    final File p(String str, int i, long j, String str2) {
        return new File(new File(new File(K(str, i, j), "_slices"), "_unverified"), str2);
    }

    final File q(String str, int i, long j, String str2) {
        return new File(new File(new File(K(str, i, j), "_slices"), "_verified"), str2);
    }

    final String r(String str) throws IOException {
        int length;
        File file = new File(L(), str);
        if (!file.exists()) {
            a.a("Pack not found with pack name: %s", str);
            return null;
        }
        File file2 = new File(file, String.valueOf(this.e.a()));
        if (!file2.exists()) {
            a.a("Pack not found with pack name: %s app version: %s", str, Integer.valueOf(this.e.a()));
            return null;
        }
        File[] fileArrListFiles = file2.listFiles();
        if (fileArrListFiles == null || (length = fileArrListFiles.length) == 0) {
            a.a("No pack version found for pack name: %s app version: %s", str, Integer.valueOf(this.e.a()));
            return null;
        }
        if (length <= 1) {
            return fileArrListFiles[0].getCanonicalPath();
        }
        a.b("Multiple pack versions found for pack name: %s app version: %s", str, Integer.valueOf(this.e.a()));
        return null;
    }

    final List s(String str) {
        PackageInfo packageInfo;
        String str2 = null;
        try {
            packageInfo = this.d.getPackageManager().getPackageInfo(this.d.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            a.b("Could not find PackageInfo.", new Object[0]);
            packageInfo = null;
        }
        if (packageInfo == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (packageInfo.splitNames == null || packageInfo.applicationInfo.splitSourceDirs == null) {
            a.a("No splits present for package %s.", str);
        } else {
            int iBinarySearch = Arrays.binarySearch(packageInfo.splitNames, str);
            if (iBinarySearch < 0) {
                a.a("Asset Pack '%s' is not installed.", str);
            } else {
                str2 = packageInfo.applicationInfo.splitSourceDirs[iBinarySearch];
            }
        }
        if (str2 == null) {
            arrayList.add(packageInfo.applicationInfo.sourceDir);
            arrayList.addAll(N(packageInfo, "config."));
            return arrayList;
        }
        arrayList.add(str2);
        arrayList.addAll(N(packageInfo, String.valueOf(str).concat(".config.")));
        return arrayList;
    }

    final Map t() {
        HashMap map = new HashMap();
        Iterator it = O().iterator();
        while (it.hasNext()) {
            String name = ((File) it.next()).getName();
            int iH = (int) H(I(name), true);
            long jH = H(g(name, iH), true);
            if (h(name, iH, jH).exists()) {
                map.put(name, Long.valueOf(jH));
            }
        }
        return map;
    }

    final Map u() {
        HashMap map = new HashMap();
        for (String str : v().keySet()) {
            map.put(str, Long.valueOf(c(str)));
        }
        return map;
    }

    final Map v() {
        HashMap map = new HashMap();
        try {
            for (File file : O()) {
                AssetPackLocation assetPackLocationF = f(file.getName());
                if (assetPackLocationF != null) {
                    map.put(file.getName(), assetPackLocationF);
                }
            }
        } catch (IOException e) {
            a.b("Could not process directory while scanning installed packs: %s", e);
        }
        return map;
    }

    final void w() {
        for (File file : O()) {
            if (file.listFiles() != null) {
                P(file);
                long jH = H(file, false);
                if (this.e.a() != jH) {
                    try {
                        new File(new File(file, String.valueOf(jH)), "stale.tmp").createNewFile();
                    } catch (IOException unused) {
                        a.b("Could not write staleness marker.", new Object[0]);
                    }
                }
                for (File file2 : file.listFiles()) {
                    P(file2);
                }
            }
        }
    }

    final void x() {
        if (M().exists()) {
            for (File file : M().listFiles()) {
                if (System.currentTimeMillis() - file.lastModified() > b) {
                    Q(file);
                } else {
                    P(file);
                }
            }
        }
    }

    final void y() {
        for (File file : O()) {
            if (file.listFiles() != null) {
                for (File file2 : file.listFiles()) {
                    File file3 = new File(file2, "stale.tmp");
                    if (file3.exists() && System.currentTimeMillis() - file3.lastModified() > c) {
                        Q(file2);
                    }
                }
            }
        }
    }

    final void z() {
        Q(L());
    }
}
