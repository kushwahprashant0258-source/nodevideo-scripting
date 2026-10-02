package com.unity3d.player;

import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
final class v1 implements Runnable {
    final /* synthetic */ UnityPlayerForGameActivity a;

    v1(UnityPlayerForGameActivity unityPlayerForGameActivity) {
        this.a = unityPlayerForGameActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityPlayerForGameActivity unityPlayerForGameActivity = this.a;
        J j = unityPlayerForGameActivity.m_PersistentUnitySurface;
        FrameLayout frameLayout = unityPlayerForGameActivity.getFrameLayout();
        I i = j.b;
        if (i != null && i.getParent() != null) {
            frameLayout.removeView(j.b);
        }
        this.a.m_PersistentUnitySurface.b = null;
    }
}
