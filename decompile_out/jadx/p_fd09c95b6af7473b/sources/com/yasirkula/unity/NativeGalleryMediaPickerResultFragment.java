package com.yasirkula.unity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
public class NativeGalleryMediaPickerResultFragment extends DialogFragment {
    public static String progressBarLabel = "Please wait...";
    public static int uiUpdateInterval = 100;
    private ProgressBar progressBar;
    private final Runnable progressBarUpdateTask;
    private final NativeGalleryMediaPickerResultOperation resultOperation;
    private final Handler uiUpdateHandler;

    public NativeGalleryMediaPickerResultFragment() {
        this.uiUpdateHandler = new Handler(Looper.getMainLooper());
        this.progressBarUpdateTask = new Runnable() { // from class: com.yasirkula.unity.NativeGalleryMediaPickerResultFragment.1
            @Override // java.lang.Runnable
            public void run() {
                if (NativeGalleryMediaPickerResultFragment.this.resultOperation.finished) {
                    NativeGalleryMediaPickerResultFragment.this.resultOperation.sendResultToUnity();
                    NativeGalleryMediaPickerResultFragment.this.dismissAllowingStateLoss();
                    return;
                }
                try {
                    if (NativeGalleryMediaPickerResultFragment.this.progressBar != null) {
                        if (NativeGalleryMediaPickerResultFragment.this.resultOperation.progress >= 0) {
                            if (NativeGalleryMediaPickerResultFragment.this.progressBar.isIndeterminate()) {
                                NativeGalleryMediaPickerResultFragment.this.progressBar.setIndeterminate(false);
                            }
                            NativeGalleryMediaPickerResultFragment.this.progressBar.setProgress(NativeGalleryMediaPickerResultFragment.this.resultOperation.progress);
                        } else if (!NativeGalleryMediaPickerResultFragment.this.progressBar.isIndeterminate()) {
                            NativeGalleryMediaPickerResultFragment.this.progressBar.setIndeterminate(true);
                        }
                    }
                } finally {
                    NativeGalleryMediaPickerResultFragment.this.uiUpdateHandler.postDelayed(NativeGalleryMediaPickerResultFragment.this.progressBarUpdateTask, NativeGalleryMediaPickerResultFragment.uiUpdateInterval);
                }
            }
        };
        this.resultOperation = null;
    }

    public NativeGalleryMediaPickerResultFragment(final NativeGalleryMediaPickerResultOperation resultOperation) {
        this.uiUpdateHandler = new Handler(Looper.getMainLooper());
        this.progressBarUpdateTask = new Runnable() { // from class: com.yasirkula.unity.NativeGalleryMediaPickerResultFragment.1
            @Override // java.lang.Runnable
            public void run() {
                if (NativeGalleryMediaPickerResultFragment.this.resultOperation.finished) {
                    NativeGalleryMediaPickerResultFragment.this.resultOperation.sendResultToUnity();
                    NativeGalleryMediaPickerResultFragment.this.dismissAllowingStateLoss();
                    return;
                }
                try {
                    if (NativeGalleryMediaPickerResultFragment.this.progressBar != null) {
                        if (NativeGalleryMediaPickerResultFragment.this.resultOperation.progress >= 0) {
                            if (NativeGalleryMediaPickerResultFragment.this.progressBar.isIndeterminate()) {
                                NativeGalleryMediaPickerResultFragment.this.progressBar.setIndeterminate(false);
                            }
                            NativeGalleryMediaPickerResultFragment.this.progressBar.setProgress(NativeGalleryMediaPickerResultFragment.this.resultOperation.progress);
                        } else if (!NativeGalleryMediaPickerResultFragment.this.progressBar.isIndeterminate()) {
                            NativeGalleryMediaPickerResultFragment.this.progressBar.setIndeterminate(true);
                        }
                    }
                } finally {
                    NativeGalleryMediaPickerResultFragment.this.uiUpdateHandler.postDelayed(NativeGalleryMediaPickerResultFragment.this.progressBarUpdateTask, NativeGalleryMediaPickerResultFragment.uiUpdateInterval);
                }
            }
        };
        this.resultOperation = resultOperation;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new Thread(new Runnable() { // from class: com.yasirkula.unity.NativeGalleryMediaPickerResultFragment.2
            @Override // java.lang.Runnable
            public void run() {
                NativeGalleryMediaPickerResultFragment.this.resultOperation.execute();
            }
        }).start();
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(1);
        linearLayout.setPadding(30, 30, 30, 30);
        linearLayout.setGravity(17);
        linearLayout.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        layoutParams2.width = (int) (getActivity().getResources().getDisplayMetrics().density * 175.0f);
        ProgressBar progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleHorizontal);
        this.progressBar = progressBar;
        progressBar.setIndeterminate(true);
        this.progressBar.setPadding(0, 30, 0, 0);
        this.progressBar.setLayoutParams(layoutParams2);
        String str = progressBarLabel;
        if (str != null && str.length() > 0) {
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams3.gravity = 17;
            TextView textView = new TextView(getActivity());
            textView.setText(progressBarLabel);
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            textView.setTextSize(20.0f);
            textView.setLayoutParams(layoutParams3);
            linearLayout.addView(textView);
        }
        linearLayout.addView(this.progressBar);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(getActivity()).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.yasirkula.unity.NativeGalleryMediaPickerResultFragment.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                NativeGalleryMediaPickerResultFragment.this.resultOperation.cancel();
                NativeGalleryMediaPickerResultFragment.this.resultOperation.sendResultToUnity();
                NativeGalleryMediaPickerResultFragment.this.dismissAllowingStateLoss();
            }
        }).setCancelable(false).setView(linearLayout).create();
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.setCanceledOnTouchOutside(false);
        return alertDialogCreate;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.progressBarUpdateTask.run();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDetach() {
        this.progressBar = null;
        this.uiUpdateHandler.removeCallbacks(this.progressBarUpdateTask);
        super.onDetach();
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        this.resultOperation.sendResultToUnity();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.resultOperation.sendResultToUnity();
    }
}
