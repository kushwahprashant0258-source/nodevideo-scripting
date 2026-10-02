package com.shallwaystudio.nodevideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Adds the Node Video Script Editor entry point without changing Unity's native lifecycle.
 * The editor operates on exported/importable .nv project JSON.
 */
public class NodeVideoScriptActivity extends UnityPlayerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout root = new FrameLayout(this);
        root.setClickable(false);
        Button script = new Button(this);
        script.setText("Script");
        script.setAllCaps(false);
        script.setOnClickListener(v -> {
            Intent i = new Intent(this, NodeVideoScriptEditorActivity.class);
            startActivity(i);
        });

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.TOP | Gravity.END);
        lp.setMargins(0, 32, 24, 0);
        root.addView(script, lp);
        addContentView(root, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
    }
}
