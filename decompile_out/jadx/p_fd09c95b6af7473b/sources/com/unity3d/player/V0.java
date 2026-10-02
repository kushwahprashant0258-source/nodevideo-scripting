package com.unity3d.player;

import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
final class V0 implements Runnable {
    boolean a = false;
    boolean b = false;
    final /* synthetic */ Semaphore c;
    final /* synthetic */ UnityPlayerForActivityOrService d;
    final /* synthetic */ UnityPlayerForActivityOrService e;

    V0(UnityPlayerForActivityOrService unityPlayerForActivityOrService, Semaphore semaphore, UnityPlayerForActivityOrService unityPlayerForActivityOrService2) {
        this.e = unityPlayerForActivityOrService;
        this.c = semaphore;
        this.d = unityPlayerForActivityOrService2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.e.mSoftInput != null) {
            this.d.setOnHandleFocusListener(new T0(this));
            UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.e;
            unityPlayerForActivityOrService.mSoftInput.h = new U0(this);
            unityPlayerForActivityOrService.dismissSoftInput();
        }
    }
}
