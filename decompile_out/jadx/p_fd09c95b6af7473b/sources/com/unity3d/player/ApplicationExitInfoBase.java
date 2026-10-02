package com.unity3d.player;

import java.text.SimpleDateFormat;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
abstract class ApplicationExitInfoBase {
    ApplicationExitInfoBase() {
    }

    public abstract int describeContents();

    public abstract int getDefiningUid();

    public abstract String getDescription();

    public abstract int getImportance();

    public abstract int getPackageUid();

    public abstract int getPid();

    public abstract String getProcessName();

    public abstract byte[] getProcessStateSummary();

    public abstract long getPss();

    public abstract int getRealUid();

    public abstract int getReason();

    public abstract long getRss();

    public abstract int getStatus();

    public abstract long getTimestamp();

    public abstract byte[] getTrace();

    public int hashCode() {
        return Objects.hashCode(getDescription()) + ((Objects.hashCode(getProcessName()) + ((Long.hashCode(getTimestamp()) + (((((((((((((((((getPid() * 31) + getRealUid()) * 31) + getPackageUid()) * 31) + getDefiningUid()) * 31) + getReason()) * 31) + getImportance()) * 31) + getStatus()) * 31) + ((int) getPss())) * 31) + ((int) getRss())) * 31)) * 31)) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ApplicationExitInfo [");
        sb.append(hashCode()).append("]");
        sb.append(System.lineSeparator()).append("{");
        long timestamp = getTimestamp();
        sb.append(System.lineSeparator()).append("timestamp : ").append(timestamp);
        sb.append(System.lineSeparator()).append("date : ").append(new SimpleDateFormat("yyyy MM dd HH:mm:ss").format(Long.valueOf(timestamp)));
        sb.append(System.lineSeparator()).append("pid : ").append(getPid());
        sb.append(System.lineSeparator()).append("realUid : ").append(getRealUid());
        sb.append(System.lineSeparator()).append("packageUid : ").append(getPackageUid());
        sb.append(System.lineSeparator()).append("definingUid : ").append(getDefiningUid());
        sb.append(System.lineSeparator()).append("process : ").append(getProcessName());
        sb.append(System.lineSeparator()).append("reason : ").append(getReason());
        sb.append(System.lineSeparator()).append("status : ").append(getStatus());
        sb.append(System.lineSeparator()).append("importance : ").append(getImportance());
        sb.append(System.lineSeparator()).append("pss : ").append(getPss());
        sb.append(System.lineSeparator()).append("rss : ").append(getRss());
        sb.append(System.lineSeparator()).append("description : ").append(getDescription());
        byte[] processStateSummary = getProcessStateSummary();
        sb.append(System.lineSeparator()).append("state : ").append((processStateSummary == null || processStateSummary.length == 0) ? "empty" : Integer.toString(processStateSummary.length) + " bytes");
        byte[] trace = getTrace();
        sb.append(System.lineSeparator()).append("trace : ").append(trace != null ? Integer.toString(trace.length) + " bytes" : "empty");
        sb.append(System.lineSeparator()).append("}");
        return sb.toString();
    }
}
