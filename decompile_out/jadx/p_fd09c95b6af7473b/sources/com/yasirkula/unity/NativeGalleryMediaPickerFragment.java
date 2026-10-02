package com.yasirkula.unity;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class NativeGalleryMediaPickerFragment extends Fragment {
    private static final int MEDIA_REQUEST_CODE = 987455;
    public static final String MEDIA_TYPE_ID = "NGMP_MEDIA_TYPE";
    public static final String MIME_ID = "NGMP_MIME";
    public static final String SAVE_PATH_ID = "NGMP_SAVE_PATH";
    public static final String SELECT_MULTIPLE_ID = "NGMP_MULTIPLE";
    public static final String TITLE_ID = "NGMP_TITLE";
    public static boolean preferGetContent = false;
    public static boolean showProgressbar = true;
    public static boolean tryPreserveFilenames;
    public static boolean useDefaultGalleryApp;
    private final NativeGalleryMediaReceiver mediaReceiver;
    private String savePathDirectory;
    private String savePathFilename;
    private boolean selectMultiple;

    public NativeGalleryMediaPickerFragment() {
        this.mediaReceiver = null;
    }

    public NativeGalleryMediaPickerFragment(final NativeGalleryMediaReceiver mediaReceiver) {
        this.mediaReceiver = mediaReceiver;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Intent intent;
        int i;
        super.onCreate(savedInstanceState);
        if (this.mediaReceiver == null) {
            onActivityResult(MEDIA_REQUEST_CODE, 0, null);
            return;
        }
        int i2 = getArguments().getInt(MEDIA_TYPE_ID);
        String string = getArguments().getString(MIME_ID);
        String string2 = getArguments().getString(TITLE_ID);
        this.selectMultiple = getArguments().getBoolean(SELECT_MULTIPLE_ID);
        String string3 = getArguments().getString(SAVE_PATH_ID);
        int iLastIndexOf = string3.lastIndexOf(47);
        this.savePathFilename = iLastIndexOf >= 0 ? string3.substring(iLastIndexOf + 1) : string3;
        this.savePathDirectory = iLastIndexOf > 0 ? string3.substring(0, iLastIndexOf) : getActivity().getCacheDir().getAbsolutePath();
        int i3 = i2 & 1;
        int i4 = i3 == 1 ? 1 : 0;
        int i5 = i2 & 2;
        if (i5 == 2) {
            i4++;
        }
        int i6 = i2 & 4;
        if (i6 == 4) {
            i4++;
        }
        if (preferGetContent || this.selectMultiple || i4 != 1 || i2 == 4) {
            intent = null;
        } else {
            intent = new Intent("android.intent.action.PICK");
            if (i2 == 1) {
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, string);
            } else if (i2 == 2) {
                intent.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, string);
            } else {
                intent.setDataAndType(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, string);
            }
        }
        if (intent == null) {
            intent = new Intent(i4 > 1 ? "android.intent.action.OPEN_DOCUMENT" : "android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.addFlags(1);
            if (this.selectMultiple) {
                intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            }
            if (i4 > 1) {
                String[] strArr = new String[i4];
                if (i3 == 1) {
                    strArr[0] = "image/*";
                    i = 1;
                } else {
                    i = 0;
                }
                if (i5 == 2) {
                    strArr[i] = "video/*";
                    i++;
                }
                if (i6 == 4) {
                    strArr[i] = "audio/*";
                }
                intent.putExtra("android.intent.extra.MIME_TYPES", strArr);
                string = "*/*";
            }
            intent.setType(string);
        }
        if (string2 != null && string2.length() > 0) {
            intent.putExtra("android.intent.extra.TITLE", string2);
        }
        try {
            if (!useDefaultGalleryApp && (Build.VERSION.SDK_INT != 30 || !NativeGalleryUtils.IsXiaomiOrMIUI())) {
                startActivityForResult(Intent.createChooser(intent, string2), MEDIA_REQUEST_CODE);
                return;
            }
            startActivityForResult(intent, MEDIA_REQUEST_CODE);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(getActivity(), "No apps can perform this action.", 1).show();
            onActivityResult(MEDIA_REQUEST_CODE, 0, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x004c  */
    /* JADX WARN: Code duplicated, block: B:23:0x005c  */
    @Override // android.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        NativeGalleryMediaPickerResultFragment nativeGalleryMediaPickerResultFragment;
        if (requestCode != MEDIA_REQUEST_CODE) {
            return;
        }
        NativeGalleryMediaReceiver nativeGalleryMediaReceiver = this.mediaReceiver;
        if (nativeGalleryMediaReceiver == null) {
            Log.d("Unity", "NativeGalleryMediaPickerFragment.mediaReceiver became null!");
        } else if (resultCode != -1 || data == null) {
            if (!this.selectMultiple) {
                nativeGalleryMediaReceiver.OnMediaReceived("");
            } else {
                nativeGalleryMediaReceiver.OnMultipleMediaReceived("");
            }
        } else {
            NativeGalleryMediaPickerResultOperation nativeGalleryMediaPickerResultOperation = new NativeGalleryMediaPickerResultOperation(getActivity(), this.mediaReceiver, data, this.selectMultiple, this.savePathDirectory, this.savePathFilename);
            if (showProgressbar) {
                nativeGalleryMediaPickerResultFragment = new NativeGalleryMediaPickerResultFragment(nativeGalleryMediaPickerResultOperation);
            } else {
                nativeGalleryMediaPickerResultOperation.execute();
                nativeGalleryMediaPickerResultOperation.sendResultToUnity();
            }
            if (nativeGalleryMediaPickerResultFragment == null) {
                getFragmentManager().beginTransaction().remove(this).commit();
            } else {
                getFragmentManager().beginTransaction().remove(this).add(0, nativeGalleryMediaPickerResultFragment).commit();
            }
        }
        nativeGalleryMediaPickerResultFragment = null;
        if (nativeGalleryMediaPickerResultFragment == null) {
            getFragmentManager().beginTransaction().remove(this).commit();
        } else {
            getFragmentManager().beginTransaction().remove(this).add(0, nativeGalleryMediaPickerResultFragment).commit();
        }
    }
}
