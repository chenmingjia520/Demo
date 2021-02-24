package com.lanyoumobility.mobility_webview.presenter;


import com.lanyoumobility.mobility_webview.R;
import com.lanyoumobility.mobility_webview.mode.api.ApiModel;
import com.lanyoumobility.mobility_webview.mode.base.BaseBean;
import com.lanyoumobility.mobility_webview.presenter.base.BaseOldPresenter;
import com.lanyoumobility.mobility_webview.utils.MD5;
import com.lanyoumobility.mobility_webview.utils.ToastUtil;
import com.lanyoumobility.mobility_webview.ui.activity.WelcomeActivity;

public class WelcomePresenter extends BaseOldPresenter<WelcomeActivity> {

    private final String TAG = "LoginPresenter";
    /**
     * 用户登录
     */
    public void login(String username, String password) {
        observerListener(ApiModel.getInstance().login(username, MD5.GetMD5Code(password),""), mView, new rx.Observer<BaseBean>() {
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
            public void onNext(BaseBean baseBean) {
                mView.hideLoading();
                if("ok".equals(baseBean.getCode())){

                }else{
                    mView.hideLoading();
                    ToastUtil.showToast(mView,baseBean.getMessage());
                }
            }
        });
    }



}
