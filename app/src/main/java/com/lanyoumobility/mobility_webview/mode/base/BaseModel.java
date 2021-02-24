package com.lanyoumobility.mobility_webview.mode.base;

import android.os.Build;
import android.util.Log;


import com.lanyoumobility.mobility_webview.BuildConfig;
import com.lanyoumobility.mobility_webview.mode.interceptor.MoreBaseUrlInterceptor;
import com.lanyoumobility.mobility_webview.utils.L;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by EZ on 2016/10/9.
 */

public abstract class BaseModel<T, R extends BaseModel> {

    private static final String TAG = "BaseModel";
    private T service;
    private Retrofit retrofit;

    public BaseModel(String url) {
        L.i(TAG,"BaseModel:::::"+url);
        retrofit =   getRetrofit(url);
        service = retrofit.create(getServiceClass());
    }



    public Retrofit getRetrofit(String baseUrl){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new MoreBaseUrlInterceptor(baseUrl))
                .addInterceptor(createUserAgentInterceptor())
                .addInterceptor(createHttpLoggingInterceptor());
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH) {
            try {
                TrustManager[] tm = new TrustManager[]{new SsX509TrustManager()};
                SSLSocketFactory socketFactory = new Tls12SocketFactory(null, tm, new SecureRandom());
                builder.sslSocketFactory(socketFactory, (X509TrustManager) tm[0]);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        }
        OkHttpClient client = builder.build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    private Interceptor createUserAgentInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        //.addHeader("versionCode", Integer.toString(AppUtil.getVersionCode(BaseApplication.context)))
                        .addHeader("versionName", "")
                        .addHeader("platform", "android")
                        .addHeader("model", Build.BRAND + Build.MODEL) //手机品牌+该品牌的手机类型，如 华为荣耀8
                        .addHeader("systemVersion", Build.VERSION.RELEASE)
                        .build();

                if(BuildConfig.DEBUG){
                    printRequest("createUserAgentInterceptor", request);
                }

                Response response = chain.proceed(request);

                if(BuildConfig.DEBUG){
                    Response tmpResponse = printResponse("createUserAgentInterceptor", response);
                    return tmpResponse;
                }
                return response;
            }
        };
    }


    private void printRequest(String tag, Request request) {
        Log.e(TAG,"    ");
        Log.e(TAG, "    ");
        Log.e(TAG, "    ");
        Log.e(TAG,  "===========请求信息=========== "+tag);
        Log.e(TAG,  request.toString());
        Log.e(TAG, "    ");
        Log.e(TAG, "请求head：");
        Log.e(TAG, request.headers().toString() );
        Log.e(TAG, "   ");
        Log.e(TAG, "请求body: ");
        String bodyParams = null;
        try {
            RequestBody requestBody = request.body();
            Buffer buffer = new Buffer();
            if(requestBody==null){
                return;
            }
            requestBody.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(Util.UTF_8);
            }
            bodyParams = buffer.readString(charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG, bodyParams);
    }


    private Response printResponse(String tag, Response response) {
        Log.e(TAG,"    ");
        Log.e(TAG, "    ");
        Log.e(TAG, "    ");
        Log.e(TAG,  "===========响应信息=========== "+tag);
        Log.e(TAG,  response.toString());
        Log.e(TAG, "    ");
        Log.e(TAG, "响应head：");
        Log.e(TAG, response.headers().toString());
        Log.e(TAG, "    ");
        Log.e(TAG, "响应body：");
        try {
            Response.Builder builder = response.newBuilder();
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                MediaType type = responseBody.contentType();
                String bodyStr  = responseBody.string();
                L.log(TAG, bodyStr);
                ResponseBody body = ResponseBody.create(type,bodyStr);
                return  builder.body(body).build();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "null");
        return null;
    }


    private static Interceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(Boolean.parseBoolean("true") ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }


    public T getService() {
        return service;
    }

    protected abstract Class<T> getServiceClass();

    public class Tls12SocketFactory extends SSLSocketFactory {
        private SSLSocketFactory internalSSLSocketFactory;

        private SSLContext context;

        public Tls12SocketFactory(KeyManager[] km, TrustManager[] tm, SecureRandom sr) throws KeyManagementException, NoSuchAlgorithmException {
            context = SSLContext.getInstance("TLSv1.2");
            context.init(km, tm, sr);
            internalSSLSocketFactory = context.getSocketFactory();
        }

        @Override
        public String[] getDefaultCipherSuites() {
            return internalSSLSocketFactory.getDefaultCipherSuites();
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return internalSSLSocketFactory.getSupportedCipherSuites();
        }

        @Override
        public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
            SSLSocket sslSocket = (SSLSocket) context.getSocketFactory().createSocket(s, host, port, autoClose);
            sslSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
            return sslSocket;
        }

        @Override
        public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
            SSLSocket sslSocket = (SSLSocket) context.getSocketFactory().createSocket(host, port);
            sslSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
            return sslSocket;
        }

        @Override
        public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
            SSLSocket sslSocket = (SSLSocket) context.getSocketFactory().createSocket(host, port, localHost, localPort);
            sslSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
            return sslSocket;
        }

        @Override
        public Socket createSocket(InetAddress host, int port) throws IOException {
            SSLSocket sslSocket = (SSLSocket) context.getSocketFactory().createSocket(host, port);
            sslSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
            return sslSocket;
        }

        @Override
        public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
            SSLSocket sslSocket = (SSLSocket) context.getSocketFactory().createSocket(address, port, localAddress, localPort);
            sslSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
            return sslSocket;
        }
    }


    public class SsX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[0];
        }
    }

}
