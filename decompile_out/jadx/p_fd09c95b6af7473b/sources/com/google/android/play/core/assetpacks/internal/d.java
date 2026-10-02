package com.google.android.play.core.assetpacks.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends a implements f {
    d(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void c(String str, List list, Bundle bundle, h hVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        c.c(parcelA, bundle);
        parcelA.writeStrongBinder(hVar);
        b(14, parcelA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void d(String str, Bundle bundle, Bundle bundle2, h hVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        c.c(parcelA, bundle);
        c.c(parcelA, bundle2);
        parcelA.writeStrongBinder(hVar);
        b(11, parcelA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void e(String str, Bundle bundle, h hVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        c.c(parcelA, bundle);
        parcelA.writeStrongBinder(hVar);
        b(5, parcelA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void f(String str, Bundle bundle, h hVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        c.c(parcelA, bundle);
        parcelA.writeStrongBinder(hVar);
        b(10, parcelA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void g(String str, Bundle bundle, Bundle bundle2, h hVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        c.c(parcelA, bundle);
        c.c(parcelA, bundle2);
        parcelA.writeStrongBinder(hVar);
        b(6, parcelA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void h(String str, Bundle bundle, Bundle bundle2, h hVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        c.c(parcelA, bundle);
        c.c(parcelA, bundle2);
        parcelA.writeStrongBinder(hVar);
        b(7, parcelA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void i(String str, Bundle bundle, Bundle bundle2, h hVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        c.c(parcelA, bundle);
        c.c(parcelA, bundle2);
        parcelA.writeStrongBinder(hVar);
        b(9, parcelA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void j(String str, Bundle bundle, Bundle bundle2, h hVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        c.c(parcelA, bundle);
        c.c(parcelA, bundle2);
        parcelA.writeStrongBinder(hVar);
        b(13, parcelA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void k(String str, List list, Bundle bundle, h hVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        c.c(parcelA, bundle);
        parcelA.writeStrongBinder(hVar);
        b(12, parcelA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void l(String str, List list, Bundle bundle, h hVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        c.c(parcelA, bundle);
        parcelA.writeStrongBinder(hVar);
        b(2, parcelA);
    }
}
