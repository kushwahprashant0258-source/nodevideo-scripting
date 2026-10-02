package com.unity3d.player;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
final class T extends Dialog implements View.OnClickListener {
    protected Context a;
    protected UnityPlayerForActivityOrService b;
    protected O c;
    protected S d;

    public T(Context context, UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        super(context);
        this.c = null;
        this.d = null;
        this.a = context;
        this.b = unityPlayerForActivityOrService;
    }

    public final Rect a() {
        Rect rect = new Rect();
        FrameLayout frameLayout = this.b.getFrameLayout();
        frameLayout.getWindowVisibleDisplayFrame(rect);
        int[] iArr = new int[2];
        frameLayout.getLocationOnScreen(iArr);
        Point point = new Point(rect.left - iArr[0], rect.height() - this.c.getHeight());
        Point point2 = new Point();
        getWindow().getWindowManager().getDefaultDisplay().getSize(point2);
        int height = frameLayout.getHeight();
        int i = height - point2.y;
        int i2 = height - point.y;
        int height2 = this.c.getHeight() + i;
        UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.b;
        if (i2 != height2) {
            unityPlayerForActivityOrService.reportSoftInputIsVisible(true);
        } else {
            unityPlayerForActivityOrService.reportSoftInputIsVisible(false);
        }
        return new Rect(point.x, point.y, this.c.getWidth(), i2);
    }

    public final void a(S s, boolean z, boolean z2) {
        this.d = s;
        Window window = getWindow();
        window.requestFeature(1);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        b();
        this.c = createSoftInputView(this.d.c);
        window.setLayout(-1, -2);
        window.clearFlags(2);
        window.clearFlags(134217728);
        window.clearFlags(67108864);
        if (!z2) {
            window.addFlags(32);
            window.addFlags(262144);
        }
        a(z);
        getWindow().setSoftInputMode(5);
    }

    public final void a(boolean z) {
        O o = this.c;
        if (z) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) o.b.getLayoutParams();
            layoutParams.height = 1;
            o.b.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) o.a.getLayoutParams();
            layoutParams2.height = 1;
            o.a.setLayoutParams(layoutParams2);
            Rect rect = o.e;
            o.setPadding(rect.left, rect.top, rect.right, rect.bottom);
            o.setVisibility(4);
        } else {
            o.setVisibility(0);
            Rect rect2 = o.d;
            o.setPadding(rect2.left, rect2.top, rect2.right, rect2.bottom);
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) o.b.getLayoutParams();
            layoutParams3.height = -2;
            o.b.setLayoutParams(layoutParams3);
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) o.a.getLayoutParams();
            layoutParams4.height = -2;
            o.a.setLayoutParams(layoutParams4);
        }
        o.invalidate();
        o.requestLayout();
    }

    public final void b() {
        ColorDrawable colorDrawable = new ColorDrawable(0);
        if (!PlatformSupport.VANILLA_ICE_CREAM_SUPPORT) {
            getWindow().setBackgroundDrawable(colorDrawable);
            return;
        }
        Insets insets = this.b.getActivity().getWindow().getDecorView().getRootWindowInsets().getInsets(WindowInsets.Type.displayCutout());
        getWindow().setBackgroundDrawable(new InsetDrawable((Drawable) colorDrawable, insets.left, insets.top, insets.right, 0));
    }

    protected O createSoftInputView(EditText editText) {
        O o = new O(this.a, editText);
        o.a.setOnClickListener(this);
        setContentView(o);
        return o;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.d.c() || !(motionEvent.getAction() == 4 || this.d.e)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        this.d.d();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        S s = this.d;
        s.a(s.a(), false);
    }

    @Override // android.app.Dialog
    public final void onStop() {
        this.d.e();
        super.onStop();
    }
}
