package com.unity3d.player;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: renamed from: com.unity3d.player.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class ViewOnCapturedPointerListenerC0051t0 implements View.OnCapturedPointerListener {
    final /* synthetic */ RunnableC0053u0 a;

    ViewOnCapturedPointerListenerC0051t0(RunnableC0053u0 runnableC0053u0) {
        this.a = runnableC0053u0;
    }

    @Override // android.view.View.OnCapturedPointerListener
    public final boolean onCapturedPointer(View view, MotionEvent motionEvent) {
        return this.a.a.a.getActivity().onTouchEvent(motionEvent);
    }
}
