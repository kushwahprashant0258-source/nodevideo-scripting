package com.unity3d.player;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
final class C0 extends Thread {
    private UnityPlayerForActivityOrService a;
    private Handler b;
    private int g;
    private int h;
    private boolean c = false;
    private boolean d = false;
    private int e = 2;
    private int f = 0;
    private int i = 5;

    C0(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    private void a(B0 b0) {
        Handler handler = this.b;
        if (handler != null) {
            Message.obtain(handler, 2269, b0).sendToTarget();
        }
    }

    public final void a() {
        a(B0.g);
    }

    public final void a(int i, int i2) {
        this.g = i;
        this.h = i2;
        a(B0.i);
    }

    public final void a(Runnable runnable) {
        if (this.b == null) {
            return;
        }
        a(B0.a);
        Message.obtain(this.b, runnable).sendToTarget();
    }

    public final void b() {
        a(B0.f);
    }

    public final void b(Runnable runnable) {
        Handler handler = this.b;
        if (handler == null) {
            return;
        }
        Message.obtain(handler, runnable).sendToTarget();
        a(B0.e);
    }

    public final void c() {
        a(B0.c);
    }

    public final void c(Runnable runnable) {
        Handler handler = this.b;
        if (handler != null) {
            Message.obtain(handler, runnable).sendToTarget();
        }
    }

    public final void d() {
        a(B0.b);
    }

    public final void d(Runnable runnable) {
        if (this.b == null) {
            return;
        }
        a(B0.d);
        Message.obtain(this.b, runnable).sendToTarget();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        setName("UnityMain");
        Looper.prepare();
        this.b = new Handler(Looper.myLooper(), new A0(this));
        Looper.loop();
    }
}
