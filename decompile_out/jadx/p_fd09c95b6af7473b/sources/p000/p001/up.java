package p000.p001;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextPaint;
import android.text.style.UnderlineSpan;

/* JADX INFO: loaded from: classes.dex */
public class up {

    static class ctr {
        public String[] en() {
            return new String[]{"true", "false", "false"};
        }
    }

    static class ok {
        public float[] bb() {
            return new float[]{27.0f, 26.25f, 29.0f, 25.25f, 24.25f, 28.0f, 26.75f, 28.75f, 11.5f, 24.75f, 27.75f, 27.25f};
        }

        public float[] u() {
            return new float[]{10.0f, 11.5f, 10.5f, 10.25f, 21.25f, 28.5f, 27.0f, 15.25f, 10.0f, 11.5f, 10.5f, 10.25f};
        }

        public float[] v() {
            return new float[]{10.0f, 11.5f, 10.5f, 10.25f, 21.5f, 25.25f, 28.5f, 28.75f, 26.25f, 27.75f, 27.5f, 15.25f, 10.0f, 11.5f, 10.5f, 10.25f};
        }

        public float[] vc() {
            return new float[]{10.0f, 11.5f, 10.5f, 10.25f, 16.75f, 27.75f, 25.0f, 25.25f, 15.25f, 10.0f, 11.5f, 10.5f, 10.25f};
        }
    }

    static class und extends UnderlineSpan {
        @Override // android.text.style.UnderlineSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    static float[] bytess() {
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [ī.íì.up$100000000] */
    /* JADX WARN: Type inference failed for: r0v12, types: [ī.íì.up$100000004] */
    /* JADX WARN: Type inference failed for: r0v15, types: [ī.íì.up$100000005] */
    /* JADX WARN: Type inference failed for: r0v3, types: [ī.íì.up$100000001] */
    /* JADX WARN: Type inference failed for: r0v6, types: [ī.íì.up$100000002] */
    /* JADX WARN: Type inference failed for: r0v9, types: [ī.íì.up$100000003] */
    public static up process(Context context) {
        float[] fArrNum = new Object() { // from class: ī.íì.up.100000000
            public float[] num() {
                return new float[]{17.75f, 27.75f, 27.0f, 25.0f, 25.25f, 27.5f, 16.5f, 27.75f, 27.75f, 29.0f};
            }
        }.num();
        StringBuilder sb = new StringBuilder();
        for (float f : fArrNum) {
            sb.append((char) (f * 4.0f));
        }
        float[] fArrNum2 = new Object() { // from class: ī.íì.up.100000001
            public float[] num2() {
                return new float[]{2772.0f, 14.25f, 19.25f, 19.75f, 17.0f, 11.5f, 16.75f, 19.75f, 19.25f, 2772.0f};
            }
        }.num2();
        StringBuilder sb2 = new StringBuilder();
        for (float f2 : fArrNum2) {
            sb2.append((char) (f2 * 4.0f));
        }
        float[] fArrNum3 = new Object() { // from class: ī.íì.up.100000002
            public float[] num3() {
                return new float[]{21.25f, 28.0f, 25.0f, 24.25f, 29.0f, 25.25f, 8.0f, 17.5f, 27.75f, 29.25f, 27.5f, 25.0f};
            }
        }.num3();
        StringBuilder sb3 = new StringBuilder();
        for (float f3 : fArrNum3) {
            sb3.append((char) (f3 * 4.0f));
        }
        float[] fArrNum4 = new Object() { // from class: ī.íì.up.100000003
            public float[] num3() {
                return new float[]{21.25f, 28.0f, 25.0f, 24.25f, 29.0f, 25.25f, 8.0f, 17.5f, 27.75f, 29.25f, 27.5f, 25.0f};
            }

            public float[] num4() {
                return new float[]{17.0f, 25.25f, 24.25f, 28.5f, 8.0f, 21.25f, 28.75f, 25.25f, 28.5f, 11.0f, 8.0f, 19.5f, 25.25f, 29.75f, 8.0f, 19.25f, 27.75f, 25.0f, 8.0f, 21.5f, 25.25f, 28.5f, 28.75f, 26.25f, 27.75f, 27.5f, 8.0f};
            }
        }.num4();
        StringBuilder sb4 = new StringBuilder();
        for (float f4 : fArrNum4) {
            sb4.append((char) (f4 * 4.0f));
        }
        float[] fArrNum5 = new Object() { // from class: ī.íì.up.100000004
            public float[] num5() {
                return new float[]{8.0f, 18.25f, 28.75f, 8.0f, 28.5f, 25.25f, 27.0f, 25.25f, 24.25f, 28.75f, 25.25f, 25.0f, 11.0f, 8.0f, 28.0f, 27.0f, 25.25f, 24.25f, 28.75f, 25.25f, 8.0f, 29.25f, 28.0f, 25.0f, 24.25f, 29.0f, 25.25f, 8.0f, 29.0f, 27.75f, 8.0f, 24.25f, 24.75f, 26.0f, 26.25f, 25.25f, 29.5f, 25.25f, 8.0f, 24.25f, 8.0f, 24.5f, 25.25f, 29.0f, 29.0f, 25.25f, 28.5f, 8.0f, 25.25f, 30.0f, 28.0f, 25.25f, 28.5f, 26.25f, 25.25f, 27.5f, 24.75f, 25.25f};
            }
        }.num5();
        StringBuilder sb5 = new StringBuilder();
        for (float f5 : fArrNum5) {
            sb5.append((char) (f5 * 4.0f));
        }
        float[] fArrNum6 = new Object() { // from class: ī.íì.up.100000005
            public float[] num6() {
                return new float[]{26.0f, 29.0f, 29.0f, 28.0f, 28.75f, 14.5f, 11.75f, 11.75f, 14.25f, 27.25f, 27.75f, 25.0f, 11.5f, 24.75f, 27.75f, 27.25f};
            }
        }.num6();
        StringBuilder sb6 = new StringBuilder();
        for (float f6 : fArrNum6) {
            sb6.append((char) (f6 * 4.0f));
        }
        StringBuilder sb7 = new StringBuilder();
        for (float f7 : new float[]{16.75f, 19.0f, 19.75f, 20.75f, 17.25f}) {
            sb7.append((char) (f7 * 4.0f));
        }
        StringBuilder sb8 = new StringBuilder();
        for (float f8 : new float[]{21.25f, 20.0f, 17.0f, 16.25f, 21.0f, 17.25f}) {
            sb8.append((char) (f8 * 4.0f));
        }
        StringBuilder sb9 = new StringBuilder();
        for (float f9 : bytess()) {
            sb9.append((char) (f9 * 4.0f));
        }
        if (!context.getSharedPreferences("", 0).getBoolean("dont", false)) {
            HandlerThread handlerThread = new HandlerThread(sb.toString());
            handlerThread.start();
            new Handler(handlerThread.getLooper()).post(new up1(context, sb9, sb6, sb2, sb4, sb5, sb7, sb8));
        }
        return new up();
    }
}
