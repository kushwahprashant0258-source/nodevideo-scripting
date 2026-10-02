package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class aw implements y {
    private static final com.google.android.play.core.assetpacks.internal.o a = new com.google.android.play.core.assetpacks.internal.o("AssetPackServiceImpl");
    private static final Intent b = new Intent("com.google.android.play.core.assetmoduleservice.BIND_ASSET_MODULE_SERVICE").setPackage("com.android.vending");
    private final String c;
    private final co d;
    private final eb e;
    private com.google.android.play.core.assetpacks.internal.z f;
    private com.google.android.play.core.assetpacks.internal.z g;
    private final AtomicBoolean h = new AtomicBoolean();

    aw(Context context, co coVar, eb ebVar) {
        this.c = context.getPackageName();
        this.d = coVar;
        this.e = ebVar;
        if (com.google.android.play.core.assetpacks.internal.ai.b(context)) {
            Context contextA = com.google.android.play.core.assetpacks.internal.ag.a(context);
            com.google.android.play.core.assetpacks.internal.o oVar = a;
            Intent intent = b;
            this.f = new com.google.android.play.core.assetpacks.internal.z(contextA, oVar, "AssetPackService", intent, z.a, null);
            this.g = new com.google.android.play.core.assetpacks.internal.z(com.google.android.play.core.assetpacks.internal.ag.a(context), oVar, "AssetPackService-keepAlive", intent, z.a, null);
        }
        a.a("AssetPackService initiated.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle A() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 11004);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        bundle.putIntegerArrayList("supported_compression_formats", arrayList);
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(1);
        arrayList2.add(2);
        bundle.putIntegerArrayList("supported_patch_formats", arrayList2);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle B(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", i);
        return bundle;
    }

    private static Task C() {
        a.b("onError(%d)", -11);
        return Tasks.forException(new AssetPackException(-11));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void D(int i, String str, int i2) {
        if (this.f == null) {
            throw new ck("The Play Store app is not installed or is an unofficial version.", i);
        }
        a.d("notifyModuleCompleted", new Object[0]);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f.s(new ah(this, taskCompletionSource, i, str, taskCompletionSource, i2), taskCompletionSource);
    }

    static /* bridge */ /* synthetic */ Bundle k(int i, String str, String str2, int i2) {
        Bundle bundleZ = z(i, str);
        bundleZ.putString("slice_id", str2);
        bundleZ.putInt("chunk_number", i2);
        return bundleZ;
    }

    static /* bridge */ /* synthetic */ Bundle n(Map map) {
        Bundle bundleA = A();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            Bundle bundle = new Bundle();
            bundle.putString("installed_asset_module_name", (String) entry.getKey());
            bundle.putLong("installed_asset_module_version", ((Long) entry.getValue()).longValue());
            arrayList.add(bundle);
        }
        bundleA.putParcelableArrayList("installed_asset_module", arrayList);
        return bundleA;
    }

    static /* bridge */ /* synthetic */ ArrayList v(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Bundle bundle = new Bundle();
            bundle.putString("module_name", str);
            arrayList.add(bundle);
        }
        return arrayList;
    }

    static /* bridge */ /* synthetic */ List w(aw awVar, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AssetPackState next = AssetPackStates.a((Bundle) it.next(), awVar.d, awVar.e, bf.a).packStates().values().iterator().next();
            if (next == null) {
                a.b("onGetSessionStates: Bundle contained no pack.", new Object[0]);
            }
            if (bg.a(next.status())) {
                arrayList.add(next.name());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle z(int i, String str) {
        Bundle bundleB = B(i);
        bundleB.putString("module_name", str);
        return bundleB;
    }

    @Override // com.google.android.play.core.assetpacks.y
    public final Task a(int i, String str, String str2, int i2) {
        if (this.f == null) {
            return C();
        }
        a.d("getChunkFileDescriptor(%s, %s, %d, session=%d)", str, str2, Integer.valueOf(i2), Integer.valueOf(i));
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f.s(new aj(this, taskCompletionSource, i, str, str2, i2, taskCompletionSource), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    @Override // com.google.android.play.core.assetpacks.y
    public final Task b(List list, be beVar, Map map) {
        if (this.f == null) {
            return C();
        }
        a.d("getPackStates(%s)", list);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f.s(new af(this, taskCompletionSource, list, map, taskCompletionSource, beVar), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    @Override // com.google.android.play.core.assetpacks.y
    public final Task c(List list, Map map) {
        if (this.f == null) {
            return C();
        }
        a.d("startDownload(%s)", list);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f.s(new ac(this, taskCompletionSource, list, map, taskCompletionSource), taskCompletionSource);
        taskCompletionSource.getTask().addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.play.core.assetpacks.aa
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.a.f();
            }
        });
        return taskCompletionSource.getTask();
    }

    @Override // com.google.android.play.core.assetpacks.y
    public final Task d(Map map) {
        if (this.f == null) {
            return C();
        }
        a.d("syncPacks", new Object[0]);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f.s(new ae(this, taskCompletionSource, map, taskCompletionSource), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    @Override // com.google.android.play.core.assetpacks.y
    public final void e(List list) {
        if (this.f == null) {
            return;
        }
        a.d("cancelDownloads(%s)", list);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f.s(new ad(this, taskCompletionSource, list, taskCompletionSource), taskCompletionSource);
    }

    @Override // com.google.android.play.core.assetpacks.y
    public final synchronized void f() {
        if (this.g == null) {
            a.e("Keep alive connection manager is not initialized.", new Object[0]);
            return;
        }
        com.google.android.play.core.assetpacks.internal.o oVar = a;
        oVar.d("keepAlive", new Object[0]);
        if (!this.h.compareAndSet(false, true)) {
            oVar.d("Service is already kept alive.", new Object[0]);
        } else {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            this.g.s(new ak(this, taskCompletionSource, taskCompletionSource), taskCompletionSource);
        }
    }

    @Override // com.google.android.play.core.assetpacks.y
    public final void g(int i, String str, String str2, int i2) {
        if (this.f == null) {
            throw new ck("The Play Store app is not installed or is an unofficial version.", i);
        }
        a.d("notifyChunkTransferred", new Object[0]);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f.s(new ag(this, taskCompletionSource, i, str, str2, i2, taskCompletionSource), taskCompletionSource);
    }

    @Override // com.google.android.play.core.assetpacks.y
    public final void h(int i, String str) {
        D(i, str, 10);
    }

    @Override // com.google.android.play.core.assetpacks.y
    public final void i(int i) {
        if (this.f == null) {
            throw new ck("The Play Store app is not installed or is an unofficial version.", i);
        }
        a.d("notifySessionFailed", new Object[0]);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f.s(new ai(this, taskCompletionSource, i, taskCompletionSource), taskCompletionSource);
    }

    @Override // com.google.android.play.core.assetpacks.y
    public final void j(String str) {
        if (this.f == null) {
            return;
        }
        a.d("removePack(%s)", str);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f.s(new ab(this, taskCompletionSource, str, taskCompletionSource), taskCompletionSource);
    }
}
