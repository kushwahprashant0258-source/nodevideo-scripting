package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzgu implements zzdh {
    static final zzdh zza = new zzgu();

    private zzgu() {
    }

    @Override // com.google.android.gms.internal.play_billing.zzdh
    public final boolean zza(int i) {
        zzgv zzgvVar;
        zzgv zzgvVar2 = zzgv.BROADCAST_ACTION_UNSPECIFIED;
        if (i == 0) {
            zzgvVar = zzgv.BROADCAST_ACTION_UNSPECIFIED;
        } else if (i == 1) {
            zzgvVar = zzgv.PURCHASES_UPDATED_ACTION;
        } else if (i != 2) {
            zzgvVar = i != 3 ? null : zzgv.ALTERNATIVE_BILLING_ACTION;
        } else {
            zzgvVar = zzgv.LOCAL_PURCHASES_UPDATED_ACTION;
        }
        return zzgvVar != null;
    }
}
