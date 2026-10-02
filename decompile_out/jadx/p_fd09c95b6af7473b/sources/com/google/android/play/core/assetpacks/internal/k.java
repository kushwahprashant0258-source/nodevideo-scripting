package com.google.android.play.core.assetpacks.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class k extends a implements IInterface {
    k(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
    }

    public final void c(Bundle bundle) throws RemoteException {
        Parcel parcelA = a();
        c.c(parcelA, bundle);
        b(4, parcelA);
    }

    public final void d(Bundle bundle) throws RemoteException {
        Parcel parcelA = a();
        c.c(parcelA, bundle);
        b(3, parcelA);
    }

    public final void e(Bundle bundle, Bundle bundle2) throws RemoteException {
        Parcel parcelA = a();
        c.c(parcelA, bundle);
        c.c(parcelA, bundle2);
        b(2, parcelA);
    }
}
