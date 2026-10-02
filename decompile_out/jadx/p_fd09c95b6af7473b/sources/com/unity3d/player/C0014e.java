package com.unity3d.player;

import com.google.android.play.core.assetpacks.AssetPackState;
import com.google.android.play.core.assetpacks.AssetPackStateUpdateListener;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: renamed from: com.unity3d.player.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0014e implements AssetPackStateUpdateListener {
    private HashSet a;
    private UnityPlayer b;
    final /* synthetic */ C0032k c;

    public C0014e(C0032k c0032k, UnityPlayer unityPlayer, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        this.c = c0032k;
        this.b = unityPlayer;
        HashSet hashSet = new HashSet();
        this.a = hashSet;
        hashSet.add(iAssetPackManagerDownloadStatusCallback);
    }

    public final synchronized void a(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        this.a.add(iAssetPackManagerDownloadStatusCallback);
    }

    @Override // com.google.android.play.core.listener.StateUpdatedListener
    public final void onStateUpdate(AssetPackState assetPackState) {
        AssetPackState assetPackState2 = assetPackState;
        synchronized (this) {
            if (assetPackState2.status() == 4 || assetPackState2.status() == 5 || assetPackState2.status() == 0) {
                synchronized (C0032k.e) {
                    this.c.c.remove(assetPackState2.name());
                    if (this.c.c.isEmpty()) {
                        C0032k c0032k = this.c;
                        c0032k.a(c0032k.d);
                        this.c.d = null;
                    }
                }
            }
            if (this.a.size() == 0) {
                return;
            }
            this.b.invokeOnMainThread(new RunnableC0011d((Set) this.a.clone(), assetPackState2.name(), assetPackState2.status(), assetPackState2.totalBytesToDownload(), assetPackState2.bytesDownloaded(), assetPackState2.transferProgressPercentage(), assetPackState2.errorCode()));
        }
    }
}
