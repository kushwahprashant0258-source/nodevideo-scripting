package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
final class I1 {
    private UnityPlayer a;
    private H1 c;
    private Context b = null;
    private final Semaphore d = new Semaphore(0);
    private final ReentrantLock e = new ReentrantLock();
    private A1 f = null;
    private int g = 2;
    private boolean h = false;
    private boolean i = false;

    I1(UnityPlayer unityPlayer) {
        this.a = null;
        this.a = unityPlayer;
    }

    public final void a() {
        this.e.lock();
        A1 a1 = this.f;
        if (a1 != null) {
            a1.updateVideoLayout();
        }
        this.e.unlock();
    }

    public final boolean a(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, H1 h1) {
        this.e.lock();
        this.c = h1;
        this.b = context;
        this.d.drainPermits();
        this.g = 2;
        runOnUiThread(new D1(this, str, i, i2, i3, z, j, j2));
        boolean z2 = false;
        try {
            this.e.unlock();
            this.d.acquire();
            this.e.lock();
            if (this.g != 2) {
                z2 = true;
            }
        } catch (InterruptedException unused) {
        }
        runOnUiThread(new E1(this));
        runOnUiThread((!z2 || this.g == 3) ? new G1(this) : new F1(this));
        this.e.unlock();
        return z2;
    }

    public final void b() {
        this.e.lock();
        A1 a1 = this.f;
        if (a1 != null) {
            if (this.g == 0) {
                a1.cancelOnPrepare();
            } else if (this.i) {
                boolean zA = a1.a();
                this.h = zA;
                if (!zA) {
                    this.f.pause();
                }
            }
        }
        this.e.unlock();
    }

    public final void c() {
        this.e.lock();
        A1 a1 = this.f;
        if (a1 != null && this.i && !this.h) {
            a1.start();
        }
        this.e.unlock();
    }

    protected void runOnUiThread(Runnable runnable) {
        Context context = this.b;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        } else {
            AbstractC0060y.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}
