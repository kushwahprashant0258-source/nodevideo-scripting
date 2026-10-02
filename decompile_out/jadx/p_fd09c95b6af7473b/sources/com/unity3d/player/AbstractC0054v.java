package com.unity3d.player;

import android.app.Activity;
import android.content.pm.ApplicationInfo;

/* JADX INFO: renamed from: com.unity3d.player.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
abstract class AbstractC0054v {
    /* JADX WARN: Code duplicated, block: B:16:0x002f  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    public static void a(Activity activity) {
        ?? r0;
        if (activity == null || activity.getWindow() == null || !PlatformSupport.PIE_SUPPORT) {
            return;
        }
        if (PlatformSupport.VANILLA_ICE_CREAM_SUPPORT) {
            r0 = 3;
        } else {
            try {
                if (PlatformSupport.RED_VELVET_CAKE_SUPPORT) {
                    ApplicationInfo applicationInfo = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128);
                    if (applicationInfo != null && applicationInfo.metaData.getBoolean("unity.render-outside-safearea")) {
                        r0 = 3;
                    }
                } else {
                    ApplicationInfo applicationInfo2 = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128);
                    if (applicationInfo2 != null) {
                        r0 = applicationInfo2.metaData.getBoolean("unity.render-outside-safearea");
                    }
                }
            } catch (Exception unused) {
            }
            r0 = 0;
        }
        activity.getWindow().getAttributes().layoutInDisplayCutoutMode = r0;
    }
}
