package com.google.android.play.core.assetpacks;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class ci implements ServiceConnection {
    private final com.google.android.play.core.assetpacks.internal.o a = new com.google.android.play.core.assetpacks.internal.o("ExtractionForegroundServiceConnection");
    private final List b = new ArrayList();
    private final Context c;
    private ExtractionForegroundService d;
    private Notification e;

    ci(Context context) {
        this.c = context;
    }

    private final void d() {
        ArrayList arrayList;
        synchronized (this.b) {
            arrayList = new ArrayList(this.b);
            this.b.clear();
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            try {
                ((com.google.android.play.core.assetpacks.internal.k) arrayList.get(i)).e(new Bundle(), new Bundle());
            } catch (RemoteException unused) {
                this.a.b("Could not resolve Play Store service state update callback.", new Object[0]);
            }
        }
    }

    final void a(Notification notification) {
        this.e = notification;
    }

    final void b() {
        this.a.a("Stopping foreground installation service.", new Object[0]);
        this.c.unbindService(this);
        ExtractionForegroundService extractionForegroundService = this.d;
        if (extractionForegroundService != null) {
            extractionForegroundService.a();
        }
        d();
    }

    final void c(com.google.android.play.core.assetpacks.internal.k kVar) {
        synchronized (this.b) {
            this.b.add(kVar);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.a.a("Starting foreground installation service.", new Object[0]);
        ExtractionForegroundService extractionForegroundService = ((ch) iBinder).a;
        this.d = extractionForegroundService;
        extractionForegroundService.startForeground(-1883842196, this.e);
        d();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
