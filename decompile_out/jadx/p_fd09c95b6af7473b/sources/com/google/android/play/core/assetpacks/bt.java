package com.google.android.play.core.assetpacks;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.zip.ZipException;

/* JADX INFO: compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class bt {
    static AssetLocation a(String str, String str2) throws IOException {
        Long lValueOf;
        int i;
        com.google.android.play.core.assetpacks.internal.aj.b(str != null, "Attempted to get file location from a null apk path.");
        com.google.android.play.core.assetpacks.internal.aj.b(str2 != null, String.format("Attempted to get file location in apk %s with a null file path.", str));
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
        byte[] bArr = new byte[22];
        randomAccessFile.seek(randomAccessFile.length() - 22);
        randomAccessFile.readFully(bArr);
        bs bsVarB = br.b(bArr, 0) == 1347093766 ? b(bArr) : null;
        if (bsVarB == null) {
            long length = randomAccessFile.length();
            long jMax = (-22) + length;
            int iMin = (int) Math.min(1024L, randomAccessFile.length());
            byte[] bArr2 = new byte[iMin];
            byte[] bArr3 = new byte[22];
            loop0: while (true) {
                long j = (-65558) + length;
                if (j < 0) {
                    j = 0;
                }
                long j2 = length;
                jMax = Math.max((jMax - ((long) iMin)) + 3, j);
                randomAccessFile.seek(jMax);
                randomAccessFile.readFully(bArr2);
                for (int i2 = iMin - 4; i2 >= 0; i2 -= 4) {
                    byte b = bArr2[i2];
                    if (b == 5) {
                        i = 2;
                    } else if (b == 6) {
                        i = 3;
                    } else if (b != 75) {
                        i = b != 80 ? -1 : 0;
                    } else {
                        i = 1;
                    }
                    if (i >= 0 && i2 >= i && br.b(bArr2, i2 - i) == 1347093766) {
                        randomAccessFile.seek((jMax + ((long) i2)) - ((long) i));
                        randomAccessFile.readFully(bArr3);
                        bsVarB = b(bArr3);
                        break loop0;
                    }
                }
                if (jMax == j) {
                    throw new ZipException(String.format("End Of Central Directory signature not found in APK %s", str));
                }
                length = j2;
            }
        }
        long jA = bsVarB.a;
        byte[] bytes = str2.getBytes("UTF-8");
        byte[] bArr4 = new byte[46];
        byte[] bArr5 = new byte[str2.length()];
        int i3 = 0;
        while (true) {
            if (i3 >= bsVarB.b) {
                lValueOf = null;
                break;
            }
            randomAccessFile.seek(jA);
            randomAccessFile.readFully(bArr4);
            int iB = br.b(bArr4, 0);
            if (iB != 1347092738) {
                throw new ZipException(String.format("Missing central directory file header signature when looking for file %s in APK %s. Read %d entries out of %d. Found %d instead of the header signature %d.", str2, str, Integer.valueOf(i3), Integer.valueOf(bsVarB.b), Integer.valueOf(iB), 1347092738));
            }
            randomAccessFile.seek(28 + jA);
            int iA = br.a(bArr4, 28);
            if (iA == str2.length()) {
                randomAccessFile.seek(46 + jA);
                randomAccessFile.read(bArr5);
                if (Arrays.equals(bArr5, bytes)) {
                    lValueOf = Long.valueOf(br.c(bArr4, 42));
                    break;
                }
            }
            jA += (long) (iA + 46 + br.a(bArr4, 30) + br.a(bArr4, 32));
            i3++;
        }
        if (lValueOf == null) {
            return null;
        }
        long jLongValue = lValueOf.longValue();
        byte[] bArr6 = new byte[8];
        randomAccessFile.seek(22 + jLongValue);
        randomAccessFile.readFully(bArr6);
        return new bl(str, jLongValue + 30 + ((long) br.a(bArr6, 4)) + ((long) br.a(bArr6, 6)), br.c(bArr6, 0));
    }

    private static bs b(byte[] bArr) {
        int iA = br.a(bArr, 10);
        return new bs(br.c(bArr, 16), br.c(bArr, 12), iA);
    }
}
