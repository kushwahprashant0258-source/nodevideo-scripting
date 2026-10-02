package com.yasirkula.unity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NativeGalleryPermissionFragment extends Fragment {
    public static final String MEDIA_TYPE_ID = "NG_MediaType";
    private static final int PERMISSIONS_REQUEST_CODE = 123655;
    public static final String READ_PERMISSION_ONLY = "NG_ReadOnly";
    private final NativeGalleryPermissionReceiver permissionReceiver;

    public NativeGalleryPermissionFragment() {
        this.permissionReceiver = null;
    }

    public NativeGalleryPermissionFragment(final NativeGalleryPermissionReceiver permissionReceiver) {
        this.permissionReceiver = permissionReceiver;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.permissionReceiver == null) {
            onRequestPermissionsResult(PERMISSIONS_REQUEST_CODE, new String[0], new int[0]);
            return;
        }
        if (!getArguments().getBoolean(READ_PERMISSION_ONLY) && Build.VERSION.SDK_INT < 30) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, PERMISSIONS_REQUEST_CODE);
            return;
        }
        if (Build.VERSION.SDK_INT < 33 || getActivity().getApplicationInfo().targetSdkVersion < 33) {
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, PERMISSIONS_REQUEST_CODE);
            return;
        }
        if (Build.VERSION.SDK_INT < 34) {
            ArrayList arrayList = new ArrayList(3);
            int i = getArguments().getInt(MEDIA_TYPE_ID);
            if ((i & 1) == 1) {
                arrayList.add("android.permission.READ_MEDIA_IMAGES");
            }
            if ((i & 2) == 2) {
                arrayList.add("android.permission.READ_MEDIA_VIDEO");
            }
            if ((i & 4) == 4) {
                arrayList.add("android.permission.READ_MEDIA_AUDIO");
            }
            String[] strArr = new String[arrayList.size()];
            arrayList.toArray(strArr);
            requestPermissions(strArr, PERMISSIONS_REQUEST_CODE);
            return;
        }
        onRequestPermissionsResult(PERMISSIONS_REQUEST_CODE, new String[0], new int[0]);
    }

    @Override // android.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != PERMISSIONS_REQUEST_CODE) {
            return;
        }
        if (this.permissionReceiver == null) {
            Log.e("Unity", "Fragment data got reset while asking permissions!");
            getFragmentManager().beginTransaction().remove(this).commit();
            return;
        }
        int i = 2;
        if (permissions.length != 0 && grantResults.length != 0) {
            int i2 = 1;
            int i3 = 0;
            while (true) {
                if (i3 >= permissions.length || i3 >= grantResults.length) {
                    i = i2;
                    break;
                }
                if (grantResults[i3] == -1) {
                    if (!shouldShowRequestPermissionRationale(permissions[i3])) {
                        i = 0;
                        break;
                    }
                    i2 = 2;
                }
                i3++;
            }
        }
        this.permissionReceiver.OnPermissionResult(i);
        getFragmentManager().beginTransaction().remove(this).commit();
        try {
            Intent intent = new Intent(getActivity(), getActivity().getClass());
            intent.setFlags(131072);
            getActivity().startActivityIfNeeded(intent, 0);
        } catch (Exception e) {
            Log.e("Unity", "Exception (resume):", e);
        }
    }
}
