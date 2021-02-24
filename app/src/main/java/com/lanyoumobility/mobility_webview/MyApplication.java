package com.lanyoumobility.mobility_webview;

import android.app.Application;

import com.lanyoumobility.mobility_webview.mode.entity.UserInfoBean;
import com.lanyoumobility.mobility_webview.utils.CrashHandler;
import com.lanyoumobility.mobility_webview.utils.DirUtils;
import com.lanyoumobility.mobility_webview.utils.Foreground;
import com.lanyoumobility.mobility_webview.utils.L;
import com.lanyoumobility.mobility_webview.utils.SharedPreferencesUtils;

import org.litepal.LitePal;

public class MyApplication extends Application {

    private static MyApplication INSTANCE = null;

    public static MyApplication getInstance() {
        return INSTANCE;
    }


    private final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        LitePal.initialize(this);
        //注册自己的Activity的生命周期回调接口
        Foreground.init(this);

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

    private static final String SP_USER_INFO = "SP_USER_INFO";
    private static UserInfoBean.ResultBean mUser_Inf;
    /**
     * 保存保存用户信息
     * @param userInfo
     */
    public static void setUserInfo(UserInfoBean.ResultBean userInfo) {
        if (userInfo == null) {
            return;
        }
        mUser_Inf = userInfo;
        SharedPreferencesUtils.putBean(INSTANCE, SP_USER_INFO, userInfo);
    }

    /**
     * 获取用户信息
     * @return
     */
    public static UserInfoBean.ResultBean getUserInfo() {
        if (mUser_Inf == null) {
            mUser_Inf = (UserInfoBean.ResultBean) SharedPreferencesUtils.getBean(INSTANCE, SP_USER_INFO);
        }
        return mUser_Inf;
    }
}
