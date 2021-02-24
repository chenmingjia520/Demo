package com.lanyoumobility.mobility_webview.utils;

import android.app.Activity;
import android.support.annotation.NonNull;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * className: ActivityController
 * description: 活动管理类
 * author: hong
 * datetime: 2016/4/5 0005 上午 8:54
 */
public class ActivityController {

    private static List<Activity> activities = new ArrayList<>();

    private static WeakReference<Activity> currActivity = null;

    /**
     * 添加活动
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 销毁活动
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }


    /**
     * 销毁指定类名其他的activity
     */
    public static void finishOtherActivity(@NonNull Class clazz){
        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if(!activity.getClass().equals(clazz) && !activity.isFinishing()){
                activity.finish();
            }
        }
    }

    /**
     * 销毁活动
     *
     * @param tags
     */
    public static void removeActivity(String... tags) {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                for (int i = 0; i < tags.length; i++) {
                    if (activity.getClass().getName().equals(tags[i])) {
                        activity.finish();
                    }
                }
            }
        }
    }

    /**
     * 销毁指定类名的activity
     */
    public static void finishActivity(@NonNull Class clazz){
        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if(activity.getClass().equals(clazz) && !activity.isFinishing()){
                activity.finish();
                return;
            }
        }
    }


    /**
     * 销毁所有活动
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static int getSize(){
        return activities.size();
    }


    /**
     * 销毁指定活动之外的所有活动
     *
     * @param tags
     */
    public static void finishIgnoreTag(String... tags) {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                boolean flag = true;
                for (int i = 0; i < tags.length; i++) {
                    if (activity.getClass().getName().equals(tags[i])) {
                        flag = false;
                    }
                }
                if (flag) {
                    activity.finish();
                }
            }
        }
    }

    /**
     * 判断活动是否在集合里
     *
     * @param tag
     * @return
     */
    public static boolean hasAdded(String tag) {
        for (Activity activity : activities) {
            if (activity.getClass().getName().equals(tag)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 设置当前Activity
     *
     * @param activity
     */
    public static void setCurrActivity(WeakReference<Activity> activity) {
        currActivity = activity;
    }
  public static List<Activity> getAllRunningActvity(){
        return activities;
  }

    /**
     * 获取当前Activity
     *
     * @return
     */
    public static Activity getCurrActivity() {
        return currActivity.get();
    }


}
