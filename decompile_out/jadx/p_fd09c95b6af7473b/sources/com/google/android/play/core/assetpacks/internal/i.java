package com.google.android.play.core.assetpacks.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class i extends b implements j {
    public i() {
        super("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionService");
    }

    @Override // com.google.android.play.core.assetpacks.internal.b
    protected final boolean a(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        k kVar = null;
        if (i == 2) {
            Bundle bundle = (Bundle) c.a(parcel, Bundle.CREATOR);
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder != null) {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
                kVar = iInterfaceQueryLocalInterface instanceof k ? (k) iInterfaceQueryLocalInterface : new k(strongBinder);
            }
            c.b(parcel);
            c(bundle, kVar);
            return true;
        }
        if (i != 3) {
            return false;
        }
        Bundle bundle2 = (Bundle) c.a(parcel, Bundle.CREATOR);
        IBinder strongBinder2 = parcel.readStrongBinder();
        if (strongBinder2 != null) {
            IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
            kVar = iInterfaceQueryLocalInterface2 instanceof k ? (k) iInterfaceQueryLocalInterface2 : new k(strongBinder2);
        }
        c.b(parcel);
        b(bundle2, kVar);
        return true;
    }
}
