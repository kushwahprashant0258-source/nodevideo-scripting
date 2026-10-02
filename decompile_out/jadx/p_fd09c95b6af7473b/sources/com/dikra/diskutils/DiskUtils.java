package com.dikra.diskutils;

import android.os.Environment;
import android.os.StatFs;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class DiskUtils {
    private static final long MEGA_BYTE = 1048576;

    public static int totalSpace(boolean z) {
        StatFs stats = getStats(z);
        return BigInteger.valueOf(stats.getBlockCountLong()).multiply(BigInteger.valueOf(stats.getBlockSizeLong())).divide(BigInteger.valueOf(MEGA_BYTE)).intValue();
    }

    public static int availableSpace(boolean z) {
        StatFs stats = getStats(z);
        return BigInteger.valueOf(stats.getAvailableBlocksLong()).multiply(BigInteger.valueOf(stats.getBlockSizeLong())).divide(BigInteger.valueOf(MEGA_BYTE)).intValue();
    }

    public static int busySpace(boolean z) {
        StatFs stats = getStats(z);
        return BigInteger.valueOf(stats.getBlockCountLong()).multiply(BigInteger.valueOf(stats.getBlockSizeLong())).subtract(BigInteger.valueOf(stats.getFreeBlocksLong()).multiply(BigInteger.valueOf(stats.getBlockSizeLong()))).divide(BigInteger.valueOf(MEGA_BYTE)).intValue();
    }

    private static StatFs getStats(boolean z) {
        String absolutePath;
        if (z) {
            absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            absolutePath = Environment.getRootDirectory().getAbsolutePath();
        }
        return new StatFs(absolutePath);
    }
}
