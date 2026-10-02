package com.google.android.gms.internal.play_billing;

import androidx.core.internal.view.SupportMenu;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzco {
    private final Object zza;
    private final int zzb;

    zzco(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzco)) {
            return false;
        }
        zzco zzcoVar = (zzco) obj;
        return this.zza == zzcoVar.zza && this.zzb == zzcoVar.zzb;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * SupportMenu.USER_MASK) + this.zzb;
    }
}
