package com.lanyoumobility.mobility_webview.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.lanyoumobility.mobility_webview.utils.Config;
import com.lanyoumobility.mobility_webview.utils.L;

import java.io.File;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class MyWebChromeClient extends android.webkit.WebChromeClient {

    private final String TAG = "MyWebChromeClient";

    private ProgressBar mProgressBar;
    private ValueCallback<Uri> mUploadMsg;// 表单的数据信息
    private ValueCallback<Uri[]> mValueCallback;
    private Activity activity;
    public MyWebChromeClient(Activity activity ) {
        this.activity = activity;
    }


    public void setProgressBar(ProgressBar mProgressBar){
        this.mProgressBar = mProgressBar;
    }



    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (newProgress == 100){
            if(mProgressBar==null){
                return;
            }
            mProgressBar.setVisibility(View.GONE);
        } else {
            if(mProgressBar==null){
                return;
            }
            if (mProgressBar.getVisibility() == View.GONE){
                mProgressBar.setVisibility(View.VISIBLE);
            }
            mProgressBar.setProgress(newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }



    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        //  return super.onJsAlert(view, url, message, result);
        return true;
    }


    // For Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        openFileChooseProcess(uploadMsg);
    }

    // For Android < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsgs) {
        openFileChooseProcess(uploadMsgs);
    }

    // For Android  > 4.1.1
//    @Override
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooseProcess(uploadMsg);
    }

    // For Android  >= 5.0
    @Override
    public boolean onShowFileChooser(WebView webView,
                                     ValueCallback<Uri[]> filePathCallback,
                                     WebChromeClient.FileChooserParams fileChooserParams) {
        openFileChooseProcess5(filePathCallback,fileChooserParams);
        return true;
    }


    /**
     * android 5.0(含) 系统自带的图片选择
     *
     */
    private void openFileChooseProcess5(ValueCallback<Uri[]> filePathCallback,WebChromeClient.FileChooserParams fileChooserParams) {
        mValueCallback = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        if (fileChooserParams != null && fileChooserParams.getMode() == WebChromeClient.FileChooserParams.MODE_OPEN_MULTIPLE) {
            //关键在这
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }
        activity.startActivityForResult(intent, RESULT_CANCELED);
    }
    /**
     * 5.0以下
     */
    private void openFileChooseProcess(ValueCallback<Uri> uploadMsg) {
        mUploadMsg = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(i, "Image Chooser"), RESULT_CANCELED);
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode  == RESULT_CANCELED) {
            if (mUploadMsg != null) {
                mUploadMsg.onReceiveValue(null);
                mUploadMsg = null;
            }
            if (mValueCallback != null) {
                mValueCallback.onReceiveValue(null);
                mValueCallback = null;
            }
        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (null == mUploadMsg && null == mValueCallback) {
                        return;
                    }
                    Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
                    if (mValueCallback != null) {
                        //处理相关数据
                        onActivityResultAboveL(data);
                    } else if (mUploadMsg != null) {
                        mUploadMsg.onReceiveValue(result);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    //选中图片并传给js
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(Intent intent) {
        Uri[] results = null;
        if (intent != null) {
            String dataString = intent.getDataString();
            ClipData clipData = intent.getClipData();
            if (clipData != null) {
                results = new Uri[clipData.getItemCount()];
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    results[i] = item.getUri();
                }
            }
            if (dataString != null) {
                results = new Uri[]{Uri.parse(dataString)};
            }
        }
        mValueCallback.onReceiveValue(results);
        mValueCallback = null;
    }
}