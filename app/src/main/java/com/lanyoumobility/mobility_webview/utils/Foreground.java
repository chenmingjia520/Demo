package com.lanyoumobility.mobility_webview.utils;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;

import java.lang.ref.WeakReference;


public class Foreground implements Application.ActivityLifecycleCallbacks {

    //单例
    private static Foreground instance = new Foreground();

    private static String TAG = Foreground.class.getSimpleName();
    private final int CHECK_DELAY = 500;

    //用于判断是否程序在前台
    private boolean foreground = false, paused = true;
    //handler用于处理切换activity时的短暂时期可能出现的判断错误
    private Handler handler = new Handler();
    private Runnable check;

    public static void init(Application app) {
        app.registerActivityLifecycleCallbacks(instance);
    }

    public static Foreground get() {
        return instance;
    }

    private Foreground() {
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityController.addActivity(activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
//        if(ActivityController.getSize() == 1){
//            activity.stopService(new Intent(activity, GDLocationService.class));
//        }
        ActivityController.removeActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        paused = true;
        if (check != null)
            handler.removeCallbacks(check);
            handler.postDelayed(check = new Runnable() {
                @Override
                public void run() {
                    if (foreground && paused) {
                        foreground = false;
                    } else {
                    }
                }
            }, CHECK_DELAY);

       // PgyFeedbackShakeManager.unregister();
    }

    @Override
    public void onActivityResumed(Activity activity) {
        paused = false;
        foreground = true;
        if (check != null)
            handler.removeCallbacks(check);

        ActivityController.setCurrActivity(new WeakReference<Activity>(activity));

     /*   // 自定义摇一摇的灵敏度，默认为950，数值越小灵敏度越高。
        PgyFeedbackShakeManager.setShakingThreshold(800);
        // 以对话框的形式弹出
        PgyFeedbackShakeManager.register(activity);*/

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    public boolean isForeground() {
        return foreground;
    }

}
