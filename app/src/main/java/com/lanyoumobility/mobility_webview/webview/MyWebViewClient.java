package com.lanyoumobility.mobility_webview.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.lanyoumobility.mobility_webview.utils.L;

public class MyWebViewClient extends WebViewClient {

    private final String TAG = "MyWebViewClient";

    private TextView tv_title_error;
    private WebView mWebView;
    public MyWebViewClient(TextView tv_title_error,WebView mWebView){
        this.tv_title_error = tv_title_error;
        this.mWebView = mWebView;
    }


    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        L.i(TAG,"error：：：："+error.toString());
        handler.proceed(); // 直接跳过证书认证
    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(tv_title_error!=null&&mWebView!=null){
            tv_title_error.setVisibility(View.GONE);
            if(mWebView.getVisibility()== View.GONE){
                mWebView.setVisibility(View.VISIBLE);
            }
        }
    }
    @Override
    public void onReceivedError(WebView view, int errorCode,  String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if(tv_title_error!=null&&mWebView!=null){
            tv_title_error.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.GONE);
        }
    }
}
