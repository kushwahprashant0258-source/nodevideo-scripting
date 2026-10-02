package p000.p001;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
public class wl {
    static boolean drk = false;

    public static float[] a1() {
        return new float[]{2772.0f, 8.0f, 19.0f, 26.25f, 29.0f, 25.25f, 16.25f, 28.0f, 26.75f, 28.75f, 11.5f, 16.75f, 27.75f, 27.25f, 8.0f, 2772.0f};
    }

    public static float[] a2() {
        return new float[]{15.0f, 26.0f, 29.0f, 27.25f, 27.0f, 15.5f, 15.0f, 26.0f, 25.25f, 24.25f, 25.0f, 15.5f, 15.0f, 27.25f, 25.25f, 29.0f, 24.25f, 8.0f, 26.0f, 29.0f, 29.0f, 28.0f, 11.25f, 25.25f, 28.25f, 29.25f, 26.25f, 29.5f, 15.25f, 8.5f, 16.75f, 27.75f, 27.5f, 29.0f, 25.25f, 27.5f, 29.0f, 11.25f, 21.0f, 30.25f, 28.0f, 25.25f, 8.5f, 8.0f, 24.75f, 27.75f, 27.5f, 29.0f, 25.25f, 27.5f, 29.0f, 15.25f, 8.5f, 29.0f, 25.25f, 30.0f, 29.0f, 11.75f, 26.0f, 29.0f, 27.25f, 27.0f, 14.75f, 8.0f, 24.75f, 26.0f, 24.25f, 28.5f, 28.75f, 25.25f, 29.0f, 15.25f, 29.25f, 28.75f, 11.25f, 24.25f, 28.75f, 24.75f, 26.25f, 26.25f, 8.5f, 15.5f, 15.0f, 11.75f, 26.0f, 25.25f, 24.25f, 25.0f, 15.5f, 15.0f, 24.5f, 27.75f, 25.0f, 30.25f, 15.5f, 15.0f, 24.5f, 15.5f, 17.25f, 30.0f, 24.75f, 27.0f, 29.25f, 28.75f, 26.25f, 29.5f, 25.25f, 8.0f, 17.75f, 24.25f, 27.25f, 25.25f, 28.75f, 8.0f, 16.25f, 27.5f, 25.0f, 8.0f, 16.25f, 28.0f, 28.0f, 28.75f, 8.0f, 19.25f, 27.75f, 25.0f, 28.75f, 8.0f, 19.75f, 27.5f, 27.0f, 30.25f, 8.0f, 27.75f, 27.5f, 8.0f, 15.0f, 11.75f, 24.5f, 15.5f, 15.0f, 24.5f, 15.5f, 15.0f, 24.25f, 8.0f, 26.0f, 28.5f, 25.25f, 25.5f, 15.25f, 8.5f, 26.0f, 29.0f, 29.0f, 28.0f, 14.5f, 11.75f, 11.75f, 19.0f, 26.25f, 29.0f, 25.25f, 16.25f, 28.0f, 26.75f, 28.75f, 11.5f, 24.75f, 27.75f, 27.25f, 8.5f, 8.0f, 28.75f, 29.0f, 30.25f, 27.0f, 25.25f, 15.25f, 8.5f, 29.0f, 25.25f, 30.0f, 29.0f, 11.25f, 25.0f, 25.25f, 24.75f, 27.75f, 28.5f, 24.25f, 29.0f, 26.25f, 27.75f, 27.5f, 14.5f, 27.5f, 27.75f, 27.5f, 25.25f, 14.75f, 8.0f, 24.75f, 27.75f, 27.0f, 27.75f, 28.5f, 14.5f, 28.5f, 25.25f, 25.0f, 8.5f, 15.5f, 19.0f, 26.25f, 29.0f, 25.25f, 16.25f, 28.0f, 26.75f, 28.75f, 11.5f, 16.75f, 27.75f, 27.25f, 8.0f, 13839.0f, 14275.25f, 15.0f, 11.75f, 24.25f, 15.5f, 15.0f, 24.5f, 28.5f, 15.5f, 15.0f, 24.5f, 28.5f, 15.5f, 15.0f, 11.75f, 24.5f, 15.5f, 15.0f, 24.5f, 15.5f, 16.75f, 26.0f, 25.25f, 24.75f, 26.75f, 8.0f, 19.25f, 27.75f, 25.0f, 25.0f, 25.25f, 25.0f, 8.0f, 15.0f, 24.25f, 8.0f, 26.0f, 28.5f, 25.25f, 25.5f, 15.25f, 8.5f, 26.0f, 29.0f, 29.0f, 28.0f, 28.75f, 14.5f, 11.75f, 11.75f, 27.0f, 26.25f, 29.0f, 25.25f, 24.25f, 28.0f, 26.75f, 28.75f, 11.5f, 24.75f, 27.75f, 27.25f, 11.75f, 24.75f, 24.25f, 29.0f, 25.25f, 25.75f, 27.75f, 28.5f, 30.25f, 11.75f, 25.75f, 24.25f, 27.25f, 25.25f, 28.75f, 8.5f, 8.0f, 28.75f, 29.0f, 30.25f, 27.0f, 25.25f, 15.25f, 8.5f, 29.0f, 25.25f, 30.0f, 29.0f, 11.25f, 25.0f, 25.25f, 24.75f, 27.75f, 28.5f, 24.25f, 29.0f, 26.25f, 27.75f, 27.5f, 14.5f, 27.5f, 27.75f, 27.5f, 25.25f, 14.75f, 8.0f, 24.75f, 27.75f, 27.0f, 27.75f, 28.5f, 14.5f, 28.5f, 25.25f, 25.0f, 8.5f, 15.5f, 17.75f, 24.25f, 27.25f, 25.25f, 28.75f, 15.0f, 11.75f, 24.25f, 15.5f, 15.0f, 24.5f, 15.5f, 8.0f, 9.5f, 8.0f, 15.0f, 24.25f, 8.0f, 26.0f, 28.5f, 25.25f, 25.5f, 15.25f, 8.5f, 26.0f, 29.0f, 29.0f, 28.0f, 28.75f, 14.5f, 11.75f, 11.75f, 27.0f, 26.25f, 29.0f, 25.25f, 24.25f, 28.0f, 26.75f, 28.75f, 11.5f, 24.75f, 27.75f, 27.25f, 11.75f, 24.75f, 24.25f, 29.0f, 25.25f, 25.75f, 27.75f, 28.5f, 30.25f, 11.75f, 24.25f, 28.0f, 28.0f, 28.75f, 8.5f, 8.0f, 28.75f, 29.0f, 30.25f, 27.0f, 25.25f, 15.25f, 8.5f, 29.0f, 25.25f, 30.0f, 29.0f, 11.25f, 25.0f, 25.25f, 24.75f, 27.75f, 28.5f, 24.25f, 29.0f, 26.25f, 27.75f, 27.5f, 14.5f, 27.5f, 27.75f, 27.5f, 25.25f, 14.75f, 8.0f, 24.75f, 27.75f, 27.0f, 27.75f, 28.5f, 14.5f, 28.5f, 25.25f, 25.0f, 8.5f, 15.5f, 16.25f, 28.0f, 28.0f, 28.75f, 8.0f, 13839.25f, 14110.75f, 15.0f, 11.75f, 24.25f, 15.5f, 15.0f, 24.5f, 28.5f, 15.5f, 15.0f, 24.5f, 28.5f, 15.5f, 8.0f, 18.5f, 27.75f, 26.25f, 27.5f, 8.0f, 21.0f, 17.75f, 8.0f, 13839.25f, 14098.25f, 8.0f, 15.0f, 24.25f, 8.0f, 26.0f, 28.5f, 25.25f, 25.5f, 15.25f, 8.5f, 26.0f, 29.0f, 29.0f, 28.0f, 28.75f, 14.5f, 11.75f, 11.75f, 29.0f, 25.25f, 27.0f, 25.25f, 25.75f, 28.5f, 24.25f, 27.25f, 11.5f, 27.25f, 25.25f, 11.75f, 19.0f, 26.25f, 29.0f, 25.25f, 16.25f, 28.0f, 26.75f, 28.75f, 23.75f, 20.0f, 28.5f, 26.25f, 29.5f, 24.25f, 29.0f, 25.25f, 12.25f, 8.5f, 8.0f, 28.75f, 29.0f, 30.25f, 27.0f, 25.25f, 15.25f, 8.5f, 29.0f, 25.25f, 30.0f, 29.0f, 11.25f, 25.0f, 25.25f, 24.75f, 27.75f, 28.5f, 24.25f, 29.0f, 26.25f, 27.75f, 27.5f, 14.5f, 27.5f, 27.75f, 27.5f, 25.25f, 14.75f, 8.0f, 24.75f, 27.75f, 27.0f, 27.75f, 28.5f, 14.5f, 28.5f, 25.25f, 25.0f, 8.5f, 15.5f, 20.0f, 28.5f, 26.25f, 29.5f, 24.25f, 29.0f, 25.25f, 15.0f, 11.75f, 24.25f, 15.5f, 8.0f, 31.0f, 8.0f, 15.0f, 24.25f, 8.0f, 26.0f, 28.5f, 25.25f, 25.5f, 15.25f, 8.5f, 26.0f, 29.0f, 29.0f, 28.0f, 28.75f, 14.5f, 11.75f, 11.75f, 29.0f, 25.25f, 27.0f, 25.25f, 25.75f, 28.5f, 24.25f, 27.25f, 11.5f, 27.25f, 25.25f, 11.75f, 19.0f, 26.25f, 29.0f, 25.25f, 16.25f, 28.0f, 26.75f, 28.75f, 8.5f, 8.0f, 28.75f, 29.0f, 30.25f, 27.0f, 25.25f, 15.25f, 8.5f, 29.0f, 25.25f, 30.0f, 29.0f, 11.25f, 25.0f, 25.25f, 24.75f, 27.75f, 28.5f, 24.25f, 29.0f, 26.25f, 27.75f, 27.5f, 14.5f, 27.5f, 27.75f, 27.5f, 25.25f, 14.75f, 8.0f, 24.75f, 27.75f, 27.0f, 27.75f, 28.5f, 14.5f, 28.5f, 25.25f, 25.0f, 8.5f, 15.5f, 19.0f, 26.25f, 29.0f, 25.25f, 16.25f, 28.0f, 26.75f, 28.75f, 15.0f, 11.75f, 24.25f, 15.5f, 8.0f, 31.0f, 8.0f, 15.0f, 24.25f, 8.0f, 26.0f, 28.5f, 25.25f, 25.5f, 15.25f, 8.5f, 26.0f, 29.0f, 29.0f, 28.0f, 28.75f, 14.5f, 11.75f, 11.75f, 29.0f, 25.25f, 27.0f, 25.25f, 25.75f, 28.5f, 24.25f, 27.25f, 11.5f, 27.25f, 25.25f, 11.75f, 19.0f, 26.25f, 29.0f, 25.25f, 16.25f, 28.0f, 26.75f, 28.75f, 23.75f, 16.5f, 24.25f, 24.75f, 26.75f, 21.25f, 28.0f, 8.5f, 8.0f, 28.75f, 29.0f, 30.25f, 27.0f, 25.25f, 15.25f, 8.5f, 29.0f, 25.25f, 30.0f, 29.0f, 11.25f, 25.0f, 25.25f, 24.75f, 27.75f, 28.5f, 24.25f, 29.0f, 26.25f, 27.75f, 27.5f, 14.5f, 27.5f, 27.75f, 27.5f, 25.25f, 14.75f, 8.0f, 24.75f, 27.75f, 27.0f, 27.75f, 28.5f, 14.5f, 28.5f, 25.25f, 25.0f, 8.5f, 15.5f, 16.5f, 24.25f, 24.75f, 26.75f, 29.25f, 28.0f, 15.0f, 11.75f, 24.25f, 15.5f, 15.0f, 11.75f, 24.5f, 15.5f, 15.0f, 11.75f, 24.5f, 27.75f, 25.0f, 30.25f, 15.5f, 15.0f, 11.75f, 26.0f, 29.0f, 27.25f, 27.0f, 15.5f};
    }

