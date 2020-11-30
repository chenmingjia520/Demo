package com.lanyoumobility.mobility_webview.utils;


import android.content.Context;
import android.os.Environment;

public class Config {

    /******** 路径/目录, 内置SD卡的根目录: /storage/sdcard0/zyd ************/
    public static final String PATHS_ROOT = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/zyd";

    /******** 路径/目录, 临时目录(打印文件等): /zyd/temp ************/
    public static final String PATHS_IMG = PATHS_ROOT + "/img";


    /******** 路径/目录, 临时目录(打印文件等): /zyd/temp ************/
    public static final String PATHS_ERROR = PATHS_ROOT + "/error";

    /******** 路径/目录, 临时目录(打印文件等): /zyd/temp ************/
    public static final String PATHS_EXCEL = PATHS_ROOT + "/excel";




    /******** 路径/目录, 临时目录(打印文件等): /zyd/temp ************/
    public static final String PATHS_DATA= PATHS_ROOT + "/data";


    /******** 路径/目录, 临时目录(打印文件等): /zyd/temp ************/
    public static final String PATHS_PRINTING = PATHS_ROOT + "/printing";



    /******** 路径/目录, 临时目录(打印文件等): /zyd/temp ************/
    public static final String PATHS_DATA_FILE = PATHS_DATA + "/imageinfos.txt";



    /************* 微信支付成功之后回调 ******************/
    public static final String SP_ROOR_URL = "SP_ROOR_URL";

    public static void saveRootUrl(Context context,String rootUrl){
        SharedPreferencesUtils.saveString(context,SP_ROOR_URL,rootUrl);
    }

//    private static final String RootUrl = "https://lonely-travel.cn/drawingdesignsystem";
    private static final String RootUrl = "http://192.168.0.107:8080/drawingdesignsystem";


    public static String getRoorUrl(Context context){
        return SharedPreferencesUtils.getString(context,SP_ROOR_URL,RootUrl);
    }


    /************* 微信支付成功之后回调 ******************/
    public static final String SP_IMAGE_INFO = "SP_IMAGE_INFO";

    public static void saveImageInfos(Context context,String imageInfo){
        SharedPreferencesUtils.saveString(context,SP_IMAGE_INFO,imageInfo);
    }



    public static String getImageInfos(Context context){
        return SharedPreferencesUtils.getString(context,SP_IMAGE_INFO,"");
    }


}
