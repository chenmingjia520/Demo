package com.lanyoumobility.mobility_webview.update.util;

import android.content.Context;
import android.os.FileUtils;

import com.lanyoumobility.mobility_webview.update.AppUtil;
import com.lanyoumobility.mobility_webview.update.custom.ProgressResponseBody;
import com.lanyoumobility.mobility_webview.update.listener.ProgressListener;
import com.lanyoumobility.mobility_webview.utils.Config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpUtils {

    public static String getJsonContent(String url_path) {
        try {
            URL url = new URL(url_path);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if (code == 200) {
                return changeInputStream(connection.getInputStream());
            }
        } catch (Exception e) {

        }
        return "";
    }

    private static String changeInputStream(InputStream inputStream) {
        String jsonString = "";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        try {
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
            jsonString = new String(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }


    public static void downloadFile(final Context context, String url, final ProgressListener progressListener) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
//                                Request request = chain.request().newBuilder()
//                                        .addHeader("Accept", "Application/JSON").build();
//                                return chain.proceed(request);
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse.newBuilder().body(
                                new ProgressResponseBody(originalResponse.body(), progressListener))
                                .build();
                    }
                }).build();

        Request build = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = response.body().byteStream();
                File apkFile = new File(Config.PATHS_APK, Config.APK_NAME);
                if (apkFile.exists()) {
                    apkFile.delete();
                }
                apkFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(apkFile);
                int len = 0;
                byte[] buffer = new byte[2048];
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.flush();
                fos.close();
                is.close();

                AppUtil.installApk(context, apkFile);
            }
        });
    }


}