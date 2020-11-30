package com.lanyoumobility.mobility_webview;

import android.app.Application;

import com.lanyoumobility.mobility_webview.utils.CrashHandler;
import com.lanyoumobility.mobility_webview.utils.DirUtils;
import com.lanyoumobility.mobility_webview.utils.L;

public class MyApplication extends Application {

    private final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();


        DirUtils.initDir();
        initCrash();
    }

    /**
     * 程序崩溃处理
     */
    private void initCrash() {
        CrashHandler myCrashHandler = CrashHandler.getInstance();
        myCrashHandler.init(getApplicationContext());
        Thread.currentThread().setUncaughtExceptionHandler(myCrashHandler);
    }

}
