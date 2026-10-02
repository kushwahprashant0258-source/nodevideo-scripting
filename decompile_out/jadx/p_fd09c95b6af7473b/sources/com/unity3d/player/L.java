package com.unity3d.player;

import java.lang.reflect.Member;

/* JADX INFO: loaded from: classes.dex */
final class L {
    private final Class a;
    private final String b;
    private final String c;
    private final int d;
    public volatile Member e;

    L(Class cls, String str, String str2) {
        this.a = cls;
        this.b = str;
        this.c = str2;
        this.d = str2.hashCode() + ((str.hashCode() + ((cls.hashCode() + 527) * 31)) * 31);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof L)) {
            return false;
        }
        L l = (L) obj;
        return this.d == l.d && this.c.equals(l.c) && this.b.equals(l.b) && this.a.equals(l.a);
    }

    public final int hashCode() {
        return this.d;
    }
}
