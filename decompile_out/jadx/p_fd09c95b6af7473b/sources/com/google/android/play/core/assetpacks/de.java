package com.google.android.play.core.assetpacks;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class de {
    private static final com.google.android.play.core.assetpacks.internal.o a = new com.google.android.play.core.assetpacks.internal.o("ExtractorSessionStoreView");
    private final bh b;
    private final co c;
    private final Map d = new HashMap();
    private final ReentrantLock e = new ReentrantLock();
    private final com.google.android.play.core.assetpacks.internal.aq f;
    private final com.google.android.play.core.assetpacks.internal.aq g;

    de(bh bhVar, com.google.android.play.core.assetpacks.internal.aq aqVar, co coVar, com.google.android.play.core.assetpacks.internal.aq aqVar2) {
        this.b = bhVar;
        this.f = aqVar;
        this.c = coVar;
        this.g = aqVar2;
    }

    private final db q(int i) {
        Map map = this.d;
        Integer numValueOf = Integer.valueOf(i);
        db dbVar = (db) map.get(numValueOf);
        if (dbVar != null) {
            return dbVar;
        }
        throw new ck(String.format("Could not find session %d while trying to get it", numValueOf), i);
    }

    private final Object r(dd ddVar) {
        try {
            this.e.lock();
            return ddVar.a();
        } finally {
            this.e.unlock();
        }
    }

    private static String s(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        if (stringArrayList == null || stringArrayList.isEmpty()) {
            throw new ck("Session without pack received.");
        }
        return stringArrayList.get(0);
    }

    private static List t(List list) {
        return list == null ? Collections.emptyList() : list;
    }

    private final Map u(final List list) {
        return (Map) r(new dd() { // from class: com.google.android.play.core.assetpacks.cr
            @Override // com.google.android.play.core.assetpacks.dd
            public final Object a() {
                return this.a.i(list);
            }
        });
    }

    final /* synthetic */ Boolean a(Bundle bundle) {
        int i = bundle.getInt("session_id");
        if (i == 0) {
            return true;
        }
        Map map = this.d;
        Integer numValueOf = Integer.valueOf(i);
        if (!map.containsKey(numValueOf)) {
            return true;
        }
        db dbVar = (db) this.d.get(numValueOf);
        if (dbVar.c.d == 6) {
            return false;
        }
        return Boolean.valueOf(!bg.c(dbVar.c.d, bundle.getInt(com.google.android.play.core.assetpacks.model.b.a(NotificationCompat.CATEGORY_STATUS, s(bundle)))));
    }

    final /* synthetic */ Boolean b(Bundle bundle) {
        boolean z;
        boolean z2;
        dc dcVar;
        int i = bundle.getInt("session_id");
        boolean z3 = false;
        if (i == 0) {
            return false;
        }
        Map map = this.d;
        Integer numValueOf = Integer.valueOf(i);
        boolean z4 = true;
        if (map.containsKey(numValueOf)) {
            db dbVarQ = q(i);
            int i2 = bundle.getInt(com.google.android.play.core.assetpacks.model.b.a(NotificationCompat.CATEGORY_STATUS, dbVarQ.c.a));
            da daVar = dbVarQ.c;
            int i3 = daVar.d;
            if (bg.c(i3, i2)) {
                a.a("Found stale update for session %s with status %d.", numValueOf, Integer.valueOf(i3));
                da daVar2 = dbVarQ.c;
                String str = daVar2.a;
                int i4 = daVar2.d;
                if (i4 == 4) {
                    ((y) this.f.a()).h(i, str);
                } else if (i4 == 5) {
                    ((y) this.f.a()).i(i);
                } else if (i4 == 6) {
                    ((y) this.f.a()).e(Arrays.asList(str));
                }
            } else {
                daVar.d = i2;
                if (bg.d(i2)) {
                    n(i);
                    this.c.c(dbVarQ.c.a);
                } else {
                    for (dc dcVar2 : daVar.f) {
                        ArrayList parcelableArrayList = bundle.getParcelableArrayList(com.google.android.play.core.assetpacks.model.b.b("chunk_intents", dbVarQ.c.a, dcVar2.a));
                        if (parcelableArrayList != null) {
                            for (int i5 = 0; i5 < parcelableArrayList.size(); i5++) {
                                if (parcelableArrayList.get(i5) != null && ((Intent) parcelableArrayList.get(i5)).getData() != null) {
                                    ((cz) dcVar2.d.get(i5)).a = true;
                                }
                            }
                        }
                    }
                }
            }
            z = true;
        } else {
            String strS = s(bundle);
            long j = bundle.getLong(com.google.android.play.core.assetpacks.model.b.a("pack_version", strS));
            String string = bundle.getString(com.google.android.play.core.assetpacks.model.b.a("pack_version_tag", strS), "");
            int i6 = bundle.getInt(com.google.android.play.core.assetpacks.model.b.a(NotificationCompat.CATEGORY_STATUS, strS));
            long j2 = bundle.getLong(com.google.android.play.core.assetpacks.model.b.a("total_bytes_to_download", strS));
            ArrayList<String> stringArrayList = bundle.getStringArrayList(com.google.android.play.core.assetpacks.model.b.a("slice_ids", strS));
            ArrayList arrayList = new ArrayList();
            for (String str2 : t(stringArrayList)) {
                ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(com.google.android.play.core.assetpacks.model.b.b("chunk_intents", strS, str2));
                ArrayList arrayList2 = new ArrayList();
                Iterator it = t(parcelableArrayList2).iterator();
                while (it.hasNext()) {
                    if (((Intent) it.next()) == null) {
                        z4 = z3;
                    }
                    arrayList2.add(new cz(z4));
                    z3 = false;
                    z4 = true;
                }
                String string2 = bundle.getString(com.google.android.play.core.assetpacks.model.b.b("uncompressed_hash_sha256", strS, str2));
                long j3 = bundle.getLong(com.google.android.play.core.assetpacks.model.b.b("uncompressed_size", strS, str2));
                int i7 = bundle.getInt(com.google.android.play.core.assetpacks.model.b.b("patch_format", strS, str2), 0);
                if (i7 != 0) {
                    dcVar = new dc(str2, string2, j3, arrayList2, 0, i7);
                    z2 = false;
                } else {
                    z2 = false;
                    dcVar = new dc(str2, string2, j3, arrayList2, bundle.getInt(com.google.android.play.core.assetpacks.model.b.b("compression_format", strS, str2), 0), 0);
                }
                arrayList.add(dcVar);
                z3 = z2;
                z4 = true;
            }
            this.d.put(Integer.valueOf(i), new db(i, bundle.getInt("app_version_code"), new da(strS, j, i6, j2, arrayList, string)));
            z = true;
        }
        return Boolean.valueOf(z);
    }

    final /* synthetic */ Object c(String str, int i, long j) {
        db dbVar = (db) u(Arrays.asList(str)).get(str);
        if (dbVar == null || bg.d(dbVar.c.d)) {
            a.b(String.format("Could not find pack %s while trying to complete it", str), new Object[0]);
        }
        this.b.E(str, i, j);
        dbVar.c.d = 4;
        return null;
    }

    final /* synthetic */ Object d(int i, int i2) {
        q(i).c.d = 5;
        return null;
    }

    final /* synthetic */ Object e(int i) {
        db dbVarQ = q(i);
        da daVar = dbVarQ.c;
        if (!bg.d(daVar.d)) {
            throw new ck(String.format("Could not safely delete session %d because it is not in a terminal state.", Integer.valueOf(i)), i);
        }
        this.b.E(daVar.a, dbVarQ.b, daVar.b);
        da daVar2 = dbVarQ.c;
        int i2 = daVar2.d;
        if (i2 != 5 && i2 != 6) {
            return null;
        }
        this.b.F(daVar2.a, dbVarQ.b, daVar2.b);
        return null;
    }

    final Map f(final List list) {
        return (Map) r(new dd() { // from class: com.google.android.play.core.assetpacks.cv
            @Override // com.google.android.play.core.assetpacks.dd
            public final Object a() {
                return this.a.h(list);
            }
        });
    }

    final Map g() {
        return this.d;
    }

    final /* synthetic */ Map h(List list) {
        Map mapU = u(list);
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            final db dbVar = (db) mapU.get(str);
            if (dbVar == null) {
                map.put(str, 8);
            } else {
                da daVar = dbVar.c;
                if (bg.a(daVar.d)) {
                    try {
                        daVar.d = 6;
                        ((Executor) this.g.a()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.cy
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.a.n(dbVar.a);
                            }
                        });
                        this.c.c(str);
                    } catch (ck unused) {
                        a.d("Session %d with pack %s does not exist, no need to cancel.", Integer.valueOf(dbVar.a), str);
                    }
                }
                map.put(str, Integer.valueOf(dbVar.c.d));
            }
        }
        return map;
    }

    final /* synthetic */ Map i(List list) {
        HashMap map = new HashMap();
        for (db dbVar : this.d.values()) {
            String str = dbVar.c.a;
            if (list.contains(str)) {
                db dbVar2 = (db) map.get(str);
                if ((dbVar2 == null ? -1 : dbVar2.a) < dbVar.a) {
                    map.put(str, dbVar);
                }
            }
        }
        return map;
    }

    final void j() {
        this.e.lock();
    }

    final void k(final String str, final int i, final long j) {
        r(new dd() { // from class: com.google.android.play.core.assetpacks.cu
            @Override // com.google.android.play.core.assetpacks.dd
            public final Object a() {
                this.a.c(str, i, j);
                return null;
            }
        });
    }

    final void l() {
        this.e.unlock();
    }

    final void m(final int i, int i2) {
        final int i3 = 5;
        r(new dd(i, i3) { // from class: com.google.android.play.core.assetpacks.cx
            public final /* synthetic */ int b;

            @Override // com.google.android.play.core.assetpacks.dd
            public final Object a() {
                this.a.d(this.b, 5);
                return null;
            }
        });
    }

    final void n(final int i) {
        r(new dd() { // from class: com.google.android.play.core.assetpacks.cs
            @Override // com.google.android.play.core.assetpacks.dd
            public final Object a() {
                this.a.e(i);
                return null;
            }
        });
    }

    final boolean o(final Bundle bundle) {
        return ((Boolean) r(new dd() { // from class: com.google.android.play.core.assetpacks.ct
            @Override // com.google.android.play.core.assetpacks.dd
            public final Object a() {
                return this.a.a(bundle);
            }
        })).booleanValue();
    }

    final boolean p(final Bundle bundle) {
        return ((Boolean) r(new dd() { // from class: com.google.android.play.core.assetpacks.cw
            @Override // com.google.android.play.core.assetpacks.dd
            public final Object a() {
                return this.a.b(bundle);
            }
        })).booleanValue();
    }
}
