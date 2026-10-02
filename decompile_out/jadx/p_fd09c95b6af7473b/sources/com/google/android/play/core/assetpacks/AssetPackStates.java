package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AssetPackStates {
    public static AssetPackStates a(Bundle bundle, co coVar, eb ebVar, be beVar) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        HashMap map = new HashMap();
        int size = stringArrayList.size();
        for (int i = 0; i < size; i++) {
            String str = stringArrayList.get(i);
            map.put(str, AssetPackState.b(bundle, str, coVar, ebVar, beVar));
        }
        return new bo(bundle.getLong("total_bytes_to_download"), map);
    }

    public abstract Map<String, AssetPackState> packStates();

    public abstract long totalBytes();
}
