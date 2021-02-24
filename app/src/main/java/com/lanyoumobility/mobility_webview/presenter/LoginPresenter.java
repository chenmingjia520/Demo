package com.lanyoumobility.mobility_webview.presenter;


import com.lanyoumobility.mobility_webview.MyApplication;
import com.lanyoumobility.mobility_webview.R;
import com.lanyoumobility.mobility_webview.mode.api.ApiModel;
import com.lanyoumobility.mobility_webview.mode.base.BaseBean;
import com.lanyoumobility.mobility_webview.mode.entity.ProjectGroupListBean;
import com.lanyoumobility.mobility_webview.mode.entity.UserInfoBean;
import com.lanyoumobility.mobility_webview.mode.entity.UserLoginBean;
import com.lanyoumobility.mobility_webview.presenter.base.BaseOldPresenter;
import com.lanyoumobility.mobility_webview.utils.Config;
import com.lanyoumobility.mobility_webview.utils.MD5;
import com.lanyoumobility.mobility_webview.utils.StringUtils;
import com.lanyoumobility.mobility_webview.utils.ToastUtil;
import com.lanyoumobility.mobility_webview.ui.activity.LoginActivity;

import org.apache.poi.util.StringUtil;

public class LoginPresenter extends BaseOldPresenter<LoginActivity> {

    private final String TAG = "LoginPresenter";
    /**
     * 用户登录
     */
    public void login(String username, String password, String loginType) {
        mView.showLoading();
        observerListener(ApiModel.getInstance().login(username, MD5.GetMD5Code(password),loginType), mView, new rx.Observer<UserLoginBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.hideLoading();
                mView.showToast(R.string.network_failure);
            }

            @Override
            public void onNext(UserLoginBean baseBean) {
                mView.hideLoading();
                if("ok".equals(baseBean.getCode())){
                    String token = baseBean.getResult();
                    if(!StringUtils.isEmpty(token)){
                        Config.saveAccessToken(token);
                        getUserInfo();
                    }
                }
                ToastUtil.showToast(mView,baseBean.getMessage());
            }
        });
    }

    /**
     * 用户注册
     */
    public void regiest(String username, String password, String messageCode) {
        mView.showLoading();
        observerListener(ApiModel.getInstance().regiest(username, MD5.GetMD5Code(password),messageCode), mView, new rx.Observer<UserLoginBean>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.hideLoading();
                mView.showToast(R.string.network_failure);
            }

            @Override
            public void onNext(UserLoginBean baseBean) {
                if("ok".equals(baseBean.getCode())){
                    String token = baseBean.getResult();
                    if(!StringUtils.isEmpty(token)){
                        Config.saveAccessToken(token);
                        getUserInfo();
                        return;
                    }
                }
                mView.hideLoading();
                ToastUtil.showToast(mView,baseBean.getMessage());
            }
        });
    }

    /**
     * 获取项目组列表
     */
    public void getUserInfo() {
        observerListener(ApiModel.getInstance().getUserInfo(), mView, new rx.Observer<UserInfoBean>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.hideLoading();
                mView.showToast(R.string.network_failure);
            }

            @Override
            public void onNext(UserInfoBean baseBean) {
                if("ok".equals(baseBean.getCode())){
                  UserInfoBean.ResultBean resultBean = baseBean.getResult();
                  if(resultBean!=null){
                      MyApplication.setUserInfo(resultBean);
                      projectGroupList();
                      return;
                  }
                }
                mView.hideLoading();
                ToastUtil.showToast(mView,baseBean.getMessage());
            }
        });
    }



    /**
     * 获取项目组列表
     */
    public void projectGroupList() {
        observerListener(ApiModel.getInstance().projectGroupList(), mView, new rx.Observer<ProjectGroupListBean>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.hideLoading();
                mView.showToast(R.string.network_failure);
            }

            @Override
            public void onNext(ProjectGroupListBean baseBean) {
                mView.hideLoading();
                if("ok".equals(baseBean.getCode())){
                    mView.projectGroupListSuccess(baseBean);
                    return;
                }
                ToastUtil.showToast(mView,baseBean.getMessage());
            }
        });
    }

}
