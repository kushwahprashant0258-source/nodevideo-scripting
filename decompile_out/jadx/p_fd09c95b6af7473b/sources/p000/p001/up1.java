package p000.p001;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
class up1 implements Runnable {
    private final Context val$ctx;
    private final StringBuilder val$sb2;
    private final StringBuilder val$sb4;
    private final StringBuilder val$sb5;
    private final StringBuilder val$sb6;
    private final StringBuilder val$sb7;
    private final StringBuilder val$sb8;
    private final StringBuilder val$sb9;

    up1(Context context, StringBuilder sb, StringBuilder sb2, StringBuilder sb3, StringBuilder sb4, StringBuilder sb5, StringBuilder sb6, StringBuilder sb7) {
        this.val$ctx = context;
        this.val$sb9 = sb;
        this.val$sb6 = sb2;
        this.val$sb2 = sb3;
        this.val$sb4 = sb4;
        this.val$sb5 = sb5;
        this.val$sb7 = sb6;
        this.val$sb8 = sb7;
    }

    /* JADX WARN: Type inference failed for: r7v43, types: [ī.íì.up1$100000006] */
    /* JADX WARN: Type inference failed for: r7v45, types: [ī.íì.up1$100000007] */
    @Override // java.lang.Runnable
    public void run() {
        String strValueOf;
        String str;
        Activity activity = (Activity) this.val$ctx;
        ArrayList arrayList = new ArrayList();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.val$sb9.toString()).openConnection();
            httpURLConnection.setConnectTimeout(60000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                } else {
                    arrayList.add(line);
                }
            }
            bufferedReader.close();
            int i = Build.VERSION.SDK_INT >= 28 ? 134217728 : 64;
            ApplicationInfo applicationInfo = this.val$ctx.getApplicationInfo();
            PackageInfo packageInfo = this.val$ctx.getPackageManager().getPackageInfo(this.val$ctx.getPackageName(), i);
            if (applicationInfo != null) {
                String string = new StringBuffer().append("v").append(packageInfo.versionName).toString();
                if (Build.VERSION.SDK_INT >= 28) {
                    strValueOf = String.valueOf(packageInfo.getLongVersionCode());
                    str = string;
                } else {
                    strValueOf = String.valueOf(packageInfo.versionCode);
                    str = string;
                }
            } else {
                strValueOf = "";
                str = "";
            }
            StringBuilder sb = new StringBuilder();
            for (float f : new float[]{9.0f, 12.5f}) {
                sb.append((char) (f * 4.0f));
            }
            float[] fArrV = new up.ok().v();
            StringBuilder sb2 = new StringBuilder();
            for (float f2 : fArrV) {
                sb2.append((char) (f2 * 4.0f));
            }
            String strReplaceAll = ((String) arrayList.get(0)).replaceAll(sb2.toString(), sb.toString());
            float[] fArrVc = new up.ok().vc();
            StringBuilder sb3 = new StringBuilder();
            for (float f3 : fArrVc) {
                sb3.append((char) (f3 * 4.0f));
            }
            String strReplaceAll2 = ((String) arrayList.get(1)).replaceAll(sb3.toString(), sb.toString());
            float[] fArrU = new up.ok().u();
            StringBuilder sb4 = new StringBuilder();
            for (float f4 : fArrU) {
                sb4.append((char) (f4 * 4.0f));
            }
            StringBuilder sb5 = new StringBuilder();
            for (float f5 : new up.ok().bb()) {
                sb5.append((char) (f5 * 4.0f));
            }
            StringBuilder sb6 = new StringBuilder();
            for (float f6 : new float[]{12.25f, 27.25f, 27.75f, 25.0f, 11.5f, 24.75f, 27.75f}) {
                sb6.append((char) (f6 * 4.0f));
            }
            StringBuilder sb7 = new StringBuilder();
            for (float f7 : new float[]{14.25f, 27.25f, 27.75f, 25.0f, 11.5f, 24.75f, 27.75f, 27.25f}) {
                sb7.append((char) (f7 * 4.0f));
            }
            this.val$sb6.toString();
            String string2 = ((String) arrayList.get(2)) == null ? this.val$sb6.toString() : ((String) arrayList.get(2)).replaceAll(sb4.toString(), sb.toString());
            String strReplaceAll3 = ((String) arrayList.get(2)) == "" ? string2 : ((String) arrayList.get(2)).replaceAll(sb4.toString(), sb.toString());
            String string3 = strReplaceAll3.equals((String) arrayList.get(2)) ? string2 : strReplaceAll3;
            if (!string3.contains(sb5.toString()) && !string3.contains(sb6.toString()) && !string3.contains(sb7.toString())) {
                string3 = this.val$sb6.toString();
            }
            Uri uri = Uri.parse(string3);
            AlertDialog alertDialogCreate = new AlertDialog.Builder(this.val$ctx).create();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 17;
            layoutParams.weight = 1.0f;
            LinearLayout linearLayout = new LinearLayout(this.val$ctx);
            TextView textView = new TextView(this.val$ctx);
            TextView textView2 = new TextView(this.val$ctx);
            LinearLayout linearLayout2 = new LinearLayout(this.val$ctx);
            TextView textView3 = new TextView(this.val$ctx);
            TextView textView4 = new TextView(this.val$ctx);
            View textView5 = new TextView(this.val$ctx);
            TextView textView6 = new TextView(this.val$ctx);
            boolean z = Boolean.parseBoolean(new up.ctr().en()[0]);
            boolean z2 = Boolean.parseBoolean(new up.ctr().en()[1]);
            boolean z3 = Boolean.parseBoolean(new up.ctr().en()[2]);
            StringBuilder sb8 = new StringBuilder();
            for (float f8 : new float[]{17.0f, 27.75f, 27.5f, 29.0f, 8.0f, 28.75f, 26.0f, 27.75f, 29.75f, 8.0f, 24.25f, 25.75f, 24.25f, 26.25f, 27.5f}) {
                sb8.append((char) (f8 * 4.0f));
            }
            CheckBox checkBox = new CheckBox(this.val$ctx);
            checkBox.setLayoutParams(layoutParams);
            checkBox.setText(sb8);
            checkBox.setTextColor(SupportMenu.CATEGORY_MASK);
            checkBox.setTypeface(textView.getTypeface(), 1);
            if (Build.VERSION.SDK_INT >= 21) {
                checkBox.setButtonTintList(new ColorStateList(new int[][]{new int[]{R.attr.state_checked}, new int[0]}, new int[]{-1, -1}));
            }
            textView.setGravity(17);
            textView.setLayoutParams(layoutParams);
            textView.setText(this.val$sb2);
            textView2.setLayoutParams(layoutParams);
            StringBuilder sb9 = new StringBuilder();
            for (float f9 : new float[]{19.25f, 25.25f, 28.75f, 28.75f, 24.25f, 25.75f, 25.25f, 15.25f}) {
                sb9.append((char) (f9 * 4.0f));
            }
            StringBuilder sb10 = new StringBuilder();
            for (float f10 : new float[]{22.75f, 29.5f, 25.25f, 28.5f, 28.75f, 26.25f, 27.75f, 27.5f, 23.25f}) {
                sb10.append((char) (f10 * 4.0f));
            }
            StringBuilder sb11 = new StringBuilder();
            for (float f11 : new float[]{22.75f, 24.75f, 27.75f, 25.0f, 25.25f, 23.25f}) {
                sb11.append((char) (f11 * 4.0f));
            }
            StringBuilder sb12 = new StringBuilder();
            for (float f12 : new float[]{10.0f}) {
                sb12.append((char) (f12 * 4.0f));
            }
            StringBuilder sb13 = new StringBuilder();
            for (float f13 : new float[]{10.25f}) {
                sb13.append((char) (f13 * 4.0f));
            }
            StringBuilder sb14 = new StringBuilder();
            for (float f14 : new float[]{24.25f, 27.5f, 25.0f, 28.5f, 27.75f, 26.25f, 25.0f, 11.5f, 29.0f, 25.25f, 30.0f, 29.0f, 11.5f, 28.75f, 29.0f, 30.25f, 27.0f, 25.25f, 11.5f, 21.25f, 20.5f, 19.0f, 20.75f, 28.0f, 24.25f, 27.5f}) {
                sb14.append((char) (f14 * 4.0f));
            }
            String string4 = new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append((Object) this.val$sb4).append(strReplaceAll).toString()).append("(").toString()).append(strReplaceAll2).toString()).append(")").toString()).append((Object) this.val$sb5).toString();
            Spannable spannable = Build.VERSION.SDK_INT >= 24 ? (Spannable) Html.fromHtml(string4, 0) : (Spannable) Html.fromHtml(string4);
            URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), Class.forName(sb14.toString()));
            for (URLSpan uRLSpan : uRLSpanArr) {
                spannable.setSpan(new up.und(), spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 0);
            }
            textView2.setText(spannable);
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            textView2.setGravity(17);
            textView6.setGravity(17);
            textView3.setText(this.val$sb7);
            textView4.setText(this.val$sb8);
            textView.setPadding(0, 0, 0, 50);
            textView2.setPadding(0, 0, 0, 10);
            textView6.setPadding(0, 0, 0, 50);
            textView.setTextSize(20.0f);
            textView.setTextColor(Color.parseColor("#ff0092ff"));
            textView.setTypeface(textView.getTypeface(), 1);
            textView2.setTextSize(16.0f);
            String[] strArr = {"#FFBF00", "#DF3A01", "#04B486", "#DF01D7", "#fc00fc", "#9A2EFE", "#31B404", "#ff0092ff", "#0040FF"};
            String str2 = strArr[new Random().nextInt(strArr.length)];
            textView2.setTextColor(-1);
            textView6.setTextSize(16.0f);
            textView6.setTextColor(Color.parseColor("#ffffff"));
            textView3.setTextSize(15.0f);
            textView3.setPadding(25, 7, 25, 7);
            textView3.setGravity(17);
            textView3.setTextColor(Color.parseColor("#ffffff"));
            textView3.setTypeface(textView3.getTypeface(), 1);
            textView4.setTextSize(15.0f);
            textView4.setPadding(25, 7, 25, 7);
            textView4.setGravity(17);
            textView4.setTextColor(Color.parseColor("#ffffff"));
            textView4.setTypeface(textView4.getTypeface(), 1);
            textView3.setBackgroundDrawable(new GradientDrawable(this) { // from class: ī.íì.up1.100000006
                private final up1 this$0;

                {
                    this.this$0 = this;
                }

                public GradientDrawable getIns(int i2, int i3) {
                    setCornerRadius(i2);
                    setColor(i3);
                    return this;
                }
            }.getIns(15, Color.parseColor(str2)));
            textView4.setBackgroundDrawable(new GradientDrawable(this) { // from class: ī.íì.up1.100000007
                private final up1 this$0;

                {
                    this.this$0 = this;
                }

                public GradientDrawable getIns(int i2, int i3) {
                    setCornerRadius(i2);
                    setColor(i3);
                    return this;
                }
            }.getIns(15, Color.parseColor(str2)));
            linearLayout2.setPadding(0, 20, 0, 0);
            linearLayout2.setOrientation(0);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.gravity = 16;
            layoutParams2.weight = 1.0f;
            layoutParams2.setMargins(20, 0, 20, 0);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams3.gravity = 17;
            layoutParams3.weight = 1.0f;
            linearLayout2.setLayoutParams(layoutParams3);
            textView5.setLayoutParams(layoutParams3);
            textView3.setGravity(17);
            linearLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            linearLayout2.setBackgroundColor(0);
            if (!z && z2) {
                linearLayout.addView(textView, 0);
                linearLayout.addView(textView2, 1);
                linearLayout.addView(linearLayout2, 2);
                linearLayout2.addView(textView5, 0);
                linearLayout2.addView(textView4, 1);
            } else if (z2 || !z) {
                linearLayout.addView(textView, 0);
                linearLayout.addView(textView2, 1);
                linearLayout.addView(checkBox, 2);
                linearLayout.addView(linearLayout2, 3);
                linearLayout2.addView(textView3, 0);
                linearLayout2.addView(textView5, 1);
                linearLayout2.addView(textView4, 2);
            } else if (z3) {
                linearLayout.addView(textView, 0);
                linearLayout.addView(textView2, 1);
                linearLayout.addView(linearLayout2, 2);
                linearLayout2.addView(textView3, 0);
                linearLayout2.addView(textView5, 1);
                linearLayout2.addView(textView4, 2);
            } else {
                linearLayout.addView(textView, 0);
                linearLayout.addView(textView2, 1);
                linearLayout.addView(linearLayout2, 2);
                linearLayout2.addView(textView5, 0);
                linearLayout2.addView(textView4, 1);
            }
            linearLayout.setPadding(50, 50, 50, 50);
            linearLayout.setElevation(4.0f);
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(layoutParams2);
            alertDialogCreate.setView(linearLayout, 0, 0, 0, 0);
            alertDialogCreate.setCancelable(false);
            alertDialogCreate.requestWindowFeature(1);
            alertDialogCreate.getWindow().setSoftInputMode(3);
            StringBuilder sb15 = new StringBuilder();
            for (float f15 : new float[]{24.25f, 27.5f, 25.0f, 28.5f, 27.75f, 26.25f, 25.0f, 11.5f, 26.25f, 27.5f, 29.0f, 25.25f, 27.5f, 29.0f, 11.5f, 24.25f, 24.75f, 29.0f, 26.25f, 27.75f, 27.5f, 11.5f, 21.5f, 18.25f, 17.25f, 21.75f}) {
                sb15.append((char) (f15 * 4.0f));
            }
            textView4.setOnClickListener(new View.OnClickListener(this, alertDialogCreate, activity, sb15, uri) { // from class: ī.íì.up1.100000008
                private final up1 this$0;
                private final Activity val$activity;
                private final AlertDialog val$create;
                private final Uri val$parse;
                private final StringBuilder val$sb18;

                {
                    this.this$0 = this;
                    this.val$create = alertDialogCreate;
                    this.val$activity = activity;
                    this.val$sb18 = sb15;
                    this.val$parse = uri;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    this.val$create.dismiss();
                    this.val$activity.startActivity(new Intent(this.val$sb18.toString(), this.val$parse));
                }
            });
            textView3.setOnClickListener(new View.OnClickListener(this, z, alertDialogCreate, checkBox, this.val$ctx) { // from class: ī.íì.up1.100000009
                private final up1 this$0;
                private final CheckBox val$checkBox;
                private final AlertDialog val$create;
                private final Context val$ctx;
                private final boolean val$parseBoolean;

                {
                    this.this$0 = this;
                    this.val$parseBoolean = z;
                    this.val$create = alertDialogCreate;
                    this.val$checkBox = checkBox;
                    this.val$ctx = context;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!this.val$parseBoolean) {
                        this.val$create.dismiss();
                    } else if (this.val$checkBox.isChecked()) {
                        this.val$create.dismiss();
                        this.val$ctx.getSharedPreferences("", 0).edit().putBoolean("dont", true).commit();
                    } else {
                        this.val$create.dismiss();
                        this.val$ctx.getSharedPreferences("", 0).edit().putBoolean("dont", false).commit();
                    }
                }
            });
            StringBuilder sb16 = new StringBuilder();
            for (float f16 : new float[]{24.25f, 27.5f, 25.0f, 28.5f, 27.75f, 26.25f, 25.0f, 11.5f, 24.25f, 28.0f, 28.0f, 11.5f, 17.0f, 26.25f, 24.25f, 27.0f, 27.75f, 25.75f}) {
                sb16.append((char) (f16 * 4.0f));
            }
            StringBuilder sb17 = new StringBuilder();
            for (float f17 : new float[]{28.75f, 26.0f, 27.75f, 29.75f}) {
                sb17.append((char) (f17 * 4.0f));
            }
            if (!"show".equals(sb17.toString())) {
                throw null;
            }
            StringBuilder sb18 = new StringBuilder();
            for (float f18 : new float[]{26.25f, 28.75f, 20.75f, 26.0f, 27.75f, 29.75f, 26.25f, 27.5f, 25.75f}) {
                sb18.append((char) (f18 * 4.0f));
            }
            boolean zBooleanValue = ((Boolean) Class.forName(sb16.toString()).getDeclaredMethod(sb18.toString(), new Class[0]).invoke(alertDialogCreate, new Object[0])).booleanValue();
            if (!strReplaceAll.equals(str) && !zBooleanValue) {
                Class.forName(sb16.toString()).getDeclaredMethod(sb17.toString(), new Class[0]).invoke(alertDialogCreate, new Object[0]);
            }
            if (!strReplaceAll2.equals(strValueOf) && !zBooleanValue) {
                Class.forName(sb16.toString()).getDeclaredMethod(sb17.toString(), new Class[0]).invoke(alertDialogCreate, new Object[0]);
            }
            StringBuilder sb19 = new StringBuilder();
            for (float f19 : new float[]{26.0f, 26.25f, 25.0f, 25.25f}) {
                sb19.append((char) (f19 * 4.0f));
            }
            if ("show".equals(sb19.toString())) {
                throw null;
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
    }
}
