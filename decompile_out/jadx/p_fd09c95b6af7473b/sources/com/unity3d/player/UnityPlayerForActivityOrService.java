package com.unity3d.player;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Looper;
import android.provider.Settings;
import android.view.Surface;
import android.view.WindowInsets;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class UnityPlayerForActivityOrService extends UnityPlayer {
    private boolean mMainDisplayOverride;
    private D mOnHandleFocusListener;
    private boolean mProcessKillRequested;
    private S mSoftInput;
    private long mSoftInputTimeoutMilliSeconds;
    private int m_IsNoWindowMode;
    private C0 m_MainThread;

    public enum MemoryUsage {
        Low(1),
        Medium(2),
        High(3),
        Critical(4);

        public final int value;

        MemoryUsage(int i) {
            this.value = i;
        }
    }

    public enum SynchronizationTimeout {
        Pause(0),
        SurfaceDetach(1),
        Destroy(2);

        private int m_TimeoutMilliseconds = 2000;
        final int value;

        SynchronizationTimeout(int i) {
            this.value = i;
        }

        public static void setTimeoutForAll(int i) {
            for (SynchronizationTimeout synchronizationTimeout : (SynchronizationTimeout[]) SynchronizationTimeout.class.getEnumConstants()) {
                synchronizationTimeout.setTimeout(i);
            }
        }

        public int getTimeout() {
            return this.m_TimeoutMilliseconds;
        }

        public void setTimeout(int i) {
            this.m_TimeoutMilliseconds = i;
        }
    }

    public UnityPlayerForActivityOrService(Context context) {
        this(context, null);
    }

    public UnityPlayerForActivityOrService(Context context, IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents) {
        super(context, EnumC0052u.b, iUnityPlayerLifecycleEvents);
        this.m_MainThread = new C0(this);
        this.mMainDisplayOverride = false;
        this.mSoftInput = null;
        this.m_IsNoWindowMode = -1;
        this.mProcessKillRequested = true;
        this.mSoftInputTimeoutMilliSeconds = 1000L;
        C0056w c0056w = new C0056w(context, this);
        c0056w.setOnApplyWindowInsetsListener(new ViewOnApplyWindowInsetsListenerC0019f1(this));
        initialize(c0056w);
        this.m_MainThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissSoftInput() {
        S s = this.mSoftInput;
        if (s != null) {
            s.b();
            nativeReportKeyboardConfigChanged();
        }
    }

    private long getSoftInputTimeout() {
        return Math.round(this.mSoftInputTimeoutMilliSeconds * ((double) Math.max(1.0f, Settings.System.getFloat(this.mContext.getContentResolver(), "animator_duration_scale", 0.0f))));
    }

    private final native boolean nativeDone();

    private final native boolean nativeGetNoWindowMode();

    private final native void nativeMemoryUsageChanged(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeOnApplyWindowInsets(WindowInsets windowInsets);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativePause();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeRecreateGfxState(int i, Surface surface);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeReportKeyboardConfigChanged();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeResume();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSendSurfaceChangedEvent();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputArea(int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputSelection(int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputString(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetKeyboardIsVisible(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputCanceled();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputClosed();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputLostFocus();

    /* JADX INFO: Access modifiers changed from: private */
    public void queueDestroy() {
        AbstractC0060y.Log(4, "Queue Destroy");
        runOnUiThread(new RunnableC0016e1(this));
    }

    private void raiseFocusListener(boolean z) {
        D d = this.mOnHandleFocusListener;
        if (d != null) {
            V0 v0 = ((T0) d).a;
            v0.b = true;
            if (v0.a) {
                v0.c.release();
            }
        }
    }

    private boolean updateDisplayInternal(int i, Surface surface) {
        if (!isNativeInitialized()) {
            return false;
        }
        Semaphore semaphore = new Semaphore(0);
        k1 k1Var = new k1(this, i, surface, semaphore);
        if (i == 0) {
            C0 c0 = this.m_MainThread;
            if (surface == null) {
                c0.d(k1Var);
            } else {
                c0.b(k1Var);
            }
        } else {
            k1Var.run();
        }
        if (surface != null || i != 0) {
            return true;
        }
        try {
            SynchronizationTimeout synchronizationTimeout = SynchronizationTimeout.SurfaceDetach;
            if (semaphore.tryAcquire(synchronizationTimeout.getTimeout(), TimeUnit.MILLISECONDS)) {
                return true;
            }
            AbstractC0060y.Log(5, "Timeout (" + synchronizationTimeout.getTimeout() + " ms) while trying detaching primary window.");
            return true;
        } catch (InterruptedException unused) {
            AbstractC0060y.Log(5, "UI thread got interrupted while trying to detach the primary window from the Unity Engine.");
            return true;
        }
    }

    @Override // com.unity3d.player.UnityPlayer
    void cleanupResourcesForDestroy() {
        this.m_MainThread.c();
        try {
            this.m_MainThread.join(SynchronizationTimeout.Destroy.getTimeout());
        } catch (InterruptedException unused) {
            this.m_MainThread.interrupt();
        }
        if (w1.d()) {
            getFrameLayout().removeAllViews();
        }
        if (this.mProcessKillRequested) {
            this.m_UnityPlayerLifecycleEvents.onUnityPlayerQuitted();
            kill();
        }
    }

    @Override // com.unity3d.player.UnityPlayer
    public void configurationChanged(Configuration configuration) {
        super.configurationChanged(configuration);
        if (isNativeInitialized()) {
            invokeOnMainThread(new RunnableC0028i1(this, new Configuration(configuration)));
        }
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.mMainDisplayOverride = surface != null;
            runOnUiThread(new l1(this));
        }
        return updateDisplayInternal(i, surface);
    }

    boolean getHaveAndroidWindowSupport() {
        if (this.m_IsNoWindowMode == -1) {
            this.m_IsNoWindowMode = nativeGetNoWindowMode() ? 1 : 0;
        }
        return this.m_IsNoWindowMode == 1;
    }

    @Override // com.unity3d.player.UnityPlayer
    public C0008c getSurfaceView() {
        return getView().b();
    }

    @Override // com.unity3d.player.UnityPlayer
    public C0063z0 getView() {
        return ((C0056w) getFrameLayout()).a();
    }

    @Override // com.unity3d.player.UnityPlayer
    boolean handleFocus(boolean z) {
        S s;
        if (!this.mState.a() || ((s = this.mSoftInput) != null && !s.c())) {
            raiseFocusListener(z);
            return false;
        }
        C0 c0 = this.m_MainThread;
        if (z) {
            c0.a();
        } else {
            c0.b();
        }
        raiseFocusListener(z);
        return true;
    }

    @Override // com.unity3d.player.UnityPlayer
    void hidePreservedContent() {
        runOnUiThread(new n1(this));
    }

    protected void hideSoftInput() {
        if (this.mSoftInput == null) {
            return;
        }
        reportSoftInputArea(new Rect());
        reportSoftInputIsVisible(false);
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            dismissSoftInput();
            this.mSoftInput = null;
            return;
        }
        Semaphore semaphore = new Semaphore(0);
        postOnUiThread(new V0(this, semaphore, this));
        try {
            try {
                if (!semaphore.tryAcquire(getSoftInputTimeout(), TimeUnit.MILLISECONDS)) {
                    AbstractC0060y.Log(6, "Timeout (" + getSoftInputTimeout() + " ms) while waiting softinput hiding operation.");
                }
            } catch (InterruptedException unused) {
                AbstractC0060y.Log(6, "UI thread got interrupted while waiting softinput hiding operation.");
            }
        } finally {
            this.mSoftInput = null;
        }
    }

    final native void nativeConfigurationChanged(Configuration configuration);

    final native void nativeFocusChanged(boolean z);

    final native void nativeOrientationChanged(int i, int i2);

    final native boolean nativeRender();

    @Override // com.unity3d.player.UnityPlayer
    void onOrientationChanged(int i, int i2) {
        this.m_MainThread.a(this.mNaturalOrientation, i2);
    }

    public void onTrimMemory(MemoryUsage memoryUsage) {
        if (w1.d()) {
            nativeMemoryUsageChanged(memoryUsage.value);
        }
    }

    @Override // com.unity3d.player.UnityPlayer
    void pauseUnity() {
        super.pauseUnity();
        reportSoftInputStr(null, 1, true);
        this.mState.c(false);
        this.mState.e(true);
        if (w1.d()) {
            Semaphore semaphore = new Semaphore(0);
            this.m_MainThread.a(isFinishing() ? new RunnableC0022g1(this, semaphore) : new RunnableC0025h1(this, semaphore));
            try {
                SynchronizationTimeout synchronizationTimeout = SynchronizationTimeout.Pause;
                if (!semaphore.tryAcquire(synchronizationTimeout.getTimeout(), TimeUnit.MILLISECONDS)) {
                    AbstractC0060y.Log(5, "Timeout (" + synchronizationTimeout.getTimeout() + " ms) while trying to pause the Unity Engine.");
                }
            } catch (InterruptedException unused) {
                AbstractC0060y.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
            }
        }
        if (this.m_AddPhoneCallListener) {
            this.m_TelephonyManager.listen(this.m_PhoneCallListener, 0);
        }
    }

    protected void reportSoftInputArea(Rect rect) {
        invokeOnMainThread((Runnable) new C0010c1(this, rect));
    }

    protected void reportSoftInputIsVisible(boolean z) {
        invokeOnMainThread((Runnable) new C0013d1(this, z));
    }

    protected void reportSoftInputSelection(int i, int i2) {
        invokeOnMainThread((Runnable) new C0007b1(this, i, i2));
    }

    protected void reportSoftInputStr(String str, int i, boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        invokeOnMainThread((Runnable) new C0004a1(this, z, str, i));
    }

    @Override // com.unity3d.player.UnityPlayer
    void resumeUnity() {
        super.resumeUnity();
        invokeOnMainThread(new m1(this));
        this.m_MainThread.d();
    }

    @Override // com.unity3d.player.UnityPlayer
    public boolean runningOnMainThread() {
        return Thread.currentThread() == this.m_MainThread;
    }

    void sendSurfaceChangedEvent() {
        if (isNativeInitialized()) {
            this.m_MainThread.c(new RunnableC0031j1(this));
        }
    }

    protected void setCharacterLimit(int i) {
        runOnUiThread(new X0(this, i));
    }

    protected void setHideInputField(boolean z) {
        runOnUiThread(new Y0(this, z));
    }

    @Override // com.unity3d.player.UnityPlayer
    public void setMainSurfaceViewAspectRatio(float f) {
        runOnUiThread(new o1(this, f));
    }

    public void setOnHandleFocusListener(D d) {
        this.mOnHandleFocusListener = d;
    }

    protected void setSelection(int i, int i2) {
        runOnUiThread(new Z0(this, i, i2));
    }

    protected void setSoftInputStr(String str) {
        runOnUiThread(new W0(this, str));
    }

    protected void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i2, boolean z5, boolean z6) {
        Semaphore semaphore = new Semaphore(0);
        postOnUiThread(new q1(this, this, str, i, z, z2, z3, z4, str2, i2, z5, z6, semaphore));
        try {
            if (semaphore.tryAcquire(getSoftInputTimeout(), TimeUnit.MILLISECONDS)) {
                return;
            }
            AbstractC0060y.Log(6, "Timeout (" + getSoftInputTimeout() + " ms) while waiting softinput showing operation.");
        } catch (InterruptedException unused) {
            AbstractC0060y.Log(6, "UI thread got interrupted while waiting softinput showing operation.");
        }
    }

    @Override // com.unity3d.player.UnityPlayer
    void shutdown() {
        this.mProcessKillRequested = nativeDone();
        super.shutdown();
    }

    void updateGLDisplay(int i, Surface surface) {
        if (this.mMainDisplayOverride) {
            return;
        }
        updateDisplayInternal(i, surface);
    }
}
