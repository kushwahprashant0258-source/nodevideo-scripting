package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
public final class z1 implements Runnable {
    private A1 a;
    private boolean b = false;

    public z1(A1 a1) {
        this.a = a1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
        if (this.b) {
            return;
        }
        int i = A1.A;
        this.a.cancelOnPrepare();
    }
}
