package com.yasirkula.unity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class NativeShare {
    private static String authority;

    public static void Share(Context context, String str, String str2, String[] strArr, String[] strArr2, String str3, String str4, String str5) {
        String str6;
        String mimeTypeFromExtension;
        int iIndexOf;
        if (strArr.length > 0 && GetAuthority(context) == null) {
            Log.e("Unity", "Can't find ContentProvider, share not possible!");
            return;
        }
        Intent intent = new Intent();
        if (str3.length() > 0) {
            intent.putExtra("android.intent.extra.SUBJECT", str3);
        }
        if (str4.length() > 0) {
            intent.putExtra("android.intent.extra.TEXT", str4);
        }
        if (strArr.length > 0) {
            String str7 = null;
            String str8 = null;
            for (int i = 0; i < strArr.length; i++) {
                if (strArr2[i].length() > 0) {
                    mimeTypeFromExtension = strArr2[i];
                } else {
                    int iLastIndexOf = strArr[i].lastIndexOf(46);
                    if (iLastIndexOf >= 0 && iLastIndexOf != strArr.length - 1) {
                        mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(strArr[i].substring(iLastIndexOf + 1).toLowerCase(Locale.ENGLISH));
                    }
                    str7 = "*";
                    str8 = str7;
                    break;
                }
                if (mimeTypeFromExtension != null && mimeTypeFromExtension.length() != 0 && (iIndexOf = mimeTypeFromExtension.indexOf(47)) > 0 && iIndexOf != mimeTypeFromExtension.length() - 1) {
                    String strSubstring = mimeTypeFromExtension.substring(0, iIndexOf);
                    String strSubstring2 = mimeTypeFromExtension.substring(iIndexOf + 1);
                    if (str7 == null) {
                        str7 = strSubstring;
                    } else if (!str7.equals(strSubstring)) {
                    }
                    if (str8 == null) {
                        str8 = strSubstring2;
                    } else if (!str8.equals(strSubstring2)) {
                        str8 = "*";
                    }
                }
                str7 = "*";
                str8 = str7;
            }
            str6 = str7 + "/" + str8;
            if (strArr.length == 1) {
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.STREAM", NativeShareContentProvider.getUriForFile(context, authority, new File(strArr[0])));
            } else {
                intent.setAction("android.intent.action.SEND_MULTIPLE");
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>(strArr.length);
                for (String str9 : strArr) {
                    arrayList.add(NativeShareContentProvider.getUriForFile(context, authority, new File(str9)));
                }
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            }
        } else {
            intent.setAction("android.intent.action.SEND");
            str6 = "text/plain";
        }
        if (str5.length() > 0) {
            intent.putExtra("android.intent.extra.TITLE", str5);
        }
        intent.setType(str6);
        intent.setFlags(1);
        if (str.length() > 0) {
            intent.setPackage(str);
            if (str2.length() > 0) {
                intent.setClassName(str, str2);
            }
        }
        if (context.getPackageManager().queryIntentActivities(intent, 65536).size() == 1) {
            context.startActivity(intent);
        } else {
            context.startActivity(Intent.createChooser(intent, str5));
        }
    }

    private static String GetAuthority(Context context) {
        if (authority == null) {
            try {
                ProviderInfo[] providerInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers;
                if (providerInfoArr != null) {
                    for (ProviderInfo providerInfo : providerInfoArr) {
                        if (providerInfo.name != null && providerInfo.packageName != null && providerInfo.authority != null && providerInfo.name.equals(NativeShareContentProvider.class.getName()) && providerInfo.packageName.equals(context.getPackageName()) && providerInfo.authority.length() > 0) {
                            authority = providerInfo.authority;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("Unity", "Exception:", e);
            }
        }
        return authority;
    }

    public static boolean TargetExists(Context context, String str, String str2) {
        try {
            if (str2.length() == 0) {
                context.getPackageManager().getPackageInfo(str, 0);
                return true;
            }
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(str, 1).activities;
            if (activityInfoArr != null) {
                for (ActivityInfo activityInfo : activityInfoArr) {
                    if (activityInfo.name.equals(str2)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static String FindMatchingTarget(Context context, String str, String str2) {
        ActivityInfo[] activityInfoArr;
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(1);
        if (installedPackages != null) {
            Pattern patternCompile = Pattern.compile(str);
            Pattern patternCompile2 = str2.length() > 0 ? Pattern.compile(str2) : null;
            for (PackageInfo packageInfo : installedPackages) {
                if (patternCompile.matcher(packageInfo.packageName).find() && (activityInfoArr = packageInfo.activities) != null) {
                    for (ActivityInfo activityInfo : activityInfoArr) {
                        if (patternCompile2 == null || patternCompile2.matcher(activityInfo.name).find()) {
                            return packageInfo.packageName + ">" + activityInfo.name;
                        }
                    }
                }
            }
            return "";
        }
        return "";
    }
}
