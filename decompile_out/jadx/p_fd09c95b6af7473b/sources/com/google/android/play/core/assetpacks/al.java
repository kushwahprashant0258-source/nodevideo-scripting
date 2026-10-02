package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
class al extends com.google.android.play.core.assetpacks.internal.g {
    final TaskCompletionSource a;
    final /* synthetic */ aw b;

    al(aw awVar, TaskCompletionSource taskCompletionSource) {
        this.b = awVar;
        this.a = taskCompletionSource;
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public final void b(int i, Bundle bundle) {
        this.b.f.u(this.a);
        aw.a.d("onCancelDownload(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public final void c(Bundle bundle) {
        this.b.f.u(this.a);
        aw.a.d("onCancelDownloads()", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public void d(Bundle bundle) {
        this.b.f.u(this.a);
        int i = bundle.getInt("error_code");
        aw.a.b("onError(%d)", Integer.valueOf(i));
        this.a.trySetException(new AssetPackException(i));
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public void e(Bundle bundle, Bundle bundle2) throws RemoteException {
        this.b.f.u(this.a);
        aw.a.d("onGetChunkFileDescriptor", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public final void f(int i, Bundle bundle) {
        this.b.f.u(this.a);
        aw.a.d("onGetSession(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public void g(List list) {
        this.b.f.u(this.a);
        aw.a.d("onGetSessionStates", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public void h(Bundle bundle, Bundle bundle2) {
        this.b.g.u(this.a);
        aw.a.d("onKeepAlive(%b)", Boolean.valueOf(bundle.getBoolean("keep_alive")));
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public final void i(Bundle bundle, Bundle bundle2) {
        this.b.f.u(this.a);
        aw.a.d("onNotifyChunkTransferred(%s, %s, %d, session=%d)", bundle.getString("module_name"), bundle.getString("slice_id"), Integer.valueOf(bundle.getInt("chunk_number")), Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public final void j(Bundle bundle, Bundle bundle2) {
        this.b.f.u(this.a);
        aw.a.d("onNotifyModuleCompleted(%s, sessionId=%d)", bundle.getString("module_name"), Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public final void k(Bundle bundle, Bundle bundle2) {
        this.b.f.u(this.a);
        aw.a.d("onNotifySessionFailed(%d)", Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public final void l(Bundle bundle, Bundle bundle2) {
        this.b.f.u(this.a);
        aw.a.d("onRemoveModule()", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public void m(Bundle bundle, Bundle bundle2) {
        this.b.f.u(this.a);
        aw.a.d("onRequestDownloadInfo()", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.internal.h
    public void n(int i, Bundle bundle) {
        this.b.f.u(this.a);
        aw.a.d("onStartDownload(%d)", Integer.valueOf(i));
    }
}
