package com.lanyoumobility.mobility_webview.mode.interceptor;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class MoreBaseUrlInterceptor implements Interceptor {
    private static final String TAG = "MoreBaseUrlInterceptor";

    private String baseStringURL = "";
    public MoreBaseUrlInterceptor(String baseURL){
        baseStringURL = baseURL;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原始的originalRequest
        Request originalRequest = chain.request();
        //获取老的url
        HttpUrl oldUrl = originalRequest.url();
        //获取originalRequest的创建者builder
        Request.Builder builder = originalRequest.newBuilder();

        //获取头信息的集合如：manage,mdffx
        List<String> urlnameList = originalRequest.headers("urlname");


        if (urlnameList != null && urlnameList.size() > 0) {
            //删除原有配置中的值,就是namesAndValues集合里的值
            builder.removeHeader("urlname");
            //获取头信息中配置的value,如：manage或者mdffx
            String urlname = urlnameList.get(0);
            HttpUrl baseURL=null;
            //根据头信息中配置的value,来匹配新的base_url地址
            if ("appinforent".equals(urlname)){
                baseURL = HttpUrl.parse(baseStringURL);
                builder.addHeader("Access-Token", get_AES_token());//长短租 token都是加在请求头
                builder.addHeader("Content-Type","application/json");
            }else{
                baseURL = HttpUrl.parse(baseStringURL);
            }
            HttpUrl.Builder builder1 =  oldUrl.newBuilder()
                    .scheme(baseURL.scheme())//http协议如：http或者https
                    .host(baseURL.host())//主机地址
//                    .port(baseURL.port())//端口
                    ;

            HttpUrl newHttpUrl = builder1.build();
            //获取处理后的新newRequest
            Request newRequest = builder.url(newHttpUrl).build();
            return chain.proceed(newRequest);
        }else{
            return chain.proceed(originalRequest);
        }
    }

    private Map<String,String> map;
    private long longtime = 0;//计算时间
    private String Aes_Token = "";//计算时间

    private String get_AES_token(){

        return "";
    }


}
