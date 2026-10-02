package com.yasirkula.unity;

import android.app.Activity;
import android.app.RemoteAction;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class NativeGallery {
    public static final int MEDIA_TYPE_AUDIO = 4;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static boolean PermissionFreeMode;
    public static boolean mediaSaveOmitDCIM;
    public static boolean overwriteExistingMedia;

    public static boolean CanSelectMultipleMedia() {
        return true;
    }

    public static boolean CanSelectMultipleMediaTypes() {
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0221  */
    /* JADX WARN: Code duplicated, block: B:110:0x023c A[Catch: IllegalStateException -> 0x02d6, Exception -> 0x02f5, TRY_LEAVE, TryCatch #3 {Exception -> 0x02f5, blocks: (B:105:0x022c, B:106:0x0230, B:108:0x0236, B:110:0x023c, B:112:0x0253), top: B:258:0x022c }] */
    /* JADX WARN: Code duplicated, block: B:138:0x02b2 A[Catch: Exception -> 0x02be, IllegalStateException -> 0x02c0, TryCatch #22 {IllegalStateException -> 0x02c0, Exception -> 0x02be, blocks: (B:136:0x02ac, B:138:0x02b2, B:141:0x02b9, B:135:0x02a9), top: B:287:0x02a9 }] */
    /* JADX WARN: Code duplicated, block: B:141:0x02b9 A[Catch: Exception -> 0x02be, IllegalStateException -> 0x02c0, TRY_LEAVE, TryCatch #22 {IllegalStateException -> 0x02c0, Exception -> 0x02be, blocks: (B:136:0x02ac, B:138:0x02b2, B:141:0x02b9, B:135:0x02a9), top: B:287:0x02a9 }] */
    /* JADX WARN: Code duplicated, block: B:149:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:180:0x036f  */
    /* JADX WARN: Code duplicated, block: B:182:0x037b  */
    /* JADX WARN: Code duplicated, block: B:187:0x038e A[LOOP:0: B:55:0x0130->B:187:0x038e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:229:0x0492  */
    /* JADX WARN: Code duplicated, block: B:241:0x04a7 A[Catch: Exception -> 0x04ee, TRY_ENTER, TryCatch #1 {Exception -> 0x04ee, blocks: (B:212:0x0434, B:214:0x043f, B:216:0x044c, B:243:0x04b7, B:231:0x0495, B:241:0x04a7, B:242:0x04af, B:248:0x04ea, B:249:0x04ed), top: B:256:0x0434 }] */
    /* JADX WARN: Code duplicated, block: B:242:0x04af A[Catch: Exception -> 0x04ee, TryCatch #1 {Exception -> 0x04ee, blocks: (B:212:0x0434, B:214:0x043f, B:216:0x044c, B:243:0x04b7, B:231:0x0495, B:241:0x04a7, B:242:0x04af, B:248:0x04ea, B:249:0x04ed), top: B:256:0x0434 }] */
    /* JADX WARN: Code duplicated, block: B:258:0x022c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:292:0x04f4 A[EDGE_INSN: B:292:0x04f4->B:252:0x04f4 BREAK  A[LOOP:0: B:55:0x0130->B:187:0x038e], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x0207  */
    /* JADX WARN: Code duplicated, block: B:99:0x021b A[PHI: r2 r4 r31
      0x021b: PHI (r2v21 android.database.Cursor) = (r2v20 android.database.Cursor), (r2v23 android.database.Cursor) binds: [B:98:0x0219, B:87:0x0208] A[DONT_GENERATE, DONT_INLINE]
      0x021b: PHI (r4v7 android.net.Uri) = (r4v6 android.net.Uri), (r4v13 android.net.Uri) binds: [B:98:0x0219, B:87:0x0208] A[DONT_GENERATE, DONT_INLINE]
      0x021b: PHI (r31v5 java.lang.String) = (r31v4 java.lang.String), (r31v7 java.lang.String) binds: [B:98:0x0219, B:87:0x0208] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r12v4 */
    public static String SaveMedia(Context context, int mediaType, String filePath, String directoryName) throws Throwable {
        Uri uri;
        String str;
        String strSubstring;
        int i;
        File file;
        ?? r12;
        Uri uriWithAppendedId;
        Cursor cursorQuery;
        String str2;
        String str3;
        Cursor cursor;
        Cursor cursorQuery2;
        Uri uriWithAppendedId2;
        Uri uriInsert;
        Uri uriInsert2;
        File file2;
        String str4;
        Context context2;
        String str5;
        String strGetPathFromURI;
        File file3 = new File(filePath);
        if (!file3.exists()) {
            Log.e("Unity", "Original media file is missing or inaccessible!");
            return "";
        }
        int iLastIndexOf = filePath.lastIndexOf(47);
        int iLastIndexOf2 = filePath.lastIndexOf(46);
        String strSubstring2 = iLastIndexOf >= 0 ? filePath.substring(iLastIndexOf + 1) : filePath;
        String strSubstring3 = iLastIndexOf2 >= 0 ? filePath.substring(iLastIndexOf2 + 1) : "";
        String mimeTypeFromExtension = strSubstring3.length() > 0 ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(strSubstring3.toLowerCase(Locale.ENGLISH)) : null;
        ContentValues contentValues = new ContentValues();
        String str6 = "title";
        contentValues.put("title", strSubstring2);
        String str7 = "_display_name";
        contentValues.put("_display_name", strSubstring2);
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000));
        if (mimeTypeFromExtension != null && mimeTypeFromExtension.length() > 0) {
            contentValues.put("mime_type", mimeTypeFromExtension);
        }
        if (mediaType == 1) {
            int iGetImageOrientation = NativeGalleryUtils.GetImageOrientation(context, filePath);
            if (iGetImageOrientation == 3) {
                contentValues.put("orientation", (Integer) 180);
            } else if (iGetImageOrientation == 5 || iGetImageOrientation == 6) {
                contentValues.put("orientation", (Integer) 90);
            } else if (iGetImageOrientation == 7 || iGetImageOrientation == 8) {
                contentValues.put("orientation", (Integer) 270);
            }
        }
        if (mediaType == 1) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (mediaType == 2) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
        String str8 = ".";
        String str9 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE";
        String str10 = "Saved media to: ";
        String str11 = "Exception:";
        if (Build.VERSION.SDK_INT >= 29) {
            if (mediaSaveOmitDCIM) {
                str2 = directoryName + "/";
            } else {
                str2 = (mediaType != 4 ? "DCIM/" : "Music/") + directoryName + "/";
            }
            String str12 = "relative_path";
            contentValues.put("relative_path", str2);
            contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
            int i2 = 0;
            while (i2 < 2) {
                contentValues.put("is_pending", (Boolean) true);
                if (i2 == 1) {
                    String str13 = ((strSubstring3.length() <= 0 || strSubstring2.length() <= strSubstring3.length()) ? strSubstring2 : strSubstring2.substring(0, (strSubstring2.length() - strSubstring3.length()) - 1)) + " " + new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss").format(new Date());
                    if (strSubstring3.length() > 0) {
                        str13 = str13 + str8 + strSubstring3;
                    }
                    contentValues.put(str6, str13);
                    contentValues.put(str7, str13);
                }
                if (!overwriteExistingMedia) {
                    uriInsert2 = context.getContentResolver().insert(uri, contentValues);
                    str3 = str12;
                } else {
                    try {
                        try {
                            str3 = str12;
                            try {
                                cursorQuery2 = context.getContentResolver().query(uri, new String[]{"_id"}, "relative_path=? AND _display_name=?", new String[]{contentValues.getAsString(str12), contentValues.getAsString(str7)}, null);
                                if (cursorQuery2 != null) {
                                    try {
                                        try {
                                            if (cursorQuery2.moveToFirst()) {
                                                uriWithAppendedId2 = ContentUris.withAppendedId(uri, cursorQuery2.getLong(cursorQuery2.getColumnIndex("_id")));
                                                try {
                                                    Log.d("Unity", "Overwriting existing media");
                                                } catch (Exception e) {
                                                    e = e;
                                                    Log.e("Unity", "Couldn't overwrite existing media's metadata:", e);
                                                    if (cursorQuery2 != null) {
                                                        cursorQuery2.close();
                                                    }
                                                }
                                            } else {
                                                uriWithAppendedId2 = null;
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            cursor = cursorQuery2;
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            throw th;
                                        }
                                    } catch (Exception e2) {
                                        e = e2;
                                        uriWithAppendedId2 = null;
                                        Log.e("Unity", "Couldn't overwrite existing media's metadata:", e);
                                        if (cursorQuery2 != null) {
                                            cursorQuery2.close();
                                        }
                                        uriInsert = uriWithAppendedId2;
                                        if (uriInsert == null) {
                                            uriInsert = context.getContentResolver().insert(uri, contentValues);
                                        }
                                        uriInsert2 = uriInsert;
                                        if (uriInsert2 != null) {
                                            try {
                                                try {
                                                    try {
                                                        file2 = file3;
                                                        try {
                                                            if (NativeGalleryUtils.WriteFileToStream(file2, context.getContentResolver().openOutputStream(uriInsert2))) {
                                                                contentValues.put("is_pending", (Boolean) false);
                                                                context.getContentResolver().update(uriInsert2, contentValues, null, null);
                                                                str10 = str10;
                                                                try {
                                                                    Log.d("Unity", str10 + uriInsert2.toString());
                                                                    try {
                                                                        str5 = str9;
                                                                        try {
                                                                            Intent intent = new Intent(str5);
                                                                            intent.setData(uriInsert2);
                                                                            str6 = str6;
                                                                            str4 = str8;
                                                                            context2 = context;
                                                                            try {
                                                                                context2.sendBroadcast(intent);
                                                                                str7 = str7;
                                                                                str11 = str11;
                                                                            } catch (IllegalStateException e3) {
                                                                                e = e3;
                                                                                str9 = str5;
                                                                                str7 = str7;
                                                                                str11 = str11;
                                                                                if (i2 == 1) {
                                                                                    Log.e("Unity", str11, e);
                                                                                }
                                                                                context.getContentResolver().delete(uriInsert2, null, null);
                                                                                if (overwriteExistingMedia) {
                                                                                    break;
                                                                                    return "";
                                                                                }
                                                                                i2++;
                                                                                str12 = str3;
                                                                                str10 = str10;
                                                                                str8 = str4;
                                                                                str11 = str11;
                                                                                str6 = str6;
                                                                                str7 = str7;
                                                                                strSubstring2 = strSubstring2;
                                                                                file3 = file2;
                                                                            } catch (Exception e4) {
                                                                                e = e4;
                                                                                str7 = str7;
                                                                                str11 = str11;
                                                                                try {
                                                                                    Log.e("Unity", str11, e);
                                                                                } catch (IllegalStateException e5) {
                                                                                    e = e5;
                                                                                    str9 = str5;
                                                                                } catch (Exception e6) {
                                                                                    e = e6;
                                                                                    Log.e("Unity", str11, e);
                                                                                    if (overwriteExistingMedia && e.getClass().getName().equals("android.app.RecoverableSecurityException")) {
                                                                                        try {
                                                                                            Uri uri2 = uriInsert2;
                                                                                            context.startIntentSender(((RemoteAction) e.getClass().getMethod("getUserAction", new Class[0]).invoke(e, new Object[0])).getActionIntent().getIntentSender(), null, 0, 0, 0);
                                                                                            String strGetPathFromURI2 = NativeGalleryUtils.GetPathFromURI(context2, uri2);
                                                                                            return (strGetPathFromURI2 == null || strGetPathFromURI2.length() <= 0) ? uri2.toString() : strGetPathFromURI2;
                                                                                        } catch (Exception e7) {
                                                                                            Log.e("Unity", "RecoverableSecurityException failure:", e7);
                                                                                            return "";
                                                                                        }
                                                                                    }
                                                                                    context.getContentResolver().delete(uriInsert2, null, null);
                                                                                    return "";
                                                                                }
                                                                            }
                                                                        } catch (IllegalStateException e8) {
                                                                            e = e8;
                                                                            str6 = str6;
                                                                            str4 = str8;
                                                                        } catch (Exception e9) {
                                                                            e = e9;
                                                                            str4 = str8;
                                                                            context2 = context;
                                                                            str7 = str7;
                                                                            str11 = str11;
                                                                            Log.e("Unity", str11, e);
                                                                            strGetPathFromURI = NativeGalleryUtils.GetPathFromURI(context2, uriInsert2);
                                                                            if (strGetPathFromURI != null) {
                                                                                strGetPathFromURI = uriInsert2.toString();
                                                                            } else {
                                                                                strGetPathFromURI = uriInsert2.toString();
                                                                            }
                                                                            return strGetPathFromURI;
                                                                        }
                                                                    } catch (IllegalStateException e10) {
                                                                        e = e10;
                                                                        str6 = str6;
                                                                        str4 = str8;
                                                                    } catch (Exception e11) {
                                                                        e = e11;
                                                                        str5 = str9;
                                                                    }
                                                                    strGetPathFromURI = NativeGalleryUtils.GetPathFromURI(context2, uriInsert2);
                                                                    if (strGetPathFromURI != null) {
                                                                        strGetPathFromURI = uriInsert2.toString();
                                                                    } else {
                                                                        strGetPathFromURI = uriInsert2.toString();
                                                                    }
                                                                    return strGetPathFromURI;
                                                                } catch (IllegalStateException e12) {
                                                                    e = e12;
                                                                    str4 = str8;
                                                                }
                                                            }
                                                        } catch (IllegalStateException e13) {
                                                            e = e13;
                                                            str10 = str10;
                                                        }
                                                    } catch (IllegalStateException e14) {
                                                        e = e14;
                                                        str7 = str7;
                                                        file2 = file3;
                                                        str10 = str10;
                                                        str6 = str6;
                                                    }
                                                    str4 = str8;
                                                } catch (Exception e15) {
                                                    e = e15;
                                                    context2 = context;
                                                    str11 = str11;
                                                }
                                            } catch (IllegalStateException e16) {
                                                e = e16;
                                                str7 = str7;
                                                file2 = file3;
                                                str10 = str10;
                                                str11 = str11;
                                                str6 = str6;
                                                str4 = str8;
                                            }
                                            if (i2 == 1) {
                                                Log.e("Unity", str11, e);
                                            }
                                            context.getContentResolver().delete(uriInsert2, null, null);
                                            if (overwriteExistingMedia) {
                                                break;
                                                return "";
                                            }
                                            i2++;
                                            str12 = str3;
                                            str10 = str10;
                                            str8 = str4;
                                            str11 = str11;
                                            str6 = str6;
                                            str7 = str7;
                                            strSubstring2 = strSubstring2;
                                            file3 = file2;
                                        } else {
                                            file2 = file3;
                                        }
                                        str4 = str8;
                                        if (overwriteExistingMedia) {
                                            break;
                                            return "";
                                        }
                                        i2++;
                                        str12 = str3;
                                        str10 = str10;
                                        str8 = str4;
                                        str11 = str11;
                                        str6 = str6;
                                        str7 = str7;
                                        strSubstring2 = strSubstring2;
                                        file3 = file2;
                                    }
                                } else {
                                    uriWithAppendedId2 = null;
                                }
                                if (cursorQuery2 != null) {
                                    cursorQuery2.close();
                                }
                            } catch (Exception e17) {
                                e = e17;
                                cursorQuery2 = null;
                                uriWithAppendedId2 = null;
                                Log.e("Unity", "Couldn't overwrite existing media's metadata:", e);
                                if (cursorQuery2 != null) {
                                    cursorQuery2.close();
                                }
                                uriInsert = uriWithAppendedId2;
                                if (uriInsert == null) {
                                    uriInsert = context.getContentResolver().insert(uri, contentValues);
                                }
                                uriInsert2 = uriInsert;
                                if (uriInsert2 != null) {
                                    file2 = file3;
                                    if (NativeGalleryUtils.WriteFileToStream(file2, context.getContentResolver().openOutputStream(uriInsert2))) {
                                        contentValues.put("is_pending", (Boolean) false);
                                        context.getContentResolver().update(uriInsert2, contentValues, null, null);
                                        str10 = str10;
                                        Log.d("Unity", str10 + uriInsert2.toString());
                                        str5 = str9;
                                        Intent intent2 = new Intent(str5);
                                        intent2.setData(uriInsert2);
                                        str6 = str6;
                                        str4 = str8;
                                        context2 = context;
                                        context2.sendBroadcast(intent2);
                                        str7 = str7;
                                        str11 = str11;
                                        strGetPathFromURI = NativeGalleryUtils.GetPathFromURI(context2, uriInsert2);
                                        if (strGetPathFromURI != null) {
                                            strGetPathFromURI = uriInsert2.toString();
                                        } else {
                                            strGetPathFromURI = uriInsert2.toString();
                                        }
                                        return strGetPathFromURI;
                                    }
                                    str4 = str8;
                                    if (i2 == 1) {
                                        Log.e("Unity", str11, e);
                                    }
                                    context.getContentResolver().delete(uriInsert2, null, null);
                                    if (overwriteExistingMedia) {
                                        break;
                                        return "";
                                    }
                                    i2++;
                                    str12 = str3;
                                    str10 = str10;
                                    str8 = str4;
                                    str11 = str11;
                                    str6 = str6;
                                    str7 = str7;
                                    strSubstring2 = strSubstring2;
                                    file3 = file2;
                                } else {
                                    file2 = file3;
                                }
                                str4 = str8;
                                if (overwriteExistingMedia) {
                                    break;
                                    return "";
                                }
                                i2++;
                                str12 = str3;
                                str10 = str10;
                                str8 = str4;
                                str11 = str11;
                                str6 = str6;
                                str7 = str7;
                                strSubstring2 = strSubstring2;
                                file3 = file2;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = null;
                        }
                    } catch (Exception e18) {
                        e = e18;
                        str3 = str12;
                    }
                    uriInsert = uriWithAppendedId2;
                    if (uriInsert == null) {
                        uriInsert = context.getContentResolver().insert(uri, contentValues);
                    }
                    uriInsert2 = uriInsert;
                }
                if (uriInsert2 != null) {
                    file2 = file3;
                    if (NativeGalleryUtils.WriteFileToStream(file2, context.getContentResolver().openOutputStream(uriInsert2))) {
                        contentValues.put("is_pending", (Boolean) false);
                        context.getContentResolver().update(uriInsert2, contentValues, null, null);
                        str10 = str10;
                        Log.d("Unity", str10 + uriInsert2.toString());
                        str5 = str9;
                        Intent intent3 = new Intent(str5);
                        intent3.setData(uriInsert2);
                        str6 = str6;
                        str4 = str8;
                        context2 = context;
                        context2.sendBroadcast(intent3);
                        str7 = str7;
                        str11 = str11;
                        strGetPathFromURI = NativeGalleryUtils.GetPathFromURI(context2, uriInsert2);
                        if (strGetPathFromURI != null || strGetPathFromURI.length() <= 0) {
                            strGetPathFromURI = uriInsert2.toString();
                        }
                        return strGetPathFromURI;
                    }
                    str4 = str8;
                    if (i2 == 1) {
                        Log.e("Unity", str11, e);
                    }
                    context.getContentResolver().delete(uriInsert2, null, null);
                    if (overwriteExistingMedia) {
                        break;
                    }
                    i2++;
                    str12 = str3;
                    str10 = str10;
                    str8 = str4;
                    str11 = str11;
                    str6 = str6;
                    str7 = str7;
                    strSubstring2 = strSubstring2;
                    file3 = file2;
                } else {
                    file2 = file3;
                }
                str4 = str8;
                if (overwriteExistingMedia) {
                    break;
                    break;
                }
                i2++;
                str12 = str3;
                str10 = str10;
                str8 = str4;
                str11 = str11;
                str6 = str6;
                str7 = str7;
                strSubstring2 = strSubstring2;
                file3 = file2;
            }
        } else {
            String str14 = strSubstring2;
            File file4 = new File(mediaSaveOmitDCIM ? Environment.getExternalStorageDirectory() : Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), directoryName);
            file4.mkdirs();
            if (strSubstring3.length() <= 0 || str14.length() <= strSubstring3.length()) {
                str = str14;
                strSubstring = str;
            } else {
                str = str14;
                strSubstring = str.substring(0, (str14.length() - strSubstring3.length()) - 1);
            }
            while (true) {
                file = new File(file4, str);
                int i3 = i + 1;
                str = strSubstring + i;
                if (strSubstring3.length() > 0) {
                    str = str + "." + strSubstring3;
                }
                i = (!overwriteExistingMedia && file.exists()) ? i3 : 1;
            }
            try {
                if (NativeGalleryUtils.WriteFileToStream(file3, new FileOutputStream(file))) {
                    contentValues.put("_data", file.getAbsolutePath());
                    try {
                        if (!overwriteExistingMedia) {
                            context.getContentResolver().insert(uri, contentValues);
                        } else {
                            try {
                                cursorQuery = context.getContentResolver().query(uri, new String[]{"_id"}, "_data=?", new String[]{contentValues.getAsString("_data")}, null);
                                if (cursorQuery != null) {
                                    try {
                                        if (cursorQuery.moveToFirst()) {
                                            uriWithAppendedId = ContentUris.withAppendedId(uri, cursorQuery.getLong(cursorQuery.getColumnIndex("_id")));
                                            try {
                                                Log.d("Unity", "Overwriting existing media");
                                            } catch (Exception e19) {
                                                e = e19;
                                                Log.e("Unity", "Couldn't overwrite existing media's metadata:", e);
                                                if (cursorQuery != null) {
                                                }
                                                if (uriWithAppendedId == null) {
                                                    context.getContentResolver().insert(uri, contentValues);
                                                } else {
                                                    context.getContentResolver().update(uriWithAppendedId, contentValues, null, null);
                                                }
                                                Log.d("Unity", str10 + file.getPath());
                                                Intent intent4 = new Intent(str9);
                                                intent4.setData(Uri.fromFile(file));
                                                context.sendBroadcast(intent4);
                                                return file.getAbsolutePath();
                                            }
                                        } else {
                                            uriWithAppendedId = null;
                                        }
                                    } catch (Exception e20) {
                                        e = e20;
                                        uriWithAppendedId = null;
                                    }
                                } else {
                                    uriWithAppendedId = null;
                                }
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                            } catch (Exception e21) {
                                e = e21;
                                uriWithAppendedId = null;
                                cursorQuery = null;
                            } catch (Throwable th3) {
                                th = th3;
                                r12 = 0;
                                if (r12 != 0) {
                                    r12.close();
                                }
                                throw th;
                            }
                            if (uriWithAppendedId == null) {
                                context.getContentResolver().insert(uri, contentValues);
                            } else {
                                context.getContentResolver().update(uriWithAppendedId, contentValues, null, null);
                            }
                        }
                        Log.d("Unity", str10 + file.getPath());
                        Intent intent5 = new Intent(str9);
                        intent5.setData(Uri.fromFile(file));
                        context.sendBroadcast(intent5);
                        return file.getAbsolutePath();
                    } catch (Throwable th4) {
                        th = th4;
                        r12 = strSubstring;
                    }
                }
            } catch (Exception e22) {
                Log.e("Unity", str11, e22);
            }
        }
        return "";
    }

    public static void MediaDeleteFile(Context context, String path, int mediaType) {
        if (mediaType == 1) {
            context.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data=?", new String[]{path});
        } else if (mediaType == 2) {
            context.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_data=?", new String[]{path});
        } else {
            context.getContentResolver().delete(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, "_data=?", new String[]{path});
        }
    }

    public static void PickMedia(Context context, final NativeGalleryMediaReceiver mediaReceiver, int mediaType, boolean selectMultiple, String savePath, String mime, String title) {
        if (CheckPermission(context, true, mediaType) != 1) {
            if (!selectMultiple) {
                mediaReceiver.OnMediaReceived("");
                return;
            } else {
                mediaReceiver.OnMultipleMediaReceived("");
                return;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt(NativeGalleryMediaPickerFragment.MEDIA_TYPE_ID, mediaType);
        bundle.putBoolean(NativeGalleryMediaPickerFragment.SELECT_MULTIPLE_ID, selectMultiple);
        bundle.putString(NativeGalleryMediaPickerFragment.SAVE_PATH_ID, savePath);
        bundle.putString(NativeGalleryMediaPickerFragment.MIME_ID, mime);
        bundle.putString(NativeGalleryMediaPickerFragment.TITLE_ID, title);
        NativeGalleryMediaPickerFragment nativeGalleryMediaPickerFragment = new NativeGalleryMediaPickerFragment(mediaReceiver);
        nativeGalleryMediaPickerFragment.setArguments(bundle);
        ((Activity) context).getFragmentManager().beginTransaction().add(0, nativeGalleryMediaPickerFragment).commit();
    }

    public static int CheckPermission(Context context, final boolean readPermission, final int mediaType) {
        if (PermissionFreeMode) {
            return 1;
        }
        if (!readPermission) {
            if (Build.VERSION.SDK_INT >= 29) {
                return 1;
            }
            if (context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                return 0;
            }
        }
        if (Build.VERSION.SDK_INT < 33 || context.getApplicationInfo().targetSdkVersion < 33) {
            if (context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                return 0;
            }
        } else if (Build.VERSION.SDK_INT < 34) {
            if ((mediaType & 1) == 1 && context.checkSelfPermission("android.permission.READ_MEDIA_IMAGES") != 0) {
                return 0;
            }
            if ((mediaType & 2) == 2 && context.checkSelfPermission("android.permission.READ_MEDIA_VIDEO") != 0) {
                return 0;
            }
            if ((mediaType & 4) == 4 && context.checkSelfPermission("android.permission.READ_MEDIA_AUDIO") != 0) {
                return 0;
            }
        }
        return 1;
    }

    public static void RequestPermission(Context context, final NativeGalleryPermissionReceiver permissionReceiver, final boolean readPermission, final int mediaType, final int lastCheckResult) {
        if (CheckPermission(context, readPermission, mediaType) == 1) {
            permissionReceiver.OnPermissionResult(1);
            return;
        }
        if (lastCheckResult == 0) {
            permissionReceiver.OnPermissionResult(0);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(NativeGalleryPermissionFragment.READ_PERMISSION_ONLY, readPermission);
        bundle.putInt(NativeGalleryPermissionFragment.MEDIA_TYPE_ID, mediaType);
        NativeGalleryPermissionFragment nativeGalleryPermissionFragment = new NativeGalleryPermissionFragment(permissionReceiver);
        nativeGalleryPermissionFragment.setArguments(bundle);
        ((Activity) context).getFragmentManager().beginTransaction().add(0, nativeGalleryPermissionFragment).commit();
    }

    public static void OpenSettings(Context context) {
        Uri uriFromParts = Uri.fromParts("package", context.getPackageName(), null);
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(uriFromParts);
        context.startActivity(intent);
    }

    public static String GetMimeTypeFromExtension(String extension) {
        String mimeTypeFromExtension;
        return (extension == null || extension.length() == 0 || (mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase(Locale.ENGLISH))) == null) ? "" : mimeTypeFromExtension;
    }

    public static String LoadImageAtPath(Context context, String path, final String temporaryFilePath, final int maxSize) {
        return NativeGalleryUtils.LoadImageAtPath(context, path, temporaryFilePath, maxSize);
    }

    public static String GetImageProperties(Context context, final String path) {
        return NativeGalleryUtils.GetImageProperties(context, path);
    }

    public static String GetVideoProperties(Context context, final String path) {
        return NativeGalleryUtils.GetVideoProperties(context, path);
    }

    public static String GetVideoThumbnail(Context context, final String path, final String savePath, final boolean saveAsJpeg, int maxSize, double captureTime) {
        return NativeGalleryUtils.GetVideoThumbnail(context, path, savePath, saveAsJpeg, maxSize, captureTime);
    }
}
