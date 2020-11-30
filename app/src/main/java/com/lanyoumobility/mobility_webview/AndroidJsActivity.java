package com.lanyoumobility.mobility_webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;


public class AndroidJsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_js_jiaohu);

        initView();
        initWebView();
    }

    WebView webView;

    private void initWebView() {
        webView =  findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavascript(), "justTest");
//        webView.loadUrl("file:///android_asset/javascript_test.html");
        webView.loadUrl("file:///android_asset/javascript_test.html");
    }

    private void initView() {
        Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testJS();
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void testJS() {
        webView.loadUrl("javascript:test()");
    }


    /*
     * @Author hxye
     * @Date 2020/4/10
     * @Des 原生与js交互
     */
    public class MyJavascript extends Object {
        @JavascriptInterface
        public void hello(String msg) {
            Toast.makeText(AndroidJsActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

}
