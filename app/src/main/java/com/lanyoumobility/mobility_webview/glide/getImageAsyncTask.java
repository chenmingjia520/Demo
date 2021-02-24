package com.lanyoumobility.mobility_webview.glide;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;

 public class getImageAsyncTask extends AsyncTask<String, Void, File> {
     private final String TAG = "getImageAsyncTask";
    private final Context context;

    public getImageAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected File doInBackground(String... params) {
        String imgUrl =  params[0];
        Log.e(TAG, "doInBackground:::::::::::::::::"+imgUrl);
        try {
            return Glide.with(context)
                    .load(imgUrl)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception ex) {
            Log.e(TAG, "Exception:::::::::::::::::"+ex.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(File result) {
        Log.e(TAG, "onPostExecute:::::::::::::::result::"+result);
        if (result == null) {
            return;
        }
        //此path就是对应文件的缓存路径
        String path = result.getPath();
        Log.e(TAG, "onPostExecute::::::::::::::path:::"+path);
    }
}