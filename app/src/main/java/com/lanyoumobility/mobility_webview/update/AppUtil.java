package com.lanyoumobility.mobility_webview.update;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;


import com.lanyoumobility.mobility_webview.utils.ActivityController;

import java.io.File;


/**
 *
 * @author hy
 * @date 2016/7/29 0029
 */
public class AppUtil {

    private static final int REQUEST_CODE_APP_INSTALL = 111;
    /**请求安装apk权限的请求码*/
    public static final int REQUEST_CODE_INSTALL_APK_PERMISSION = 1001;
    /**
     * 获取系统版本号
     * @param context
     * @return
     */
    public static int getVersionCode(Context context){
        try {
            Context ac = context.getApplicationContext();
            PackageManager pm = ac.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ac.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }


    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机系统版本号
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }


    public static String getVersionName(Context context){
        try {
            Context ac = context.getApplicationContext();
            PackageManager pm = ac.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ac.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 安装APK
     * <p>
     *     适配Android8.0版本，检查权限增加FileProvider
     * </p>
     * */
    public static void installApk(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean haveInstallPermission = context.getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {
                //TODO 待后续完善，这种方式可能存在其他意外情况
                Activity activity = ActivityController.getCurrActivity();

                Uri parse = Uri.parse("package:" + context.getPackageName());
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, parse);
//                if (context instanceof Activity) {
//                    context.startActivity(intent);
//                }
                if (activity != null && !activity.isDestroyed()){
                    ActivityCompat.startActivityForResult(activity,intent,
                            REQUEST_CODE_INSTALL_APK_PERMISSION,null);
                }else {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            } else {
                install(context, file);
            }
        } else {
            install(context, file);
        }
    }
    /**安装APK*/
    private static void install(Context context,File file){
        Intent intent = new Intent(Intent.ACTION_VIEW);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri contentUri = FileProvider.getUriForFile(context,
                    context.getPackageName() + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        }else{
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        intent.addCategory(Intent.CATEGORY_DEFAULT);
        context.startActivity(intent);
    }
}
