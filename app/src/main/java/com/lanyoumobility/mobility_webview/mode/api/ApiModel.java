package com.lanyoumobility.mobility_webview.mode.api;


import com.lanyoumobility.mobility_webview.mode.base.BaseBean;
import com.lanyoumobility.mobility_webview.mode.base.BaseModel;
import com.lanyoumobility.mobility_webview.mode.entity.ProjectGroupListBean;
import com.lanyoumobility.mobility_webview.mode.entity.UserInfoBean;
import com.lanyoumobility.mobility_webview.mode.entity.UserLoginBean;
import com.lanyoumobility.mobility_webview.mode.entity.WxPayDepositBean;

import okhttp3.MultipartBody;
import rx.Observable;

/**
 * Created by EZ on 2016/11/14.
 */

public class ApiModel extends BaseModel<ApiService, ApiModel> {

    volatile private static ApiModel apiModel;

    public ApiModel(String url) {
        super(url);
    }


    @Override
    protected Class<ApiService> getServiceClass() {
        return ApiService.class;
    }

    public static ApiModel getInstance() {
        if (apiModel == null) {
            synchronized (ApiModel.class) {
                if (apiModel == null) {
                    apiModel = new ApiModel(ApiDefine.TUDING_BASE_URL);
                }
            }
        }
        return apiModel;
    }


    /**
     * 用户登录
     * @param phone
     * @param password
     * @param loginType
     * @return
     */
    public Observable<UserLoginBean> login(String phone, String password, String loginType) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("phone", phone);
        builder.addFormDataPart("password", password);
        builder.addFormDataPart("loginType", loginType);
        return getService().login(builder.build());
    }

    /**
     * 用户注册
     * @param phone
     * @param password
     * @param messageCode
     * @return
     */
    public Observable<UserLoginBean> regiest(String phone, String password, String messageCode) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("phone", phone);
        builder.addFormDataPart("password", password);
        builder.addFormDataPart("messageCode", messageCode);
        return getService().regiest(builder.build());
    }


    /**
     * 获取项目组列表
     */
    public Observable<UserInfoBean> getUserInfo() {
        return getService().getUserInfo();
    }

    /**
     * 获取项目组列表
     */
    public Observable<ProjectGroupListBean> projectGroupList() {
        return getService().projectGroupList();
    }




    /**
     * 订单支付
     *
     * @param id
     * @param payType
     * @return
     */
    public Observable<WxPayDepositBean> payOrder(String id, String payType) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("id", id);
        builder.addFormDataPart("payType", payType);
        return getService().payOrder(builder.build());
    }


}