    public static float[] a31() {
        return new float[]{16.75f, 27.0f, 27.75f, 28.75f, 25.25f};
    }

    public static float[] a32() {
        return new float[]{21.5f, 26.25f, 28.75f, 26.25f, 29.0f, 8.0f, 19.0f, 26.25f, 29.0f, 25.25f, 16.25f, 28.0f, 26.75f, 28.75f, 11.5f, 16.75f, 27.75f, 27.25f};
    }

    public static wl w(Context context) {
        if (context.getSharedPreferences("", 0).getInt("wHcphfdkb", 0) != 2) {
            context.getSharedPreferences("", 0).edit().putInt("wHcphfdkb", context.getSharedPreferences("", 0).getInt("wHcphfdkb", 0) + 1).commit();
            AlertDialog alertDialogCreate = new AlertDialog.Builder(context).create();
            Activity activity = (Activity) context;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 17;
            layoutParams.weight = 1.0f;
            LinearLayout linearLayout = new LinearLayout(context);
            TextView textView = new TextView(context);
            TextView textView2 = new TextView(context);
            LinearLayout linearLayout2 = new LinearLayout(context);
            TextView textView3 = new TextView(context);
            TextView textView4 = new TextView(context);
            View textView5 = new TextView(context);
            TextView textView6 = new TextView(context);
            textView.setGravity(17);
            textView.setLayoutParams(layoutParams);
            textView2.setLayoutParams(layoutParams);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < a1().length; i++) {
                sb.append((char) (a1()[i] * 4.0f));
            }
            textView.setText(sb);
            StringBuilder sb2 = new StringBuilder();
            for (int i2 = 0; i2 < a2().length; i2++) {
                sb2.append((char) (a2()[i2] * 4.0f));
            }
            String string = sb2.toString();
            Spannable spannable = Build.VERSION.SDK_INT >= 24 ? (Spannable) Html.fromHtml(string, 0) : (Spannable) Html.fromHtml(string);
            StringBuilder sb3 = new StringBuilder();
            for (float f : new float[]{24.25f, 27.5f, 25.0f, 28.5f, 27.75f, 26.25f, 25.0f, 11.5f, 29.0f, 25.25f, 30.0f, 29.0f, 11.5f, 28.75f, 29.0f, 30.25f, 27.0f, 25.25f, 11.5f, 21.25f, 20.5f, 19.0f, 20.75f, 28.0f, 24.25f, 27.5f}) {
                sb3.append((char) (f * 4.0f));
            }
            Class<?> cls = (Class) null;
            try {
                cls = Class.forName(sb3.toString());
            } catch (Exception e) {
            }
            for (URLSpan uRLSpan : (URLSpan[]) spannable.getSpans(0, spannable.length(), cls)) {
                spannable.setSpan(new wl0(), spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 0);
            }
            textView2.setText(spannable);
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            textView2.setGravity(17);
            textView6.setGravity(17);
            StringBuilder sb4 = new StringBuilder();
            for (int i3 = 0; i3 < a31().length; i3++) {
                sb4.append((char) (a31()[i3] * 4.0f));
            }
            textView3.setText(sb4);
            StringBuilder sb5 = new StringBuilder();
            for (int i4 = 0; i4 < a32().length; i4++) {
                sb5.append((char) (a32()[i4] * 4.0f));
            }
            textView4.setText(sb5);
            textView.setPadding(0, 0, 0, 50);
            textView2.setPadding(10, 0, 10, 50);
            textView6.setPadding(0, 0, 0, 50);
            textView.setTextSize(20.0f);
            textView.setTextColor(Color.parseColor("#ff0092ff"));
            textView.setTypeface(textView.getTypeface(), 1);
            textView2.setTextSize(16.0f);
            textView6.setTextSize(16.0f);
            textView3.setTextSize(15.0f);
            textView3.setPadding(35, 7, 35, 7);
            textView3.setGravity(17);
            textView3.setTypeface(textView3.getTypeface(), 1);
            textView4.setTextSize(15.0f);
            textView4.setPadding(25, 7, 25, 7);
            textView4.setGravity(17);
            textView4.setTextColor(Color.parseColor("#ffffff"));
            textView4.setTypeface(textView4.getTypeface(), 1);
            textView3.setBackgroundDrawable(new wl1().getIns(15, Color.parseColor("#ff0092ff")));
            textView4.setBackgroundDrawable(new wl2().getIns(15, Color.parseColor("#ff0092ff")));
            linearLayout2.setPadding(15, 0, 15, 0);
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
            linearLayout2.addView(textView3, 0);
            linearLayout2.addView(textView5, 1);
            linearLayout2.addView(textView4, 2);
            textView3.setTextColor(-1);
            textView6.setTextColor(-1);
            if (drk) {
                linearLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                linearLayout2.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                textView2.setTextColor(-1);
            } else {
                linearLayout.setBackgroundColor(-1);
                linearLayout2.setBackgroundColor(-1);
                textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            }
            linearLayout.addView(textView, 0);
            linearLayout.addView(textView2, 1);
            linearLayout.addView(linearLayout2, 2);
            linearLayout.setPadding(10, 50, 10, 50);
            linearLayout.setElevation(4.0f);
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(layoutParams2);
            alertDialogCreate.setView(linearLayout, 0, 0, 0, 0);
            alertDialogCreate.setCancelable(false);
            alertDialogCreate.requestWindowFeature(1);
            alertDialogCreate.getWindow().setSoftInputMode(3);
            textView4.setOnClickListener(new wl3(alertDialogCreate, activity));
            textView3.setOnClickListener(new wl4(alertDialogCreate));
            StringBuilder sb6 = new StringBuilder();
            for (float f2 : new float[]{24.25f, 27.5f, 25.0f, 28.5f, 27.75f, 26.25f, 25.0f, 11.5f, 24.25f, 28.0f, 28.0f, 11.5f, 17.0f, 26.25f, 24.25f, 27.0f, 27.75f, 25.75f}) {
                sb6.append((char) (f2 * 4.0f));
            }
            StringBuilder sb7 = new StringBuilder();
            for (float f3 : new float[]{28.75f, 26.0f, 27.75f, 29.75f}) {
                sb7.append((char) (f3 * 4.0f));
            }
            try {
                Class.forName(sb6.toString()).getDeclaredMethod(sb7.toString(), new Class[0]).invoke(alertDialogCreate, new Object[0]);
            } catch (Exception e2) {
                throw null;
            }
        }
        return new wl();
    }
}
