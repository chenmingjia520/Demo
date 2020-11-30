package com.lanyoumobility.mobility_webview.webview.listener;
//内部类

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.webkit.DownloadListener;
import android.widget.Toast;

import com.lanyoumobility.mobility_webview.webview.download.DownloaderTask;

public class MyWebViewDownLoadListener implements DownloadListener {

    private final String TAG = "MyWebViewDownLoadListener";
    private Context context;
    public MyWebViewDownLoadListener(Context context){
        this.context= context;
    }
    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                long contentLength) {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            Toast t = Toast.makeText(context, "需要SD卡。", Toast.LENGTH_LONG);

            t.setGravity(Gravity.CENTER, 0, 0);

            t.show();

            return;
        }

        Log.i("TAG","TAG::::::::::::::开始下载");

        DownloaderTask task = new DownloaderTask(context);

        task.execute(url);
    }
}

