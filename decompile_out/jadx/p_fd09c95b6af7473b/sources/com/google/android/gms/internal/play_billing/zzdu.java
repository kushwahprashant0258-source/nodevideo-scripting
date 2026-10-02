package com.google.android.gms.internal.play_billing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzdu extends zzdy {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzdu() {
        super(null);
    }

    /* synthetic */ zzdu(zzdt zzdtVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdy
    final void zza(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzfw.zzf(obj, j);
        if (list instanceof zzds) {
            objUnmodifiableList = ((zzds) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzer) && (list instanceof zzdk)) {
                zzdk zzdkVar = (zzdk) list;
                if (zzdkVar.zzc()) {
                    zzdkVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzfw.zzs(obj, j, objUnmodifiableList);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdy
    final void zzb(Object obj, Object obj2, long j) {
        List list;
        List list2;
        List listZzd;
        List list3 = (List) zzfw.zzf(obj2, j);
        int size = list3.size();
        List list4 = (List) zzfw.zzf(obj, j);
        if (list4.isEmpty()) {
            if (list4 instanceof zzds) {
                listZzd = new zzdr(size);
            } else {
                listZzd = ((list4 instanceof zzer) && (list4 instanceof zzdk)) ? ((zzdk) list4).zzd(size) : new ArrayList(size);
            }
            zzfw.zzs(obj, j, listZzd);
            list2 = listZzd;
        } else {
            if (zza.isAssignableFrom(list4.getClass())) {
                ArrayList arrayList = new ArrayList(list4.size() + size);
                arrayList.addAll(list4);
                zzfw.zzs(obj, j, arrayList);
                list = arrayList;
            } else if (list4 instanceof zzfr) {
                zzdr zzdrVar = new zzdr(list4.size() + size);
                zzdrVar.addAll(zzdrVar.size(), (zzfr) list4);
                zzfw.zzs(obj, j, zzdrVar);
                list = zzdrVar;
            } else if ((list4 instanceof zzer) && (list4 instanceof zzdk)) {
                zzdk zzdkVar = (zzdk) list4;
                if (!zzdkVar.zzc()) {
                    list2 = list4;
                    list2 = list4;
                    list2 = list4;
                    zzdk zzdkVarZzd = zzdkVar.zzd(list4.size() + size);
                    zzfw.zzs(obj, j, zzdkVarZzd);
                    list2 = zzdkVarZzd;
                }
            }
            list2 = list;
        }
        list2 = list4;
        list2 = list4;
        list2 = list4;
        list2 = list4;
        list2 = list4;
        list2 = list4;
        int size2 = list2.size();
        int size3 = list3.size();
        if (size2 > 0 && size3 > 0) {
            list2.addAll(list3);
        }
        if (size2 > 0) {
            list3 = list2;
        }
        zzfw.zzs(obj, j, list3);
    }
}
