package com.unity3d.player;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
final class A0 implements Handler.Callback {
    final /* synthetic */ C0 a;

    A0(C0 c0) {
        this.a = c0;
    }

    /* JADX WARN: Code duplicated, block: B:63:0x0123  */
    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 2269) {
            return false;
        }
        B0 b0 = (B0) message.obj;
        B0 b1 = B0.h;
        if (b0 == b1) {
            C0 c0 = this.a;
            c0.f--;
            c0.a.executeMainThreadJobs();
            C0 c1 = this.a;
            if (!c1.c) {
                return true;
            }
            if (c1.a.getHaveAndroidWindowSupport() && !this.a.d) {
                return true;
            }
            C0 c2 = this.a;
            int i = c2.i;
            if (i >= 0) {
                if (i == 0) {
                    if (c2.a.getSplashEnabled()) {
                        this.a.a.disableStaticSplashScreen();
                    }
                    if (this.a.a.shouldReportFullyDrawn()) {
                        this.a.a.reportFullyDrawn();
                    }
                }
                this.a.i--;
            }
            if (!this.a.a.isFinishing() && !this.a.a.nativeRender()) {
                this.a.a.finish();
            }
        } else if (b0 == B0.c) {
            Looper.myLooper().quit();
        } else if (b0 == B0.b) {
            this.a.c = true;
        } else if (b0 == B0.a) {
            this.a.c = false;
        } else if (b0 == B0.d) {
            this.a.d = false;
        } else if (b0 == B0.e) {
            C0 c3 = this.a;
            c3.d = true;
            if (c3.e == 3 && (!c3.a.getHaveAndroidWindowSupport() || this.a.d)) {
                this.a.a.nativeFocusChanged(true);
                this.a.e = 1;
            }
        } else if (b0 == B0.f) {
            C0 c4 = this.a;
            if (c4.e == 1) {
                c4.a.nativeFocusChanged(false);
            }
            this.a.e = 2;
        } else if (b0 == B0.g) {
            C0 c5 = this.a;
            c5.e = 3;
            if (!c5.a.getHaveAndroidWindowSupport() || this.a.d) {
                this.a.a.nativeFocusChanged(true);
                this.a.e = 1;
            }
        } else if (b0 == B0.i) {
            C0 c6 = this.a;
            c6.a.nativeOrientationChanged(c6.g, c6.h);
        }
        C0 c7 = this.a;
        if (c7.c && c7.f <= 0) {
            Message.obtain(c7.b, 2269, b1).sendToTarget();
            this.a.f++;
        }
        return true;
    }
}
