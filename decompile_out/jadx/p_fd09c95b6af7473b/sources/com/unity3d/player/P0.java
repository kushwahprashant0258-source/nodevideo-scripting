package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class P0 implements IPermissionRequestCallbacks {
    private long a;
    final /* synthetic */ UnityPlayer b;

    public P0(UnityPlayer unityPlayer, long j) {
        this.b = unityPlayer;
        this.a = j;
    }

    @Override // com.unity3d.player.IPermissionRequestCallbacks
    public final void onPermissionResult(String[] strArr, int[] iArr) {
        int length = iArr.length;
        boolean z = false;
        if (length != 0) {
            if (length != 1) {
                AbstractC0060y.Log(6, "Only a single permission request is supported");
                return;
            } else if (iArr[0] == 1) {
                z = true;
            }
        }
        if (this.a == 0) {
            return;
        }
        this.b.invokeOnMainThread((R0) new O0(this, z));
    }
}
