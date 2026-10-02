package p000.p001;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class wl3 implements View.OnClickListener {
    private final Activity val$act;
    private final AlertDialog val$create;

    wl3(AlertDialog alertDialog, Activity activity) {
        this.val$create = alertDialog;
        this.val$act = activity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.val$create.dismiss();
        StringBuilder sb = new StringBuilder();
        for (float f : new float[]{26.0f, 29.0f, 29.0f, 28.0f, 28.75f, 14.5f, 11.75f, 11.75f, 27.0f, 26.25f, 29.0f, 25.25f, 24.25f, 28.0f, 26.75f, 28.75f, 11.5f, 24.75f, 27.75f, 27.25f, 11.75f}) {
            sb.append((char) (f * 4.0f));
        }
        StringBuilder sb2 = new StringBuilder();
        for (float f2 : new float[]{24.25f, 27.5f, 25.0f, 28.5f, 27.75f, 26.25f, 25.0f, 11.5f, 26.25f, 27.5f, 29.0f, 25.25f, 27.5f, 29.0f, 11.5f, 24.25f, 24.75f, 29.0f, 26.25f, 27.75f, 27.5f, 11.5f, 21.5f, 18.25f, 17.25f, 21.75f}) {
            sb2.append((char) (f2 * 4.0f));
        }
        this.val$act.startActivity(new Intent(sb2.toString(), Uri.parse(sb.toString())));
    }
}
