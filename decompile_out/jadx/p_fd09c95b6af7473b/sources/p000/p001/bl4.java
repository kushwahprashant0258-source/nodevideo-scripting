package p000.p001;

import android.app.AlertDialog;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class bl4 implements View.OnClickListener {
    private final AlertDialog val$create;

    bl4(AlertDialog alertDialog) {
        this.val$create = alertDialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.val$create.dismiss();
    }
}
