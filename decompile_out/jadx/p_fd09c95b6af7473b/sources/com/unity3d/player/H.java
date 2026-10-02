package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.view.PixelCopy;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
final class H implements PixelCopy.OnPixelCopyFinishedListener {
    final /* synthetic */ Semaphore a;
    final /* synthetic */ I b;

    H(I i, Semaphore semaphore) {
        this.b = i;
        this.a = semaphore;
    }

    @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
    public final void onPixelCopyFinished(int i) {
        this.a.release();
        if (i == 0) {
            Context context = this.b.b.a;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new G(this));
            }
        }
    }
}
