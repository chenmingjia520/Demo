package com.lanyoumobility.mobility_webview.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.lanyoumobility.mobility_webview.utils.Config;
import com.lanyoumobility.mobility_webview.utils.L;
import com.lanyoumobility.mobility_webview.utils.MD5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageCacheManager {

    private final  String  cacheDir = Config.PATHS_FILECACHE;
    private HttpURLConnection httpURLConnection;
    /**
     * 网络获取图片
     * @param imageUrl 图片的；链接地址
     */
    public File downlaodImage(String imageUrl) {
        //开启任务
        try {
            URL url=new URL(imageUrl);
            //请求对象
            httpURLConnection=(HttpURLConnection) url.openConnection();
            //网络请求的方式
            httpURLConnection.setRequestMethod("GET");
            //超时的时间，
            httpURLConnection.setConnectTimeout(5000);
            //读取超时的时间
            httpURLConnection.setReadTimeout(5000);
            //httpURLConnection.getResponseCode()拿到最新数据
            if (httpURLConnection.getResponseCode()==200) {
                //success get data from net;get tape
                InputStream inputStream=httpURLConnection.getInputStream();
                //将流转化成bitmap图片
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                //二，将下载完后的图片保存到文件中
                return  writeToLoce(imageUrl,bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭请求
        finally{
            //断开服务器
            if (httpURLConnection!=null) {
                httpURLConnection.disconnect();
            }
        }
        return null;
    };

    /**
     * 图片写入cache文件夹下面的操作
     * @param imageUrl
     * @param bitmap
     */
    private File writeToLoce(String imageUrl, Bitmap bitmap) {
        try {
            int lastIndex = imageUrl.lastIndexOf("/");
           String bitmapefilename = imageUrl.substring(lastIndex,imageUrl.length());
            File file=new File(cacheDir, bitmapefilename);
            FileOutputStream fileOutputStream =new FileOutputStream(file);
            //写入文件的操作(1图片类型2图片质量当为100时表示不压缩3文件流)
            if(bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)){
                return file;
            };
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }



}
