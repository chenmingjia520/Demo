package com.lanyoumobility.mobility_webview.webview.download;

//内部类
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import com.lanyoumobility.mobility_webview.utils.Config;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

public class DownloaderTask extends AsyncTask<String, Void, String> {


    private final String TAG = "DownloaderTask";
    private Context mContext;
    public DownloaderTask(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        Log.i(TAG, "url=" + url);
        fileName = URLDecoder.decode(fileName);
        Log.i(TAG, "fileName=" + fileName);
        File file = new File(Config.PATHS_IMG, fileName);
        if (file.exists()) {
            return fileName;
        }

        Log.i(TAG, "index>>>>>>>>>>>,,,,,>" + url.indexOf(","));
        int index = url.indexOf(";base64,");
        Log.i(TAG, "index>>>>>>>>base64>>>>" + index);
        if (index > 0) {
            url = url.substring(index + 8);
            Log.i(TAG, "url>>>>>>>>>>>>" + url);
            Bitmap bitmap = base64ToPicture(url);
            if (bitmap != null) {
                savePictureToAlbum(mContext, bitmap);
            }
        } else {
            index = url.indexOf("blob:");
            if (index == 0) {
                url = url.substring(5);
                Log.i(TAG, "url>>>>>>>>sssss>>>>" + url);
                try {
                    HttpClient client = new DefaultHttpClient();
//                client.getParams().setIntParameter("http.socket.timeout",3000);//设置超时
                    Log.i(TAG, "下载地质:::::::" + url);
                    HttpGet get = new HttpGet(url);

                    HttpResponse response = client.execute(get);

                    if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {

                        HttpEntity entity = response.getEntity();

                        InputStream input = entity.getContent();
                        Log.i(TAG, "Exception::::::::fileName:::::::::" + fileName);
                        writeToSDCard(fileName, input);

                        input.close();

//                  entity.consumeContent();

                        return fileName;

                    } else {
                        Log.i(TAG, "Exception::::::::下载出问题:::::::::" + response.getStatusLine().getStatusCode());
                        Log.i(TAG, "Exception::::::::fileName:::::::::" + fileName);
                        return null;
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }


            }


        }

        return null;
    }

    /**
     * 将图片保存到相册并通知刷新
     */
    public void savePictureToAlbum(Context mContext, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        // 把文件插入到系统图库
        MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
                bitmap, null, null);
        // 通知图库更新
        mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + "/sdcard/namecard/")));
    }

    /**
     * 将图片base64数据转化为bitmap
     *
     * @param imgBase64
     */
    public Bitmap base64ToPicture(String imgBase64) {
        byte[] decode = Base64.decode(imgBase64, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        return bitmap;
    }


    public void writeToSDCard(String fileName, InputStream input) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File directory = new File (Config.PATHS_IMG);
            if (directory.exists()) {
                directory.mkdirs();
            }
            Log.i(TAG, "fileName:::::::::" + fileName);
            File file = new File(directory, fileName);
            Log.i(TAG, "fileName:::::getAbsolutePath::::" + file.getAbsolutePath());

            try {

                FileOutputStream fos = new FileOutputStream(file);

                byte[] b = new byte[2048];

                int j = 0;

                while ((j = input.read(b)) != -1) {

                    fos.write(b, 0, j);

                }

                fos.flush();

                fos.close();

            } catch (FileNotFoundException e) {
                Log.i(TAG, "FileNotFoundException::::::::保存出问题:::::::::" + e.toString());
// TODO Auto-generated catch block

                e.printStackTrace();

            } catch (IOException e) {
                Log.i(TAG, "IOException::::::::保存出问题:::::::::" + e.toString());
// TODO Auto-generated catch block

                e.printStackTrace();

            }

        } else {

            Log.i("tag", "NO SDCard.");

        }

    }

    @Override

    protected void onCancelled() {

// TODO Auto-generated method stub

        super.onCancelled();

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        closeProgressDialog();
        if (result == null) {
            Toast t = Toast.makeText(mContext, "连接错误！请稍后再试！", Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
            return;
        }
        Toast t = Toast.makeText(mContext, "已保存到SD卡。", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
        File directory = new File (Config.PATHS_IMG);
        if (directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, result);
        Log.i(TAG, "Path=" + file.getAbsolutePath());
        Intent intent = getFileIntent(file);
        mContext.startActivity(intent);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showProgressDialog();
    }


    private void showProgressDialog() {
        Toast t = Toast.makeText(mContext, "开始下载。", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }

    private void closeProgressDialog() {
        Toast t = Toast.makeText(mContext, "关闭弹框。", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    public Intent getFileIntent(File file) {
        Uri uri = Uri.fromFile(file);
        String type = getMIMEType(file);
        Log.i("tag", "type=" + type);

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.setDataAndType(uri, type);

        return intent;

    }

    private String getMIMEType(File f) {

        String type = "";

        String fName = f.getName();

        /* 取得扩展名 */

        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();

        /* 依扩展名的类型决定MimeType */

        if (end.equals("pdf")) {

            type = "application/pdf";//

        } else if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||

                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {

            type = "audio/*";

        } else if (end.equals("3gp") || end.equals("mp4")) {

            type = "video/*";

        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||

                end.equals("jpeg") || end.equals("bmp")) {

            type = "image/*";

        } else if (end.equals("apk")) {

            /* android.permission.INSTALL_PACKAGES */

            type = "application/vnd.android.package-archive";

        }

//      else if(end.equals("pptx")||end.equals("ppt")){

//        type = "application/vnd.ms-powerpoint";

//      }else if(end.equals("docx")||end.equals("doc")){

//        type = "application/vnd.ms-word";

//      }else if(end.equals("xlsx")||end.equals("xls")){

//        type = "application/vnd.ms-excel";

//      }

        else {

//        /*如果无法直接打开，就跳出软件列表给用户选择 */

            type = "*/*";

        }

        return type;

    }


}