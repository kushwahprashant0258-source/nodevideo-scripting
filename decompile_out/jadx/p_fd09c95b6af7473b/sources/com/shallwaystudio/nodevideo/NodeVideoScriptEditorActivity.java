package com.shallwaystudio.nodevideo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NodeVideoScriptEditorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView w = new WebView(this);
        WebSettings s = w.getSettings();
        s.setJavaScriptEnabled(true);
        w.addJavascriptInterface(new NodeVideoScriptBridge(), "NodeVideoNative");
        s.setDomStorageEnabled(true);
        w.setWebViewClient(new WebViewClient());
        setTitle("Node Video Script Editor");
        setContentView(w);
        w.loadUrl("file:///android_asset/nvscript/index.html");
    }
}
