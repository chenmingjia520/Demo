package com.lanyoumobility.mobility_webview;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lanyoumobility.mobility_webview.utils.Config;
import com.lanyoumobility.mobility_webview.utils.DirUtils;
import com.lanyoumobility.mobility_webview.utils.L;
import com.lanyoumobility.mobility_webview.utils.ShareUtils;
import com.lanyoumobility.mobility_webview.utils.ToastUtil;
import com.lanyoumobility.mobility_webview.webview.MyJavascript;
import com.lanyoumobility.mobility_webview.webview.MyWebChromeClient;
import com.lanyoumobility.mobility_webview.webview.MyWebViewClient;
import com.lanyoumobility.mobility_webview.webview.listener.MyWebViewDownLoadListener;

import org.json.JSONObject;

import java.io.File;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    public static boolean isLoding = true;
    private final String TAG = "MainActivity";
    @BindView(R.id.mWebView)
    WebView mWebView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.ll_main)
    LinearLayout ll_main;

    @BindView(R.id.tv_title_error)
    TextView tv_title_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        L.i(TAG,"onCreate..................................");
        //获取权限后，执行闪屏页正常启动的流程
        checkPermission(this::normalStartSplash, R.string.permission_storage,
//                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);//Manifest.permission.CALL_PHONE
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.i(TAG,"onStart..................................");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        L.i(TAG,"onSaveInstanceState..................................");
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            L.i(TAG,"onSaveInstanceState..............12121122....................");
            super.onSaveInstanceState(outState);
        }
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        L.i(TAG,"onConfigurationChanged..................................");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.i(TAG,"onStop..................................");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.i(TAG,"onRestart..................................");
    }

    @Override
    protected void onPause() {
        super.onPause();

        L.i(TAG,"onPause..................................");
        saveImageInfo();
    }

    public void normalStartSplash() {
        DirUtils.initDir();
        initData();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.ll_main, R.id.tv_title_error, R.id.rl_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main:
                break;
            case R.id.tv_title_error:
                toTitleError();
                break;
            case R.id.rl_setting:
                showCustomizeDialog();
                break;
        }
    }


    private void initData() {
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setAllowUniversalAccessFromFileURLs(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
//        //设置缓存
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //允许调用js
        webSettings.setJavaScriptEnabled(true);

        webSettings.setUseWideViewPort(true);
        webSettings.setAllowContentAccess(true);
//        webSettings.setBlockNetworkImage(true);
        //开启s数据缓存
        webSettings.setAppCacheEnabled(false);
        //启动数据库
        webSettings.setDatabaseEnabled(false);
//        String dir = getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
        //设置定位的数据库路径
//        webSettings.setGeolocationDatabasePath(dir);
//        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);//设定支持缩放

        mWebView.addJavascriptInterface(new MyJavascript(this, mWebView), "LymWebAction");//AndroidtoJS类对象映射到js的test对象
        myWebChromeClient = new MyWebChromeClient(this);
        myWebChromeClient.setProgressBar(mProgressBar);
        mWebView.setWebViewClient(new MyWebViewClient(tv_title_error, mWebView));
        mWebView.setWebChromeClient(myWebChromeClient);
        mWebView.setDownloadListener(new MyWebViewDownLoadListener(this));
        mWebView.loadUrl("file:///android_asset/index.html");
    }

    private MyWebChromeClient myWebChromeClient;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CANCELED) {
            myWebChromeClient.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showCustomizeDialog() {
        /* @setView 装入自定义View ==> R.layout.dialog_customize
         * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
         * dialog_customize.xml可自定义更复杂的View
         */
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(MainActivity.this);
        final View dialogView = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.dialog_customize, null);

        EditText edit_text =
                (EditText) dialogView.findViewById(R.id.edit_text);
        edit_text.setText(Config.getRoorUrl(MainActivity.this));

        customizeDialog.setTitle("设置");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        String edit = edit_text.getText().toString().trim();
                        Config.saveRootUrl(MainActivity.this, edit);

                        mWebView.loadUrl(edit);
                        Toast.makeText(MainActivity.this, edit, Toast.LENGTH_SHORT).show();
                    }
                });
        customizeDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        customizeDialog.show();
    }

    /**
     * 重新加载
     */
    private void toTitleError() {
        if (mWebView != null) {
            tv_title_error.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
            mWebView.loadUrl(Config.getRoorUrl(this));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            saveImageInfo();
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                conLogout();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void conLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirmlogout);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton(R.string.cancel, null).create().show();
    }


    @SuppressLint("SetJavaScriptEnabled")
    public void saveImageInfo() {
        L.log(TAG,"开始保存信息...isLoding."+isLoding);
        if(mWebView!=null&&!isLoding){
            L.log(TAG,"开始保存信息....");
            mWebView.loadUrl("javascript:saveImageInfo()");
        }
    }


    public void startIntentPrint(File file) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                hideLoading();
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setPackage("com.dynamixsoftware.printershare");
//            intent.setPackage("com.zhprin.tthree");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        String authority = BuildConfig.APPLICATION_ID + ".fileprovider";
                        Uri contentUri = FileProvider.getUriForFile(MainActivity.this, authority, file);
                        intent.setDataAndType(contentUri, "application/msword");
                    } else {
                        intent.setDataAndType(Uri.fromFile(file), "application/msword");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast(MainActivity.this, "请确保已安装'printershare'");
                }
            }
        });

    }

    Handler handler = new Handler();
    public void showToast(String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {

                hideLoading();
                ToastUtil.showToast(MainActivity.this,msg);
            }
        });
    }
    public void shareFile(Uri uri) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ShareUtils.shareFile(MainActivity.this,uri);
            }
        });
    }


    public void showLoadings(String msg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
//                showLoading(msg);
            }
        });
    }

    public void hideLoadings() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                hideLoading();
            }
        });
    }
}
