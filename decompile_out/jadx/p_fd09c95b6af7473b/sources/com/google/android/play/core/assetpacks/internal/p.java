package com.google.android.play.core.assetpacks.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class p implements Runnable {
    private final TaskCompletionSource a;

    p() {
        this.a = null;
    }

    public p(TaskCompletionSource taskCompletionSource) {
        this.a = taskCompletionSource;
    }

    protected abstract void a();

    final TaskCompletionSource b() {
        return this.a;
    }

    public final void c(Exception exc) {
        TaskCompletionSource taskCompletionSource = this.a;
        if (taskCompletionSource != null) {
            taskCompletionSource.trySetException(exc);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            a();
        } catch (Exception e) {
            c(e);
        }
    }
}
