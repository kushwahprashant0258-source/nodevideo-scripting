package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
class B {
    protected Runnable b;
    protected com.unity3d.player.a.e a = null;
    protected boolean c = true;

    protected B(Runnable runnable) {
        this.b = runnable;
    }

    protected void registerOnBackPressedCallback() {
        if (this.a != null) {
            return;
        }
        this.a = new A(this.b);
    }

    protected void unregisterOnBackPressedCallback() {
        this.a = null;
    }
}
