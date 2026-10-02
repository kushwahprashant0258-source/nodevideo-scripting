package com.unity3d.player;

import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: renamed from: com.unity3d.player.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0012d0 extends ContentObserver {
    private InterfaceC0009c0 a;

    public C0012d0(Handler handler, InterfaceC0009c0 interfaceC0009c0) {
        super(handler);
        this.a = interfaceC0009c0;
    }

    @Override // android.database.ContentObserver
    public final boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        InterfaceC0009c0 interfaceC0009c0 = this.a;
        if (interfaceC0009c0 != null) {
            ((OrientationLockListener) interfaceC0009c0).b();
        }
    }
}
