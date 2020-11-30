package com.lanyoumobility.mobility_webview.utils;

import android.content.Context;
import android.content.res.Resources;


/**
 * Created by Coco on 2017/1/16.
 */

public class UIUtils {
    public static boolean getPerssion =false;
    private static Context context;
    public static Context getContext(){
        return context;
    }
    public static void setContext(Context context_){
        context =context_;
    }
    public static Resources getResources (){
        return getContext().getResources();
    }
    public static String getString(int resId) {
        return getResources().getString(resId);
    }
    /**
     * 得到String.xml中的字符串数组信息
     */
    public static String[] getStrings(int resId) {
        return getResources().getStringArray(resId);
    }
    /**
     * 得到Color.xml中的颜色信息
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }
    public static String getPackageName() {
        return getContext().getPackageName();
    }
    public static int dip2Px(int dip) {
        /*
        1.  px/(ppi/160) = dp
        2.  px/dp = density
         */
        //取得当前手机px和dp的倍数关系
        float density = getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }

    public static int px2Dip(int px) {
        // 2.  px/dp = density
        //取得当前手机px和dp的倍数关系
        float density = getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + .5f);
        return dip;
    }
}
