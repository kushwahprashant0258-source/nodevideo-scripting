package com.unity3d.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.view.View;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
final class I extends View {
    Bitmap a;
    final /* synthetic */ J b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    I(J j, Context context) {
        super(context);
        this.b = j;
    }

    public final void a(SurfaceView surfaceView) {
        this.a = Bitmap.createBitmap(surfaceView.getWidth(), surfaceView.getHeight(), Bitmap.Config.ARGB_8888);
        HandlerThread handlerThread = new HandlerThread("PlaceHolderView");
        handlerThread.start();
        Semaphore semaphore = new Semaphore(0);
        PixelCopy.request(surfaceView, this.a, new H(this, semaphore), new Handler(handlerThread.getLooper()));
        try {
            if (!semaphore.tryAcquire(2000L, TimeUnit.MILLISECONDS)) {
                AbstractC0060y.Log(5, "Timeout while copying surface view.");
            }
        } catch (InterruptedException e) {
            AbstractC0060y.Log(6, e.getMessage());
        }
        handlerThread.quitSafely();
    }
}
