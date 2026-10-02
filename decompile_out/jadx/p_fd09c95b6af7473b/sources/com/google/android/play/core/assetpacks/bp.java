package com.google.android.play.core.assetpacks;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class bp extends em {
    private final int a;
    private final String b;
    private final long c;
    private final long d;
    private final int e;

    bp(int i, String str, long j, long j2, int i2) {
        this.a = i;
        this.b = str;
        this.c = j;
        this.d = j2;
        this.e = i2;
    }

    @Override // com.google.android.play.core.assetpacks.em
    final int a() {
        return this.a;
    }

    @Override // com.google.android.play.core.assetpacks.em
    final int b() {
        return this.e;
    }

    @Override // com.google.android.play.core.assetpacks.em
    final long c() {
        return this.c;
    }

    @Override // com.google.android.play.core.assetpacks.em
    final long d() {
        return this.d;
    }

    @Override // com.google.android.play.core.assetpacks.em
    final String e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof em) {
            em emVar = (em) obj;
            if (this.a == emVar.a() && ((str = this.b) != null ? str.equals(emVar.e()) : emVar.e() == null) && this.c == emVar.c() && this.d == emVar.d() && this.e == emVar.b()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.a ^ 1000003;
        String str = this.b;
        int iHashCode = ((i * 1000003) ^ (str == null ? 0 : str.hashCode())) * 1000003;
        long j = this.c;
        int i2 = (iHashCode ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.d;
        return ((i2 ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.e;
    }

    public final String toString() {
        return "SliceCheckpoint{fileExtractionStatus=" + this.a + ", filePath=" + this.b + ", fileOffset=" + this.c + ", remainingBytes=" + this.d + ", previousChunk=" + this.e + "}";
    }
}
