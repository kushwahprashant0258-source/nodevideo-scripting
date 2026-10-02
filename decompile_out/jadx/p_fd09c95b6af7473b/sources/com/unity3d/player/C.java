package com.unity3d.player;

import android.app.Activity;
import android.app.Dialog;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: loaded from: classes.dex */
final class C extends B {
    private OnBackInvokedCallback d;
    private OnBackInvokedDispatcher e;
    private int f;

    private C(OnBackInvokedDispatcher onBackInvokedDispatcher, int i, Runnable runnable) {
        super(runnable);
        this.d = null;
        this.f = i;
        this.e = onBackInvokedDispatcher;
    }

    public static B a(Object obj, int i, Runnable runnable) {
        B c = (PlatformSupport.TIRAMISU_SUPPORT && ((obj instanceof Activity) || (obj instanceof Dialog))) ? new C(AbstractC0005b.a(obj), i, runnable) : new B(runnable);
        c.registerOnBackPressedCallback();
        return c;
    }

    @Override // com.unity3d.player.B
    protected void registerOnBackPressedCallback() {
        if (this.a != null) {
            return;
        }
        super.registerOnBackPressedCallback();
        if (PlatformSupport.TIRAMISU_SUPPORT) {
            C0002a c0002a = new C0002a(this.a);
            this.d = c0002a;
            AbstractC0005b.a(this.e, this.f, c0002a);
        }
    }

    @Override // com.unity3d.player.B
    protected void unregisterOnBackPressedCallback() {
        if (this.a != null) {
            if (PlatformSupport.TIRAMISU_SUPPORT) {
                AbstractC0005b.a(this.e, this.d);
                this.d = null;
            }
            super.unregisterOnBackPressedCallback();
        }
    }
}
