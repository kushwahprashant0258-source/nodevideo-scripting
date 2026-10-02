package com.unity3d.player;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.assetpacks.AssetPackException;
import com.google.android.play.core.assetpacks.AssetPackState;
import com.google.android.play.core.assetpacks.AssetPackStates;
import java.util.Map;

/* JADX INFO: renamed from: com.unity3d.player.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0029j implements OnCompleteListener {
    private IAssetPackManagerStatusQueryCallback a;
    private UnityPlayer b;
    private String[] c;

    public C0029j(UnityPlayer unityPlayer, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback, String[] strArr) {
        this.b = unityPlayer;
        this.a = iAssetPackManagerStatusQueryCallback;
        this.c = strArr;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        int errorCode;
        if (this.a == null) {
            return;
        }
        int i = 0;
        try {
            AssetPackStates assetPackStates = (AssetPackStates) task.getResult();
            Map<String, AssetPackState> mapPackStates = assetPackStates.packStates();
            int size = mapPackStates.size();
            String[] strArr = new String[size];
            int[] iArr = new int[size];
            int[] iArr2 = new int[size];
            for (AssetPackState assetPackState : mapPackStates.values()) {
                strArr[i] = assetPackState.name();
                iArr[i] = assetPackState.status();
                iArr2[i] = assetPackState.errorCode();
                i++;
            }
            this.b.invokeOnMainThread(new RunnableC0026i(this.a, assetPackStates.totalBytes(), strArr, iArr, iArr2));
        } catch (RuntimeExecutionException e) {
            e = e;
            String message = e.getMessage();
            String[] strArr2 = this.c;
            int length = strArr2.length;
            int i2 = 0;
            while (true) {
                int errorCode2 = -100;
                if (i2 < length) {
                    String str = strArr2[i2];
                    if (message.contains(str)) {
                        UnityPlayer unityPlayer = this.b;
                        IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback = this.a;
                        String[] strArr3 = {str};
                        int[] iArr3 = {0};
                        int[] iArr4 = new int[1];
                        while (!(e instanceof AssetPackException)) {
                            e = e.getCause();
                            if (e == null) {
                                iArr4[0] = errorCode2;
                                unityPlayer.invokeOnMainThread(new RunnableC0026i(iAssetPackManagerStatusQueryCallback, 0L, strArr3, iArr3, iArr4));
                                return;
                            }
                        }
                        errorCode2 = ((AssetPackException) e).getErrorCode();
                        iArr4[0] = errorCode2;
                        unityPlayer.invokeOnMainThread(new RunnableC0026i(iAssetPackManagerStatusQueryCallback, 0L, strArr3, iArr3, iArr4));
                        return;
                    }
                    i2++;
                } else {
                    String[] strArr4 = this.c;
                    int[] iArr5 = new int[strArr4.length];
                    int[] iArr6 = new int[strArr4.length];
                    int i3 = 0;
                    while (true) {
                        String[] strArr5 = this.c;
                        if (i3 >= strArr5.length) {
                            this.b.invokeOnMainThread(new RunnableC0026i(this.a, 0L, strArr5, iArr5, iArr6));
                            return;
                        }
                        iArr5[i3] = 0;
                        Throwable cause = e;
                        while (true) {
                            if (cause instanceof AssetPackException) {
                                errorCode = ((AssetPackException) cause).getErrorCode();
                                break;
                            }
                            cause = cause.getCause();
                            if (cause == null) {
                                errorCode = -100;
                                break;
                            }
                        }
                        iArr6[i3] = errorCode;
                        i3++;
                    }
                }
            }
        }
    }
}
