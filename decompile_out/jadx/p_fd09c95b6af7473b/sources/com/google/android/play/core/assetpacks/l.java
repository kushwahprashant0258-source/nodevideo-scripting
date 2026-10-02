package com.google.android.play.core.assetpacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class l implements AssetPackManager {
    private static final com.google.android.play.core.assetpacks.internal.o a = new com.google.android.play.core.assetpacks.internal.o("AssetPackManager");
    private final bh b;
    private final bb c;
    private final com.google.android.play.core.assetpacks.internal.ad d;
    private final de e;
    private final co f;
    private final bx g;
    private final eb h;
    private final Handler i = new Handler(Looper.getMainLooper());
    private boolean j;
    private final com.google.android.play.core.assetpacks.internal.aq k;
    private final com.google.android.play.core.assetpacks.internal.aq l;

    l(bh bhVar, com.google.android.play.core.assetpacks.internal.aq aqVar, bb bbVar, com.google.android.play.core.assetpacks.internal.ad adVar, de deVar, co coVar, bx bxVar, com.google.android.play.core.assetpacks.internal.aq aqVar2, eb ebVar) {
        this.b = bhVar;
        this.k = aqVar;
        this.c = bbVar;
        this.d = adVar;
        this.e = deVar;
        this.f = coVar;
        this.g = bxVar;
        this.l = aqVar2;
        this.h = ebVar;
    }

    private final void h() {
        ((Executor) this.l.a()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.i
            @Override // java.lang.Runnable
            public final void run() {
                this.a.f();
            }
        });
    }

    final int a(int i, String str) {
        if (!this.b.G(str) && i == 4) {
            return 8;
        }
        if (!this.b.G(str) || i == 4) {
            return i;
        }
        return 4;
    }

    final /* synthetic */ void c() {
        this.b.y();
        this.b.w();
        this.b.x();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final AssetPackStates cancel(List<String> list) {
        Map mapF = this.e.f(list);
        HashMap map = new HashMap();
        for (String str : list) {
            Integer num = (Integer) mapF.get(str);
            map.put(str, AssetPackState.a(str, num == null ? 0 : num.intValue(), 0, 0L, 0L, 0.0d, 0, "", ""));
        }
        ((y) this.k.a()).e(list);
        return new bo(0L, map);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final void clearListeners() {
        this.c.e();
    }

    final /* synthetic */ void d(String str, TaskCompletionSource taskCompletionSource) {
        if (!this.b.D(str)) {
            taskCompletionSource.setException(new IOException(String.format("Failed to remove pack %s.", str)));
        } else {
            taskCompletionSource.setResult(null);
            ((y) this.k.a()).j(str);
        }
    }

    final /* synthetic */ void f() {
        Task taskD = ((y) this.k.a()).d(this.b.u());
        Executor executor = (Executor) this.l.a();
        final bh bhVar = this.b;
        bhVar.getClass();
        taskD.addOnSuccessListener(executor, new OnSuccessListener() { // from class: com.google.android.play.core.assetpacks.g
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                bhVar.C((List) obj);
            }
        }).addOnFailureListener((Executor) this.l.a(), new OnFailureListener() { // from class: com.google.android.play.core.assetpacks.h
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                l.a.e(String.format("Could not sync active asset packs. %s", exc), new Object[0]);
            }
        });
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<AssetPackStates> fetch(List<String> list) {
        Map mapU = this.b.u();
        ArrayList arrayList = new ArrayList(list);
        if (!arrayList.isEmpty()) {
            return ((y) this.k.a()).c(arrayList, mapU);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", 0);
        bundle.putInt("error_code", 0);
        for (String str : list) {
            bundle.putInt(com.google.android.play.core.assetpacks.model.b.a(NotificationCompat.CATEGORY_STATUS, str), 4);
            bundle.putInt(com.google.android.play.core.assetpacks.model.b.a("error_code", str), 0);
            bundle.putLong(com.google.android.play.core.assetpacks.model.b.a("total_bytes_to_download", str), 0L);
            bundle.putLong(com.google.android.play.core.assetpacks.model.b.a("bytes_downloaded", str), 0L);
        }
        bundle.putStringArrayList("pack_names", new ArrayList<>(list));
        bundle.putLong("total_bytes_to_download", 0L);
        bundle.putLong("bytes_downloaded", 0L);
        return Tasks.forResult(AssetPackStates.a(bundle, this.f, this.h, bf.a));
    }

    final void g(boolean z) {
        boolean zJ = this.c.j();
        this.c.g(z);
        if (!z || zJ) {
            return;
        }
        h();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final AssetLocation getAssetLocation(String str, String str2) {
        AssetPackLocation assetPackLocationF;
        if (!this.j) {
            ((Executor) this.l.a()).execute(new j(this));
            this.j = true;
        }
        if (this.b.G(str)) {
            try {
                assetPackLocationF = this.b.f(str);
            } catch (IOException unused) {
            }
        } else {
            assetPackLocationF = this.d.a().contains(str) ? AssetPackLocation.a() : null;
        }
        if (assetPackLocationF == null) {
            return null;
        }
        if (assetPackLocationF.packStorageMethod() == 1) {
            bh bhVar = this.b;
            return bhVar.d(str, str2, bhVar.s(str));
        }
        if (assetPackLocationF.packStorageMethod() == 0) {
            return this.b.e(str, str2, assetPackLocationF);
        }
        a.a("The asset %s is not present in Asset Pack %s", str2, str);
        return null;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final AssetPackLocation getPackLocation(String str) {
        if (!this.j) {
            ((Executor) this.l.a()).execute(new j(this));
            this.j = true;
        }
        if (this.b.G(str)) {
            try {
                return this.b.f(str);
            } catch (IOException unused) {
                return null;
            }
        }
        if (this.d.a().contains(str)) {
            return AssetPackLocation.a();
        }
        return null;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Map<String, AssetPackLocation> getPackLocations() {
        Map<String, AssetPackLocation> mapV = this.b.v();
        HashMap map = new HashMap();
        Iterator it = this.d.a().iterator();
        while (it.hasNext()) {
            map.put((String) it.next(), AssetPackLocation.a());
        }
        mapV.putAll(map);
        return mapV;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<AssetPackStates> getPackStates(List<String> list) {
        return ((y) this.k.a()).b(list, new f(this), this.b.u());
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final synchronized void registerListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        boolean zJ = this.c.j();
        this.c.f(assetPackStateUpdateListener);
        if (zJ) {
            return;
        }
        h();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<Void> removePack(final String str) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        ((Executor) this.l.a()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.e
            @Override // java.lang.Runnable
            public final void run() {
                this.a.d(str, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<Integer> showCellularDataConfirmation(Activity activity) {
        if (activity == null) {
            return Tasks.forException(new AssetPackException(-3));
        }
        if (this.g.a() == null) {
            return Tasks.forException(new AssetPackException(-12));
        }
        Intent intent = new Intent(activity, (Class<?>) PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", this.g.a());
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        intent.putExtra("result_receiver", new k(this, this.i, taskCompletionSource));
        activity.startActivity(intent);
        return taskCompletionSource.getTask();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final void unregisterListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        this.c.h(assetPackStateUpdateListener);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final boolean showCellularDataConfirmation(ActivityResultLauncher<IntentSenderRequest> activityResultLauncher) {
        if (activityResultLauncher == null || this.g.a() == null) {
            return false;
        }
        activityResultLauncher.launch(new IntentSenderRequest.Builder(this.g.a().getIntentSender()).build());
        return true;
    }
}
