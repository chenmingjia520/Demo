package com.lanyoumobility.mobility_webview.glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.lanyoumobility.mobility_webview.utils.Config;
import com.lanyoumobility.mobility_webview.utils.MD5;


/**
 * 图片的三级缓存工具类{日后项目需要}
 * @author double 江
 *
 */
public class ImageCachceUitl {

    private ExecutorService executorService = Executors.newFixedThreadPool(5);//维护线性池
    private final  String  cacheDir = Config.PATHS_FILECACHE;

    /**
     * 网络获取图片
     * @param url 图片的；链接地址
     */
    private void getBitmapFromNet(String url) {
        //开启任务
        executorService.execute(new RunnableTask(url));
    }
    //任务接口
    class RunnableTask implements Runnable{
        String imageUrl;
        private HttpURLConnection httpURLConnection;
        public RunnableTask(String imageUrl) {
            this.imageUrl=imageUrl;
        }

        @Override
        public void run() {
            //子线程的操作，开启网络下载图片
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
                    Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
//                    //利用handler机制放入主线程中显示
//                    Message msg=new Message();
//                    //需要在主线程中显示的图片msg.obj
//                    msg.obj=bitmap;
//                    msg.arg1=position;
//                    //为msg设置标记
//                    msg.what=SUCCSEE;
//                    handler.sendMessage(msg);
//                    //一，将下载完后的图片保存到内存中
//                    cache.put(imageUrl, bitmap);
                    //二，将下载完后的图片保存到文件中
                    writeToLoce(imageUrl,bitmap);
                    return;
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
        }
    }
    /**
     * 图片写入cache文件夹下面的操作
     * @param imageUrl
     * @param bitmap
     */
    private File writeToLoce(String imageUrl, Bitmap bitmap) {
        try {
            String bitmapefilename= MD5.GetMD5Code(imageUrl).substring(10);
            Log.i("bitmapefilename", bitmapefilename);
            File file=new File(cacheDir, bitmapefilename);
            FileOutputStream fileOutputStream =new FileOutputStream(file);
            //写入文件的操作(1图片类型2图片质量当为100时表示不压缩3文件流)
            if(bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream)){
                return file;
            };
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}