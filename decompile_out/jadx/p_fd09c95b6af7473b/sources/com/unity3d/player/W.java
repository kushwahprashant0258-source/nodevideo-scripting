package com.unity3d.player;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
final class W extends S {
    private boolean i;
    private Handler j;
    private Runnable k;

    public W(Context context, UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        super(context, unityPlayerForActivityOrService);
        this.i = false;
    }

    @Override // com.unity3d.player.S
    public final void a(boolean z) {
        EditText editText;
        int i;
        this.e = z;
        if (z) {
            editText = this.c;
            i = 4;
        } else {
            editText = this.c;
            i = 0;
        }
        editText.setVisibility(i);
        this.c.invalidate();
        this.c.requestLayout();
    }

    @Override // com.unity3d.player.S
    public final void b() {
        Runnable runnable;
        Handler handler = this.j;
        if (handler != null && (runnable = this.k) != null) {
            handler.removeCallbacks(runnable);
        }
        this.b.getFrameLayout().removeView(this.c);
        this.i = false;
        invokeOnClose();
    }

    @Override // com.unity3d.player.S
    public final boolean c() {
        return false;
    }

    @Override // com.unity3d.player.S
    protected EditText createEditText(S s) {
        return new V(this.a, s);
    }

    @Override // com.unity3d.player.S
    public final void f() {
        if (this.i) {
            return;
        }
        FrameLayout frameLayout = this.b.getFrameLayout();
        frameLayout.addView(this.c);
        frameLayout.bringChildToFront(this.c);
        this.c.setVisibility(0);
        this.c.requestFocus();
        this.k = new U(this);
        Handler handler = new Handler(Looper.getMainLooper());
        this.j = handler;
        handler.postDelayed(this.k, 400L);
        this.i = true;
    }
}
