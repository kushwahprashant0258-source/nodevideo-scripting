package com.unity3d.player;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.assetpacks.AssetPackException;
import com.google.android.play.core.assetpacks.AssetPackState;
import com.google.android.play.core.assetpacks.AssetPackStates;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;

/* JADX INFO: renamed from: com.unity3d.player.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0023h implements OnCompleteListener {
    private IAssetPackManagerDownloadStatusCallback a;
    private UnityPlayer b;
    private String[] c;

    public C0023h(UnityPlayer unityPlayer, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback, String[] strArr) {
        this.b = unityPlayer;
        this.a = iAssetPackManagerDownloadStatusCallback;
        this.c = strArr;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        int errorCode;
        try {
            AssetPackStates assetPackStates = (AssetPackStates) task.getResult();
            Map<String, AssetPackState> mapPackStates = assetPackStates.packStates();
            if (mapPackStates.size() == 0) {
                return;
            }
            Vector vector = new Vector();
            for (AssetPackState assetPackState : mapPackStates.values()) {
                if (assetPackState.errorCode() != 0 || assetPackState.status() == 4 || assetPackState.status() == 5 || assetPackState.status() == 0) {
                    String strName = assetPackState.name();
                    int iStatus = assetPackState.status();
                    int iErrorCode = assetPackState.errorCode();
                    long j = assetPackStates.totalBytes();
                    this.b.invokeOnMainThread(new RunnableC0011d(Collections.singleton(this.a), strName, iStatus, j, iStatus == 4 ? j : 0L, 0, iErrorCode));
                } else {
                    vector.add(assetPackState.name());
                }
            }
            if (vector.size() > 0) {
                C0032k c0032k = C0032k.e;
                UnityPlayer unityPlayer = this.b;
                IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback = this.a;
                c0032k.getClass();
                synchronized (C0032k.e) {
                    Object obj = c0032k.d;
                    if (obj == null) {
                        C0014e c0014e = new C0014e(c0032k, unityPlayer, iAssetPackManagerDownloadStatusCallback);
                        c0032k.b.registerListener(c0014e);
                        c0032k.d = c0014e;
                    } else {
                        ((C0014e) obj).a(iAssetPackManagerDownloadStatusCallback);
                    }
                    c0032k.c.addAll(vector);
                    c0032k.b.fetch(vector);
                }
            }
        } catch (RuntimeExecutionException e) {
            e = e;
            String[] strArr = this.c;
            if (strArr.length != 1) {
                C0032k c0032k2 = C0032k.e;
                IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback2 = this.a;
                c0032k2.getClass();
                for (String str : strArr) {
                    c0032k2.b.getPackStates(Collections.singletonList(str)).addOnCompleteListener(new C0023h(c0032k2.a, iAssetPackManagerDownloadStatusCallback2, new String[]{str}));
                }
                return;
            }
            String str2 = strArr[0];
            while (true) {
                if (e instanceof AssetPackException) {
                    errorCode = ((AssetPackException) e).getErrorCode();
                    break;
                }
                e = e.getCause();
                if (e == null) {
                    errorCode = -100;
                    break;
                }
            }
            this.b.invokeOnMainThread(new RunnableC0011d(Collections.singleton(this.a), str2, 0, 0L, 0L, 0, errorCode));
        }
    }
}
