package com.google.android.play.core.review.internal;

import android.os.IBinder;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.play:review@@2.0.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzp extends zzj {
    final /* synthetic */ IBinder zza;
    final /* synthetic */ zzr zzb;

    zzp(zzr zzrVar, IBinder iBinder) {
        this.zza = iBinder;
        this.zzb = zzrVar;
    }

    @Override // com.google.android.play.core.review.internal.zzj
    public final void zza() {
        this.zzb.zza.zzn = zze.zzb(this.zza);
        zzt.zzq(this.zzb.zza);
        this.zzb.zza.zzh = false;
        Iterator it = this.zzb.zza.zze.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.zzb.zza.zze.clear();
    }
}
