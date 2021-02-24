package com.lanyoumobility.mobility_webview.mode.api;


import com.lanyoumobility.mobility_webview.mode.entity.ProjectGroupListBean;
import com.lanyoumobility.mobility_webview.mode.entity.UserInfoBean;
import com.lanyoumobility.mobility_webview.mode.entity.UserLoginBean;
import com.lanyoumobility.mobility_webview.mode.entity.WxPayDepositBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public interface ApiService {
    /**
     *订单支付
     */
    @Headers("urlname:appinforent")
    @POST("/lycxoms/app/payOrder")
    Observable<WxPayDepositBean> payOrder(@Body RequestBody requestBody);


    /**
     *登录
     */
    @Headers("urlname:appinfologin")
    @POST("/web/user/login")
    Observable<UserLoginBean> login(@Body RequestBody requestBody);

    /**
     *用户注册
     */
    @Headers("urlname:appinfologin")
    @POST("/web/user/regiest")
    Observable<UserLoginBean> regiest(@Body RequestBody requestBody);


    /**
     *获取用户信息
     */
    @Headers("urlname:appinfologin")
    @POST("/web/user/getUserInfo")
    Observable<UserInfoBean> getUserInfo();



    /**
     *用户注册
     */
    @Headers("urlname:appinfologin")
    @POST("/web/user/regiest")
    Observable<ProjectGroupListBean> projectGroupList();





}
