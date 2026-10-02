package com.google.android.play.core.assetpacks.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ad {
    private static final o a = new o("SplitInstallInfoProvider");
    private final Context b;
    private final String c;

    ad(Context context) {
        this.b = context;
        this.c = context.getPackageName();
    }

    public final Set a() {
        PackageInfo packageInfo;
        try {
            packageInfo = this.b.getPackageManager().getPackageInfo(this.c, 128);
        } catch (PackageManager.NameNotFoundException unused) {
            a.b("App is not found in PackageManager", new Object[0]);
            packageInfo = null;
        }
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return new HashSet();
        }
        HashSet hashSet = new HashSet();
        Bundle bundle = packageInfo.applicationInfo.metaData;
        HashSet<String> hashSet2 = new HashSet();
        if (bundle != null) {
            String string = bundle.getString("com.android.dynamic.apk.fused.modules");
            if (string == null || string.isEmpty()) {
                a.a("App has no fused modules.", new Object[0]);
            } else {
                Collections.addAll(hashSet2, string.split(",", -1));
                hashSet2.remove("");
                hashSet2.remove("base");
            }
        }
        String[] strArr = packageInfo.splitNames;
        if (strArr != null) {
            a.a("Adding splits from package manager: %s", Arrays.toString(strArr));
            Collections.addAll(hashSet2, strArr);
        } else {
            a.a("No splits are found or app cannot be found in package manager.", new Object[0]);
        }
        ab abVarA = ac.a();
        if (abVarA != null) {
            hashSet2.addAll(abVarA.a());
        }
        for (String str : hashSet2) {
            if (!str.startsWith("config.") && !str.contains(".config.")) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }
}
