package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class ViewModelProvider {
    private static final String DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey";
    private final Factory mFactory;
    private final ViewModelStore mViewModelStore;

    public interface Factory {
        <T extends ViewModel> T create(Class<T> cls);
    }

    static class OnRequeryFactory {
        void onRequery(ViewModel viewModel) {
        }

        OnRequeryFactory() {
        }
    }

    static abstract class KeyedFactory extends OnRequeryFactory implements Factory {
        public abstract <T extends ViewModel> T create(String str, Class<T> cls);

        KeyedFactory() {
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ViewModelProvider(ViewModelStoreOwner viewModelStoreOwner) {
        Factory newInstanceFactory;
        ViewModelStore viewModelStore = viewModelStoreOwner.getViewModelStore();
        if (viewModelStoreOwner instanceof HasDefaultViewModelProviderFactory) {
            newInstanceFactory = ((HasDefaultViewModelProviderFactory) viewModelStoreOwner).getDefaultViewModelProviderFactory();
        } else {
            newInstanceFactory = NewInstanceFactory.getInstance();
        }
        this(viewModelStore, newInstanceFactory);
    }

    public ViewModelProvider(ViewModelStoreOwner viewModelStoreOwner, Factory factory) {
        this(viewModelStoreOwner.getViewModelStore(), factory);
    }

    public ViewModelProvider(ViewModelStore viewModelStore, Factory factory) {
        this.mFactory = factory;
        this.mViewModelStore = viewModelStore;
    }

    public <T extends ViewModel> T get(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return (T) get("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class o0oO0o0OO0o0.o0o0oOO0O00O00o$OOoooOoOo0OO
    	at o0oO0o0OO0o0.o0o0oOO0O00O00o.OOO0OooOo0O000(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:17)
    	at OO0OooO00o0Oo0.O0OO00O0oooo0o.o00O0Oo0o0O(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:3)
    	at oO0000OOoo0oO.o00O0Oo0o0O.o00oo0o0o000oOo(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:14)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.OOOO00oOO0O0o(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:102)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.ooO0Oo0O0OOo0(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:74)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.OOoo0o0ooo0O(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:21)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.OOoooOoOo0OO(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:1)
    	at o0oOOooOO000O0.O0ooOOOO00OOOOO.apply(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:44)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.O0ooOOOO00OOOOO(r8-map-id-d77ce248dbc336ce38db6c099dfe89a26d430195bd0e5b4753d42fef5efe453f:32)
     */
    public <T extends ViewModel> T get(String str, Class<T> cls) {
        T t;
        T t2 = (T) this.mViewModelStore.get(str);
        if (cls.isInstance(t2)) {
            Object obj = this.mFactory;
            if (obj instanceof OnRequeryFactory) {
                ((OnRequeryFactory) obj).onRequery(t2);
            }
            return t2;
        }
        Factory factory = this.mFactory;
        if (factory instanceof KeyedFactory) {
            t = (T) ((KeyedFactory) factory).create(str, cls);
        } else {
            t = (T) factory.create(cls);
        }
        this.mViewModelStore.put(str, t);
        return t;
    }

    public static class NewInstanceFactory implements Factory {
        private static NewInstanceFactory sInstance;

        static NewInstanceFactory getInstance() {
            if (sInstance == null) {
                sInstance = new NewInstanceFactory();
            }
            return sInstance;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> cls) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            }
        }
    }

    public static class AndroidViewModelFactory extends NewInstanceFactory {
        private static AndroidViewModelFactory sInstance;
        private Application mApplication;

        public static AndroidViewModelFactory getInstance(Application application) {
            if (sInstance == null) {
                sInstance = new AndroidViewModelFactory(application);
            }
            return sInstance;
        }

        public AndroidViewModelFactory(Application application) {
            this.mApplication = application;
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> cls) {
            if (AndroidViewModel.class.isAssignableFrom(cls)) {
                try {
                    return cls.getConstructor(Application.class).newInstance(this.mApplication);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e);
                } catch (InstantiationException e2) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e2);
                } catch (NoSuchMethodException e3) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e3);
                } catch (InvocationTargetException e4) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e4);
                }
            }
            return (T) super.create(cls);
        }
    }
}
