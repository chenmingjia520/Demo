package com.lanyoumobility.mobility_webview.glide;

import android.os.AsyncTask;

import com.lanyoumobility.mobility_webview.utils.L;

import java.io.File;

public  class DownloadTask  extends AsyncTask<String, Void, File> {

    private final String TAG = "DownloadTask";
    private ImageCacheManager imageCacheManager;
    public DownloadTask() {
        imageCacheManager = new ImageCacheManager();
    }
    @Override
    protected File doInBackground(String... params) {
        try {
            return imageCacheManager.downlaodImage(params[0]);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    protected  void onPostExecute(File result) {
        L.i(TAG,"onPostExecute::getAbsolutePath::"+result.getAbsolutePath());
        super.onPostExecute(result);
    }
}
