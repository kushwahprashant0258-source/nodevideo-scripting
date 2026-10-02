package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import com.google.android.play.core.assetpacks.AssetPackLocation;
import com.google.android.play.core.assetpacks.AssetPackManager;
import com.google.android.play.core.assetpacks.AssetPackManagerFactory;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: renamed from: com.unity3d.player.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0032k {
    private static C0032k e;
    private UnityPlayer a;
    private AssetPackManager b;
    private HashSet c;
    private Object d;

    private C0032k(UnityPlayer unityPlayer, Context context) {
        if (e != null) {
            throw new RuntimeException("AssetPackManagerWrapper should be created only once. Use getInstance() instead.");
        }
        this.a = unityPlayer;
        this.b = AssetPackManagerFactory.getInstance(context);
        this.c = new HashSet();
    }

    public static C0032k a(UnityPlayer unityPlayer, Context context) {
        if (e == null) {
            e = new C0032k(unityPlayer, context);
        }
        return e;
    }

    public final Object a(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        C0014e c0014e = new C0014e(this, this.a, iAssetPackManagerDownloadStatusCallback);
        this.b.registerListener(c0014e);
        return c0014e;
    }

    public final String a(String str) {
        AssetPackLocation packLocation = this.b.getPackLocation(str);
        return packLocation == null ? "" : packLocation.assetsPath();
    }

    public final void a(Activity activity, IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
        this.b.showCellularDataConfirmation(activity).addOnSuccessListener(new C0020g(this.a, iAssetPackManagerMobileDataConfirmationCallback));
    }

    public final void a(Object obj) {
        if (obj instanceof C0014e) {
            this.b.unregisterListener((C0014e) obj);
        }
    }

    public final void a(String[] strArr) {
        this.b.cancel(Arrays.asList(strArr));
    }

    public final void a(String[] strArr, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        this.b.getPackStates(Arrays.asList(strArr)).addOnCompleteListener(new C0023h(this.a, iAssetPackManagerDownloadStatusCallback, strArr));
    }

    public final void a(String[] strArr, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback) {
        this.b.getPackStates(Arrays.asList(strArr)).addOnCompleteListener(new C0029j(this.a, iAssetPackManagerStatusQueryCallback, strArr));
    }

    public final void b(String str) {
        this.b.removePack(str);
    }
}
