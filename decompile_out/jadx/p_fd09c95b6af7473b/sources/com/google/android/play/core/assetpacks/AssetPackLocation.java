package com.google.android.play.core.assetpacks;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AssetPackLocation {
    private static final AssetPackLocation a = new bm(1, null, null);

    static AssetPackLocation a() {
        return a;
    }

    public abstract String assetsPath();

    public abstract int packStorageMethod();

    public abstract String path();
}
