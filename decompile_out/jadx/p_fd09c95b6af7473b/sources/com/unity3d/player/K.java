package com.unity3d.player;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
final class K implements InvocationHandler {
    private Runnable a;
    private UnityPlayer b;
    private long c;
    final /* synthetic */ long d;

    K(UnityPlayer unityPlayer, long j) {
        this.d = j;
        long j2 = ReflectionHelper.b;
        this.a = new N(j2, j);
        this.b = unityPlayer;
        this.c = j2;
    }

    private static Object a(Object obj, Method method, Object[] objArr, M m) {
        if (objArr == null) {
            try {
                try {
                    objArr = new Object[0];
                } catch (NoClassDefFoundError unused) {
                    AbstractC0060y.Log(6, String.format("Java interface default methods are only supported since Android Oreo", new Object[0]));
                    ReflectionHelper.nativeProxyLogJNIInvokeException(m.a);
                    m.a = 0L;
                    return null;
                }
            } catch (Throwable th) {
                long j = m.a;
                if (j != 0) {
                    ReflectionHelper.nativeProxyJNIFreeGCHandle(j);
                }
                throw th;
            }
        }
        Class<?> declaringClass = method.getDeclaringClass();
        Constructor declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
        declaredConstructor.setAccessible(true);
        Object objInvokeWithArguments = ((MethodHandles.Lookup) declaredConstructor.newInstance(declaringClass, 2)).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
        long j2 = m.a;
        if (j2 != 0) {
            ReflectionHelper.nativeProxyJNIFreeGCHandle(j2);
        }
        return objInvokeWithArguments;
    }

    protected void finalize() throws Throwable {
        this.b.invokeOnMainThread(this.a);
        super.finalize();
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        if (!ReflectionHelper.beginProxyCall(this.c)) {
            AbstractC0060y.Log(6, "Scripting proxy object was destroyed, because Unity player was unloaded.");
            return null;
        }
        try {
            Object objNativeProxyInvoke = ReflectionHelper.nativeProxyInvoke(this.d, method.getName(), objArr);
            if (!(objNativeProxyInvoke instanceof M)) {
                return objNativeProxyInvoke;
            }
            M m = (M) objNativeProxyInvoke;
            if (m.b && (method.getModifiers() & 1024) == 0) {
                return a(obj, method, objArr, m);
            }
            ReflectionHelper.nativeProxyLogJNIInvokeException(m.a);
            return null;
        } finally {
            ReflectionHelper.endProxyCall();
        }
    }
}
