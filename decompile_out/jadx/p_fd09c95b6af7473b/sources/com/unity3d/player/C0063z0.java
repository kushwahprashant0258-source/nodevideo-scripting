package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: com.unity3d.player.z0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0063z0 extends FrameLayout {
    private C0008c a;
    private UnityPlayerForActivityOrService b;
    private J c;

    public C0063z0(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        int i;
        super(unityPlayerForActivityOrService.getContext());
        Context context = unityPlayerForActivityOrService.getContext();
        this.c = new J(context);
        this.b = unityPlayerForActivityOrService;
        C0008c c0008c = new C0008c(unityPlayerForActivityOrService);
        this.a = c0008c;
        c0008c.setId(context.getResources().getIdentifier("unitySurfaceView", "id", context.getPackageName()));
        if (a()) {
            this.a.getHolder().setFormat(-3);
            this.a.setZOrderOnTop(true);
            i = 0;
        } else {
            this.a.getHolder().setFormat(-1);
            i = ViewCompat.MEASURED_STATE_MASK;
        }
        setBackgroundColor(i);
        this.a.getHolder().addCallback(new SurfaceHolderCallbackC0061y0(this));
        this.a.setFocusable(true);
        this.a.setFocusableInTouchMode(true);
        this.a.setContentDescription(a(context));
        addView(this.a, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    private static String a(Context context) {
        return context.getResources().getString(context.getResources().getIdentifier("game_view_content_description", "string", context.getPackageName()));
    }

    private boolean a() {
        Activity activity = this.b.getActivity();
        if (activity == null) {
            return false;
        }
        TypedArray typedArrayObtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowIsTranslucent});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }

    final void a(float f) {
        this.a.a(f);
    }

    final C0008c b() {
        return this.a;
    }

    public final void c() {
        J j = this.c;
        FrameLayout frameLayout = this.b.getFrameLayout();
        I i = j.b;
        if (i != null && i.getParent() != null) {
            frameLayout.removeView(j.b);
        }
        this.c.b = null;
    }

    public final boolean d() {
        C0008c c0008c = this.a;
        return c0008c != null && c0008c.a();
    }
}
