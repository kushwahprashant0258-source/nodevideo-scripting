package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class bb extends com.google.android.play.core.assetpacks.internal.n {
    private final de c;
    private final cl d;
    private final bx e;
    private final co f;
    private final eb g;
    private final Handler h;
    private final com.google.android.play.core.assetpacks.internal.aq i;
    private final com.google.android.play.core.assetpacks.internal.aq j;
    private final com.google.android.play.core.assetpacks.internal.aq k;

    bb(Context context, de deVar, cl clVar, com.google.android.play.core.assetpacks.internal.aq aqVar, co coVar, bx bxVar, com.google.android.play.core.assetpacks.internal.aq aqVar2, com.google.android.play.core.assetpacks.internal.aq aqVar3, eb ebVar) {
        super(new com.google.android.play.core.assetpacks.internal.o("AssetPackServiceListenerRegistry"), new IntentFilter("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE"), context);
        this.h = new Handler(Looper.getMainLooper());
        this.c = deVar;
        this.d = clVar;
        this.i = aqVar;
        this.f = coVar;
        this.e = bxVar;
        this.j = aqVar2;
        this.k = aqVar3;
        this.g = ebVar;
    }

    final /* synthetic */ void a(Bundle bundle) {
        if (this.c.p(bundle)) {
            this.d.a();
        }
    }

    final /* synthetic */ void b(Bundle bundle, AssetPackState assetPackState) {
        if (this.c.o(bundle)) {
            d(assetPackState);
            ((y) this.i.a()).f();
        }
    }

    @Override // com.google.android.play.core.assetpacks.internal.n
    protected final void c(Context context, Intent intent) {
        final Bundle bundleExtra = intent.getBundleExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE");
        if (bundleExtra == null) {
            this.a.b("Empty bundle received from broadcast.", new Object[0]);
            return;
        }
        ArrayList<String> stringArrayList = bundleExtra.getStringArrayList("pack_names");
        if (stringArrayList == null || stringArrayList.size() != 1) {
            this.a.b("Corrupt bundle received from broadcast.", new Object[0]);
            return;
        }
        final AssetPackState assetPackStateB = AssetPackState.b(bundleExtra, stringArrayList.get(0), this.f, this.g, new be() { // from class: com.google.android.play.core.assetpacks.bd
            @Override // com.google.android.play.core.assetpacks.be
            public final int a(int i, String str) {
                return i;
            }
        });
        this.a.a("ListenerRegistryBroadcastReceiver.onReceive: %s", assetPackStateB);
        PendingIntent pendingIntent = (PendingIntent) bundleExtra.getParcelable("confirmation_intent");
        if (pendingIntent != null) {
            this.e.b(pendingIntent);
        }
        ((Executor) this.k.a()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.az
            @Override // java.lang.Runnable
            public final void run() {
                this.a.b(bundleExtra, assetPackStateB);
            }
        });
        ((Executor) this.j.a()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.ay
            @Override // java.lang.Runnable
            public final void run() {
                this.a.a(bundleExtra);
            }
        });
    }

    final void d(final AssetPackState assetPackState) {
        this.h.post(new Runnable() { // from class: com.google.android.play.core.assetpacks.ba
            @Override // java.lang.Runnable
            public final void run() {
                this.a.i(assetPackState);
            }
        });
    }
}
