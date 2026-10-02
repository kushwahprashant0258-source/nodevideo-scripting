package com.yasirkula.unity;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class NativeGalleryUtils {
    private static int isXiaomiOrMIUI;
    private static String secondaryStoragePath;

    public static boolean IsXiaomiOrMIUI() throws Throwable {
        int i = isXiaomiOrMIUI;
        if (i > 0) {
            return true;
        }
        if (i < 0) {
            return false;
        }
        if ("xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            isXiaomiOrMIUI = 1;
            return true;
        }
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ro.miui.ui.version.name").getInputStream()), 1024);
                try {
                    String line = bufferedReader2.readLine();
                    if (line != null && line.length() > 0) {
                        isXiaomiOrMIUI = 1;
                        try {
                            bufferedReader2.close();
                        } catch (Exception unused) {
                        }
                        return true;
                    }
                    isXiaomiOrMIUI = -1;
                    try {
                        bufferedReader2.close();
                    } catch (Exception unused2) {
                    }
                    return false;
                } catch (Exception unused3) {
                    bufferedReader = bufferedReader2;
                    isXiaomiOrMIUI = -1;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception unused4) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception unused5) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused6) {
        }
    }

    public static String GetPathFromURI(Context context, Uri uri) throws Throwable {
        String str;
        String[] strArr;
        Cursor cursorQuery;
        String string;
        if (uri == null) {
            return null;
        }
        try {
            if (!DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
                str = null;
                strArr = null;
            } else {
                if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                    String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
                    if ("primary".equalsIgnoreCase(strArrSplit[0])) {
                        return Environment.getExternalStorageDirectory() + File.separator + strArrSplit[1];
                    }
                    if ("raw".equalsIgnoreCase(strArrSplit[0])) {
                        return strArrSplit[1];
                    }
                    return GetSecondaryStoragePathFor(strArrSplit[1]);
                }
                if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    String documentId = DocumentsContract.getDocumentId(uri);
                    if (documentId.startsWith("raw:")) {
                        return documentId.substring(4);
                    }
                    if (documentId.indexOf(58) >= 0) {
                        return null;
                    }
                    uri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(documentId));
                } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
                    String str2 = strArrSplit2[0];
                    if ("image".equals(str2)) {
                        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str2)) {
                        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(str2)) {
                        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    } else if ("raw".equals(str2)) {
                        return strArrSplit2[1];
                    }
                    str = "_id=?";
                    strArr = new String[]{strArrSplit2[1]};
                }
                str = null;
                strArr = null;
            }
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                try {
                    cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
                    if (cursorQuery != null) {
                        try {
                            int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_data");
                            if (cursorQuery.moveToFirst() && (string = cursorQuery.getString(columnIndexOrThrow)) != null && string.length() > 0) {
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                                return string;
                            }
                        } catch (Exception unused) {
                            if (cursorQuery != null) {
                            }
                            return null;
                        } catch (Throwable th) {
                            th = th;
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Exception unused2) {
                    cursorQuery = null;
                } catch (Throwable th2) {
                    th = th2;
                    cursorQuery = null;
                }
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
            return null;
        } catch (Exception e) {
            Log.e("Unity", "Exception:", e);
            return null;
        }
    }

    private static String GetSecondaryStoragePathFor(String localPath) {
        String str = secondaryStoragePath;
        if (str == null) {
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String str2 = System.getenv("SECONDARY_STORAGE");
            if (str2 == null || str2.length() == 0) {
                str2 = System.getenv("EXTERNAL_SDCARD_STORAGE");
            }
            if (str2 != null && str2.length() > 0) {
                if (!str2.contains(":")) {
                    str2 = str2 + ":";
                }
                for (String str3 : str2.split(":")) {
                    if (str3 != null && str3.length() > 0) {
                        File file = new File(str3);
                        if (file.exists() && file.isDirectory() && file.canRead() && !file.getAbsolutePath().equalsIgnoreCase(absolutePath)) {
                            String str4 = file.getAbsolutePath() + File.separator + localPath;
                            if (new File(str4).exists()) {
                                secondaryStoragePath = file.getAbsolutePath();
                                return str4;
                            }
                        }
                    }
                }
            }
            String[] strArr = {"/storage", "/mnt", "/storage/removable", "/removable", "/data", "/mnt/media_rw", "/mnt/sdcard0"};
            for (int i = 0; i < 7; i++) {
                try {
                    for (File file2 : new File(strArr[i]).listFiles()) {
                        if (file2.exists() && file2.isDirectory() && file2.canRead() && !file2.getAbsolutePath().equalsIgnoreCase(absolutePath)) {
                            String str5 = file2.getAbsolutePath() + File.separator + localPath;
                            if (new File(str5).exists()) {
                                secondaryStoragePath = file2.getAbsolutePath();
                                return str5;
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
            secondaryStoragePath = "_NulL_";
            return null;
        }
        if (str.equals("_NulL_")) {
            return null;
        }
        return secondaryStoragePath + File.separator + localPath;
    }

    public static boolean WriteFileToStream(File file, OutputStream out) {
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i > 0) {
                            out.write(bArr, 0, i);
                        } else {
                            try {
                                break;
                            } catch (Exception e) {
                                Log.e("Unity", "Exception:", e);
                            }
                        }
                    }
                    fileInputStream.close();
                    try {
                        out.close();
                        return true;
                    } catch (Exception e2) {
                        Log.e("Unity", "Exception:", e2);
                        return true;
                    }
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e3) {
                        Log.e("Unity", "Exception:", e3);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    out.close();
                } catch (Exception e4) {
                    Log.e("Unity", "Exception:", e4);
                }
                throw th2;
            }
        } catch (Exception e5) {
            Log.e("Unity", "Exception:", e5);
            try {
                out.close();
            } catch (Exception e6) {
                Log.e("Unity", "Exception:", e6);
            }
            return false;
        }
    }

    private static BitmapFactory.Options GetImageMetadata(final String path) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            return options;
        } catch (Exception e) {
            Log.e("Unity", "Exception:", e);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x006f A[PHI: r2
      0x006f: PHI (r2v3 android.database.Cursor) = (r2v2 android.database.Cursor), (r2v4 android.database.Cursor) binds: [B:39:0x006d, B:33:0x0063] A[DONT_GENERATE, DONT_INLINE]] */
    public static int GetImageOrientation(Context context, final String path) {
        try {
            int attributeInt = new ExifInterface(path).getAttributeInt("Orientation", 0);
            if (attributeInt != 0) {
                return attributeInt;
            }
        } catch (Exception unused) {
        }
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(Uri.fromFile(new File(path)), new String[]{"orientation"}, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                int i = cursorQuery.getInt(cursorQuery.getColumnIndex("orientation"));
                if (i == 90) {
                    if (cursorQuery == null) {
                        return 6;
                    }
                    cursorQuery.close();
                    return 6;
                }
                if (i == 180) {
                    if (cursorQuery == null) {
                        return 3;
                    }
                    cursorQuery.close();
                    return 3;
                }
                if (i != 270) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return 1;
                }
                if (cursorQuery == null) {
                    return 8;
                }
                cursorQuery.close();
                return 8;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception unused2) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
        return 0;
    }

    private static Matrix GetImageOrientationCorrectionMatrix(final int orientation, final float scale) {
        Matrix matrix = new Matrix();
        switch (orientation) {
            case 2:
                matrix.postScale(-scale, scale);
                return matrix;
            case 3:
                matrix.postRotate(180.0f);
                matrix.postScale(scale, scale);
                return matrix;
            case 4:
                matrix.postScale(scale, -scale);
                return matrix;
            case 5:
                matrix.postRotate(90.0f);
                matrix.postScale(-scale, scale);
                return matrix;
            case 6:
                matrix.postRotate(90.0f);
                matrix.postScale(scale, scale);
                return matrix;
            case 7:
                matrix.postRotate(270.0f);
                matrix.postScale(-scale, scale);
                return matrix;
            case 8:
                matrix.postRotate(270.0f);
                matrix.postScale(scale, scale);
                return matrix;
            default:
                matrix.postScale(scale, scale);
                return matrix;
        }
    }

    /* JADX WARN: Code duplicated, block: B:105:0x010f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x010a  */
    public static String LoadImageAtPath(Context context, String path, final String temporaryFilePath, final int maxSize) throws Throwable {
        FileOutputStream fileOutputStream;
        BitmapFactory.Options optionsGetImageMetadata = GetImageMetadata(path);
        if (optionsGetImageMetadata == null) {
            return path;
        }
        boolean z = optionsGetImageMetadata.outWidth > maxSize || optionsGetImageMetadata.outHeight > maxSize;
        if (optionsGetImageMetadata.outMimeType != null && !optionsGetImageMetadata.outMimeType.equals("image/jpeg") && !optionsGetImageMetadata.outMimeType.equals("image/png")) {
            z = true;
        }
        int iGetImageOrientation = GetImageOrientation(context, path);
        if ((iGetImageOrientation == 1 || iGetImageOrientation == 0) && !z) {
            return path;
        }
        Bitmap bitmap = null;
        try {
            int i = optionsGetImageMetadata.outHeight / 2;
            int i2 = optionsGetImageMetadata.outWidth / 2;
            int i3 = 1;
            while (true) {
                if (i / i3 < maxSize && i2 / i3 < maxSize) {
                    break;
                }
                i3 *= 2;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = i3;
            options.inJustDecodeBounds = false;
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(path, options);
            try {
                float width = bitmapDecodeFile.getWidth() > maxSize ? maxSize / bitmapDecodeFile.getWidth() : 1.0f;
                float height = bitmapDecodeFile.getHeight() > maxSize ? maxSize / bitmapDecodeFile.getHeight() : 1.0f;
                if (width >= height) {
                    width = height;
                }
                if (width < 1.0f || (iGetImageOrientation != 1 && iGetImageOrientation != 0)) {
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), GetImageOrientationCorrectionMatrix(iGetImageOrientation, width), true);
                    if (bitmapCreateBitmap != bitmapDecodeFile) {
                        bitmapDecodeFile.recycle();
                        bitmapDecodeFile = bitmapCreateBitmap;
                    }
                }
                fileOutputStream = new FileOutputStream(temporaryFilePath);
                try {
                    if (optionsGetImageMetadata.outMimeType == null || !optionsGetImageMetadata.outMimeType.equals("image/jpeg")) {
                        bitmapDecodeFile.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    } else {
                        bitmapDecodeFile.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    }
                    if (bitmapDecodeFile != null) {
                        bitmapDecodeFile.recycle();
                    }
                    try {
                        fileOutputStream.close();
                    } catch (Exception unused) {
                    }
                    return temporaryFilePath;
                } catch (Exception e) {
                    e = e;
                    bitmap = bitmapDecodeFile;
                    try {
                        Log.e("Unity", "Exception:", e);
                        try {
                            File file = new File(temporaryFilePath);
                            if (file.exists()) {
                                file.delete();
                            }
                        } catch (Exception unused2) {
                        }
                        if (bitmap != null) {
                            bitmap.recycle();
                        }
                        if (fileOutputStream == null) {
                            return path;
                        }
                        try {
                            fileOutputStream.close();
                            return path;
                        } catch (Exception unused3) {
                            return path;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (bitmap != null) {
                            bitmap.recycle();
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception unused4) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bitmap = bitmapDecodeFile;
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0027 A[PHI: r7
      0x0027: PHI (r7v2 int) = (r7v0 int), (r7v1 int) binds: [B:15:0x0025, B:18:0x002b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:21:0x0030 A[PHI: r8
      0x0030: PHI (r8v2 int) = (r8v0 int), (r8v1 int) binds: [B:20:0x002e, B:23:0x0033] A[DONT_GENERATE, DONT_INLINE]] */
    public static String GetImageProperties(Context context, final String path) {
        BitmapFactory.Options optionsGetImageMetadata = GetImageMetadata(path);
        if (optionsGetImageMetadata == null) {
            return "";
        }
        int i = optionsGetImageMetadata.outWidth;
        int i2 = optionsGetImageMetadata.outHeight;
        String str = optionsGetImageMetadata.outMimeType;
        String str2 = str != null ? str : "";
        int iGetImageOrientation = GetImageOrientation(context, path);
        int i3 = -1;
        if (iGetImageOrientation != 0) {
            int i4 = 1;
            if (iGetImageOrientation == 1) {
                i3 = 0;
            } else if (iGetImageOrientation == 6) {
                i3 = i4;
            } else {
                i4 = 2;
                int i5 = 3;
                if (iGetImageOrientation == 3) {
                    i3 = i4;
                } else if (iGetImageOrientation == 8) {
                    i3 = i5;
                } else {
                    i5 = 4;
                    if (iGetImageOrientation == 2) {
                        i3 = i5;
                    } else if (iGetImageOrientation == 5) {
                        i3 = 5;
                    } else if (iGetImageOrientation == 4) {
                        i3 = 6;
                    } else if (iGetImageOrientation == 7) {
                        i3 = 7;
                    }
                }
            }
        }
        if (iGetImageOrientation == 6 || iGetImageOrientation == 8 || iGetImageOrientation == 5 || iGetImageOrientation == 7) {
            i2 = i;
            i = i2;
        }
        return i + ">" + i2 + ">" + str2 + ">" + i3;
    }

    public static String GetVideoProperties(Context context, final String path) throws IOException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(path);
            String strExtractMetadata = mediaMetadataRetriever.extractMetadata(18);
            String strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
            String strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(9);
            String strExtractMetadata4 = mediaMetadataRetriever.extractMetadata(24);
            if (strExtractMetadata == null) {
                strExtractMetadata = "0";
            }
            if (strExtractMetadata2 == null) {
                strExtractMetadata2 = "0";
            }
            if (strExtractMetadata3 == null) {
                strExtractMetadata3 = "0";
            }
            if (strExtractMetadata4 == null) {
                strExtractMetadata4 = "0";
            }
            return strExtractMetadata + ">" + strExtractMetadata2 + ">" + strExtractMetadata3 + ">" + strExtractMetadata4;
        } catch (Exception e) {
            Log.e("Unity", "Exception:", e);
            return "";
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    /* JADX WARN: Code duplicated, block: B:113:0x0110 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:119:0x0104 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:121:0x0056 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:0x0053  */
    /* JADX WARN: Code duplicated, block: B:36:0x007b  */
    /* JADX WARN: Code duplicated, block: B:40:0x0083  */
    /* JADX WARN: Code duplicated, block: B:41:0x0085  */
    /* JADX WARN: Code duplicated, block: B:46:0x009b  */
    /* JADX WARN: Code duplicated, block: B:50:0x00aa A[Catch: all -> 0x00c3, TRY_LEAVE, TryCatch #2 {all -> 0x00c3, blocks: (B:28:0x005b, B:30:0x0060, B:48:0x00a4, B:50:0x00aa, B:42:0x0087), top: B:105:0x005b }] */
    /* JADX WARN: Code duplicated, block: B:52:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00d3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:70:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:72:0x00d9 A[Catch: all -> 0x00c9, Exception -> 0x00cd, TRY_ENTER, TRY_LEAVE, TryCatch #15 {Exception -> 0x00cd, all -> 0x00c9, blocks: (B:27:0x0056, B:55:0x00bd, B:60:0x00c5, B:61:0x00c8, B:72:0x00d9), top: B:121:0x0056 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x00e2 A[Catch: all -> 0x00f6, Exception -> 0x00f8, TRY_ENTER, TryCatch #14 {Exception -> 0x00f8, all -> 0x00f6, blocks: (B:75:0x00e2, B:76:0x00e8), top: B:122:0x00e0 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x00e8 A[Catch: all -> 0x00f6, Exception -> 0x00f8, TRY_LEAVE, TryCatch #14 {Exception -> 0x00f8, all -> 0x00f6, blocks: (B:75:0x00e2, B:76:0x00e8), top: B:122:0x00e0 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:93:0x010b  */
    public static String GetVideoThumbnail(Context context, final String path, final String savePath, final boolean saveAsJpeg, int maxSize, double captureTime) throws Throwable {
        Bitmap scaledFrameAtTime;
        FileOutputStream fileOutputStream;
        Throwable th;
        Exception e;
        MediaMetadataRetriever mediaMetadataRetriever;
        MediaMetadataRetriever mediaMetadataRetriever2;
        int i;
        double d;
        long j;
        int i2;
        Bitmap bitmapCreateVideoThumbnail;
        Bitmap bitmap = null;
        if (captureTime >= 0.0d) {
            scaledFrameAtTime = null;
            if (scaledFrameAtTime == null) {
                mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(path);
                i2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                int i3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                if (maxSize > i2) {
                    i2 = maxSize;
                } else {
                    i2 = maxSize;
                }
                i = i2;
                if (captureTime < 0.0d) {
                    d = 0.0d;
                } else {
                    d = Long.parseLong(mediaMetadataRetriever.extractMetadata(9)) / 1000.0d;
                    if (captureTime <= d) {
                        d = captureTime;
                    }
                }
                j = (long) (d * 1000000.0d);
                if (Build.VERSION.SDK_INT < 27) {
                    scaledFrameAtTime = mediaMetadataRetriever.getFrameAtTime(j, 2);
                    mediaMetadataRetriever2 = mediaMetadataRetriever;
                } else {
                    mediaMetadataRetriever2 = mediaMetadataRetriever;
                    scaledFrameAtTime = mediaMetadataRetriever.getScaledFrameAtTime(j, 2, i, i);
                }
                mediaMetadataRetriever2.release();
            }
            if (scaledFrameAtTime == null) {
                if (scaledFrameAtTime != null) {
                    scaledFrameAtTime.recycle();
                }
                return "";
            }
            fileOutputStream = new FileOutputStream(savePath);
            if (saveAsJpeg) {
                scaledFrameAtTime.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            } else {
                scaledFrameAtTime.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            }
            if (scaledFrameAtTime != null) {
                scaledFrameAtTime.recycle();
            }
            fileOutputStream.close();
            return savePath;
        }
        try {
            if (maxSize <= 1024) {
                try {
                    if (Build.VERSION.SDK_INT < 29) {
                        bitmapCreateVideoThumbnail = ThumbnailUtils.createVideoThumbnail(path, maxSize > 512 ? 2 : 1);
                    } else {
                        bitmapCreateVideoThumbnail = ThumbnailUtils.createVideoThumbnail(new File(path), maxSize > 512 ? new Size(1024, 786) : new Size(512, 384), null);
                    }
                    scaledFrameAtTime = bitmapCreateVideoThumbnail;
                } catch (Exception e2) {
                    try {
                        Log.e("Unity", "Exception:", e2);
                        scaledFrameAtTime = null;
                    } catch (Exception e3) {
                        e = e3;
                        fileOutputStream = null;
                        try {
                            Log.e("Unity", "Exception:", e);
                            if (bitmap != null) {
                                bitmap.recycle();
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception unused) {
                                }
                            }
                            return "";
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
            } else {
                scaledFrameAtTime = null;
            }
            if (scaledFrameAtTime == null) {
                try {
                    mediaMetadataRetriever = new MediaMetadataRetriever();
                    try {
                        mediaMetadataRetriever.setDataSource(path);
                        try {
                            i2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                            int i4 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                            if (maxSize > i2 || maxSize <= i4) {
                                i2 = maxSize;
                            } else if (i2 <= i4) {
                                i2 = i4;
                            }
                            i = i2;
                        } catch (Exception unused2) {
                            i = maxSize;
                        }
                        if (captureTime < 0.0d) {
                            d = 0.0d;
                        } else {
                            try {
                                d = Long.parseLong(mediaMetadataRetriever.extractMetadata(9)) / 1000.0d;
                                if (captureTime <= d) {
                                    d = captureTime;
                                }
                            } catch (Exception unused3) {
                            }
                        }
                        j = (long) (d * 1000000.0d);
                        if (Build.VERSION.SDK_INT < 27) {
                            scaledFrameAtTime = mediaMetadataRetriever.getFrameAtTime(j, 2);
                            mediaMetadataRetriever2 = mediaMetadataRetriever;
                        } else {
                            mediaMetadataRetriever2 = mediaMetadataRetriever;
                            try {
                                scaledFrameAtTime = mediaMetadataRetriever.getScaledFrameAtTime(j, 2, i, i);
                            } catch (Throwable th3) {
                                th = th3;
                                mediaMetadataRetriever2.release();
                                throw th;
                            }
                        }
                        mediaMetadataRetriever2.release();
                    } catch (Throwable th4) {
                        th = th4;
                        mediaMetadataRetriever2 = mediaMetadataRetriever;
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileOutputStream = null;
                    bitmap = scaledFrameAtTime;
                    Log.e("Unity", "Exception:", e);
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return "";
                } catch (Throwable th5) {
                    th = th5;
                    fileOutputStream = null;
                    bitmap = scaledFrameAtTime;
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused4) {
                        }
                    }
                    throw th;
                }
            }
            if (scaledFrameAtTime == null) {
                if (scaledFrameAtTime != null) {
                    scaledFrameAtTime.recycle();
                }
                return "";
            }
            fileOutputStream = new FileOutputStream(savePath);
            try {
                if (saveAsJpeg) {
                    scaledFrameAtTime.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                } else {
                    scaledFrameAtTime.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                }
                if (scaledFrameAtTime != null) {
                    scaledFrameAtTime.recycle();
                }
                try {
                    fileOutputStream.close();
                } catch (Exception unused5) {
                }
                return savePath;
            } catch (Exception e5) {
                e = e5;
                bitmap = scaledFrameAtTime;
                Log.e("Unity", "Exception:", e);
                if (bitmap != null) {
                    bitmap.recycle();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return "";
            } catch (Throwable th6) {
                th = th6;
                bitmap = scaledFrameAtTime;
                if (bitmap != null) {
                    bitmap.recycle();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
            fileOutputStream = null;
        }
        if (bitmap != null) {
            bitmap.recycle();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        throw th;
    }
}
