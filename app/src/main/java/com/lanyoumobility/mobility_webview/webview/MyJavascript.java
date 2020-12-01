package com.lanyoumobility.mobility_webview.webview;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.lanyoumobility.mobility_webview.BuildConfig;
import com.lanyoumobility.mobility_webview.MainActivity;
import com.lanyoumobility.mobility_webview.utils.Config;
import com.lanyoumobility.mobility_webview.utils.FilesUtils;
import com.lanyoumobility.mobility_webview.utils.L;
import com.lanyoumobility.mobility_webview.utils.doc.WordUtils;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/*
 * @Author hxye
 * @Date 2020/4/10
 * @Des 原生与js交互
 */
public class MyJavascript extends Object {
    private final String TAG = "MyJavascript";
    private WebView webView;

    private MainActivity activity;

    public MyJavascript(MainActivity activity, WebView webView) {
        this.webView = webView;
        this.activity = activity;
    }

    //根据key获取map里面的value
    private String getParam(String param, Map<String, Object> map) {
        String paramResult = "";
        if (map.get(param) != null && map.get(param).toString().length() > 0) {
            paramResult = map.get(param).toString();
        }
        return paramResult;
    }

    /**
     * 根据方法名利用反射调用不同的方法
     *
     * @param methodName       方法名
     * @param param            参数
     * @param callbackSuccess  成功回调
     * @param callbackFail     失败回调
     * @param callbackComplete 完成回调
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void invokeMethod(String methodName, String param, String callbackSuccess, String callbackFail, String callbackComplete) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        try {
            this.getClass().getMethod(methodName, String.class, String.class, String.class, String.class) //参数类型的类class
                    .invoke(this, param, callbackSuccess, callbackFail, callbackComplete);//对应起来多个参数
        } catch (Exception e) {
        }
    }

    /*
     * js调用native的统一入口
     */
    @JavascriptInterface
    public void postMessage(String functionName, String param, String callbackSuccess, String callbackFail, String callbackComplete) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        invokeMethod(functionName, param, callbackSuccess, callbackFail, callbackComplete);
    }


    /*
     * 获取用户信息
     */
    @JavascriptInterface
    public void getAppUserInfo(String param, String callbackSuccess, String callbackFail, String callbackComplete) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                if (callbackSuccess.length() > 0) {
                    webView.loadUrl("javascript:AppJSBack('" + callbackSuccess + "')");
                    return;
                }
                if (callbackFail.length() > 0) {
                    webView.loadUrl("javascript:AppJSBack('" + callbackFail + "')");
                    return;
                }
                if (callbackComplete.length() > 0) {
                    webView.loadUrl("javascript:AppJSBack('" + callbackComplete + "')");
                    return;
                }
            }
        });
    }

    /*
     * 获取用户信息
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @JavascriptInterface
    public void getDownLoadImg(String param, String callbackSuccess, String callbackFail, String callbackComplete) {
        L.log(TAG, "getDownLoadImg:::::::::::::::::::::param:" + param);
        activity.isLoding = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(param);
                    String base64 = jsonObject.getString("base64");
                    String fileName = jsonObject.getString("fileName");
                    L.log(TAG, "getDownLoadImg:::::::::::::::::::::fileName:" +fileName);
                    String filePath =  FilesUtils.decoderBase64ToFile(base64, Config.PATHS_IMG ,fileName);
                    activity.isLoding = false;
                    if(filePath!=null){
                        Uri uri = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            String authority = BuildConfig.APPLICATION_ID + ".fileprovider";
                            uri = FileProvider.getUriForFile(activity, authority, new File(filePath));
                        } else {
                            uri = Uri.fromFile(new File(filePath));
                        }
                        if(uri!=null){
                            activity.shareFile(uri);
                        }
                        activity.showToast("下载路径为:"+filePath);
                        return;
                    }
                } catch (Exception e) {
                    L.log(TAG, "getDownLoadImg:::::::::::::::::::::Exception:" + e.toString());
                }
                activity.isLoding = false;
                activity.showToast("下载文件失败!");
            }
        }).start();
    }


    /*
     * 获取用户信息
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @JavascriptInterface
    public void showLoading(String param, String callbackSuccess, String callbackFail, String callbackComplete) {
        L.log(TAG, "getDownLoadImg:::::::::::::::::::::param:" + param);
        activity.isLoding = true;
        activity.showLoadings(param);
    }



    /*
     * 获取用户信息
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @JavascriptInterface
    public void getPrintingImg(String param, String callbackSuccess, String callbackFail, String callbackComplete) {
//        activity.showToast("开始打印文件!");
        L.i(TAG,"getPrintingImg::::::::::::::");
        activity.isLoding = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(param);
                    String ProjectBase64 = jsonObject.getString("base64");
                    String fileName = jsonObject.getString("fileName");

                    String  successPath = FilesUtils.decoderBase64ToFile(ProjectBase64, Config.PATHS_PRINTING ,fileName);
                    if (successPath!=null&&successPath.length()>0) {
                        JSONObject projectJSON = jsonObject.getJSONObject("projectInfo");
                        String partyABase64 = projectJSON.getString("partyA");

                        File filePartyA = null;
                        if (partyABase64 != null && !"".equals(partyABase64)) {
                            String filePartyABase64Path =   FilesUtils.decoderBase64ToFile(partyABase64, Config.PATHS_PRINTING ,"autograph_" + fileName);
                            if(filePartyABase64Path!=null&&filePartyABase64Path.length()>0){
                                filePartyA = new File(filePartyABase64Path);
                            }
                        }
//               String imageNumber, String designer, String progress, String date, String manage, String description
                        String projectName = projectJSON.getString("projectName");
                        String imageNumber = projectJSON.getString("imageNumber");
                        String designer = projectJSON.getString("designer");
                        String progress = projectJSON.getString("progress");
                        String date = projectJSON.getString("date");
                        String manage = projectJSON.getString("manage");
                        String description = projectJSON.getString("description");

                        WordUtils wordUtils = new WordUtils();
                        L.log(TAG,"getPrintingImg :::::wordUtils::"+wordUtils);
                        boolean writeToWordSuccess = wordUtils.writeToWord(activity, projectName, imageNumber, designer, progress, date, manage, description, new File(successPath), filePartyA);
                        activity.isLoding = false;
                        if (writeToWordSuccess) {
                            activity.startIntentPrint(new File(Config.PATHS_PRINTING + "/" + projectName + ".doc"));
                            return;
                        }
                        activity.hideLoading();
                        return;
                    }
                } catch (Exception e) {
                    L.log(TAG,"getPrintingImg :::::Exception::"+e.toString());

                }
                activity.isLoding = false;
                activity.showToast("打印文件失败!");
            }
        }).start();
    }




    /*
     * 获取用户信息
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @JavascriptInterface
    public void getDownLoadExcel(String param, String callbackSuccess, String callbackFail, String callbackComplete) {
//        activity.showLoading("下载文件...");
        activity.isLoding = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(param);
                    String base64 = jsonObject.getString("data");
                    String fileName = jsonObject.getString("fileName");
                    String filePath = FilesUtils.decoderBase64ToFile(base64, Config.PATHS_EXCEL ,fileName);
                    if(filePath!=null){
                        activity.isLoding = false;
                        Uri uri = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            String authority = BuildConfig.APPLICATION_ID + ".fileprovider";
                            uri = FileProvider.getUriForFile(activity, authority, new File(filePath));
                        } else {
                            uri = Uri.fromFile(new File(filePath));
                        }
                        if(uri!=null){
                            activity.shareFile(uri);
                        }
                        activity.showToast("下载路径为:"+filePath);

                        return;
                    }
                }catch (Exception e){
                }
                activity.isLoding = false;
                activity.showToast("导出失败!");
            }
        }).start();
    }

    /*
     * 获取图纸的信息，然后保存
     */
    @JavascriptInterface
    public void saveImageInfo(String param, String callbackSuccess, String callbackFail, String callbackComplete) {
        if (param != null) {
            File directory = new File(Config.PATHS_DATA);
            if (directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(Config.PATHS_DATA_FILE);
            if(!activity.isLoding){
                FilesUtils.writeToFile(file, param, false);
            }
//            Config.saveImageInfos(activity,param);
        }
    }


    private int postLenght = 1000;
    private String separate = "&&##@@";

    /*
     * html加载完成之后，会调用（数据还没开始调用）
     *
     * 然后Android调用js的
     */
    @JavascriptInterface
    public void getImageInfo(String param, String callbackSuccess, String callbackFail, String callbackComplete) {
        activity.isLoding = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(Config.PATHS_DATA_FILE);
                if (file.exists()) {
                    L.i(TAG, "文件存在");
                    try {
                        String imageInfo = FilesUtils.readTxtFile(file);
                        if (imageInfo != null && !"".equals(imageInfo)) {
                            int imageInfoLength = imageInfo.length();
                            int number = (int) Math.ceil((double) imageInfoLength / postLenght);
                            String data = "";
                            for (int x = 0; x < number; x++) {
                                if (x == (number - 1)) {
                                    data = imageInfo;
                                    imageInfo = "";
                                } else {
                                    data = imageInfo.substring(0, postLenght);
                                    imageInfo = imageInfo.substring(postLenght, imageInfo.length());
                                }
                                String msgData = number + separate + (x + 1) + separate + data;


                                webView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        L.log(TAG, "msgData:::::" + msgData);
                                        webView.loadUrl("javascript:setImageInfo('" + msgData + "')");
                                    }
                                });
                            }
                        }
                    } catch (Exception e) {
                        L.log(TAG, "Exception:::::" + e.toString());
                        e.printStackTrace();
                    }
                } else {
                    L.i(TAG, "文件不存在");
                    webView.post(new Runnable() {
                        @Override
                        public void run() {
                            activity.hideLoading();
                        }
                    });
                }

                activity.isLoding = false;
            }
        }).start();
    }

    /*
     * html加载完成之后，会调用（数据还没开始调用）
     *
     * 然后Android调用js的
     */
    @JavascriptInterface
    public void getImageInfoSuccess(String param, String callbackSuccess, String callbackFail, String callbackComplete) {
        L.log(TAG, "getImageInfoSuccess:::::" + param);
        activity.isLoding = false;
        webView.post(new Runnable() {
            @Override
            public void run() {
                activity.hideLoading();
            }
        });
    }
}
