package com.google.android.gms.common.api;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.3.0 */
/* JADX INFO: loaded from: classes.dex */
public class AvailabilityException extends Exception {
    private final ArrayMap zaa;

    public AvailabilityException(ArrayMap arrayMap) {
        this.zaa = arrayMap;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class o0oO0o0OO0o0.o0o0oOO0O00O00o$OOoooOoOo0OO
    	at o0oO0o0OO0o0.o0o0oOO0O00O00o.OOO0OooOo0O000(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:17)
    	at OO0OooO00o0Oo0.O0OO00O0oooo0o.o00O0Oo0o0O(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:3)
    	at oO0000OOoo0oO.o00O0Oo0o0O.o00oo0o0o000oOo(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:14)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.OOOO00oOO0O0o(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:102)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.ooO0Oo0O0OOo0(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:74)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.OOoo0o0ooo0O(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:21)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.OOoooOoOo0OO(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:1)
    	at o0oOOooOO000O0.O0ooOOOO00OOOOO.apply(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:44)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.O0ooOOOO00OOOOO(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:32)
     */
    public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> googleApi) {
        ArrayMap arrayMap = this.zaa;
        ApiKey<O> apiKey = googleApi.getApiKey();
        Preconditions.checkArgument(arrayMap.get(apiKey) != null, "The given API (" + apiKey.zaa() + ") was not part of the availability request.");
        return (ConnectionResult) Preconditions.checkNotNull((ConnectionResult) this.zaa.get(apiKey));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Throwable
    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (ApiKey apiKey : this.zaa.keySet()) {
            ConnectionResult connectionResult = (ConnectionResult) Preconditions.checkNotNull((ConnectionResult) this.zaa.get(apiKey));
            z &= !connectionResult.isSuccess();
            arrayList.add(apiKey.zaa() + ": " + String.valueOf(connectionResult));
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("None of the queried APIs are available. ");
        } else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(TextUtils.join("; ", arrayList));
        return sb.toString();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class o0oO0o0OO0o0.o0o0oOO0O00O00o$OOoooOoOo0OO
    	at o0oO0o0OO0o0.o0o0oOO0O00O00o.OOO0OooOo0O000(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:17)
    	at OO0OooO00o0Oo0.O0OO00O0oooo0o.o00O0Oo0o0O(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:3)
    	at oO0000OOoo0oO.o00O0Oo0o0O.o00oo0o0o000oOo(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:14)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.OOOO00oOO0O0o(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:102)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.ooO0Oo0O0OOo0(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:74)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.OOoo0o0ooo0O(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:21)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.OOoooOoOo0OO(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:1)
    	at o0oOOooOO000O0.O0ooOOOO00OOOOO.apply(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:44)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.O0ooOOOO00OOOOO(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:32)
     */
    public ConnectionResult getConnectionResult(HasApiKey<? extends Api.ApiOptions> hasApiKey) {
        ArrayMap arrayMap = this.zaa;
        ApiKey<O> apiKey = hasApiKey.getApiKey();
        Preconditions.checkArgument(arrayMap.get(apiKey) != null, "The given API (" + apiKey.zaa() + ") was not part of the availability request.");
        return (ConnectionResult) Preconditions.checkNotNull((ConnectionResult) this.zaa.get(apiKey));
    }
}
