package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzeg {
    private static final zzef zza;
    private static final zzef zzb;

    static {
        zzef zzefVar;
        try {
            zzefVar = (zzef) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzefVar = null;
        }
        zza = zzefVar;
        zzb = new zzef();
    }

    static zzef zza() {
        return zza;
    }

    static zzef zzb() {
        return zzb;
    }
}
