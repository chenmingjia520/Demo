package com.lanyoumobility.mobility_webview.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lanyoumobility.mobility_webview.R;
import com.lanyoumobility.mobility_webview.base.BaseActivity;
import com.lanyoumobility.mobility_webview.glide.DownloadTask;
import com.lanyoumobility.mobility_webview.glide.getImageAsyncTask;
import com.lanyoumobility.mobility_webview.mode.entity.ProjectGroupListBean;
import com.lanyoumobility.mobility_webview.presenter.LoginPresenter;
import com.lanyoumobility.mobility_webview.utils.L;
import com.lanyoumobility.mobility_webview.utils.StringUtils;
import com.lanyoumobility.mobility_webview.view.ClearableEditText;
import com.lanyoumobility.mobility_webview.view.PwdVisibleLayout;

import java.io.File;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginActivity, LoginPresenter> {
    private final String TAG = "LoginActivity";

    @BindView(R.id.ll_login_into)
    LinearLayout ll_login_into;

    @BindView(R.id.ll_login_init)
    LinearLayout ll_login_init;


    @BindView(R.id.ll_login)
    LinearLayout ll_login;


    @BindView(R.id.ll_setvice)
    LinearLayout ll_setvice;


    @BindView(R.id.iv_userHeader)
    ImageView iv_userHeader;

    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.pvl_login_password)
    PwdVisibleLayout pvl_login_password;

    @BindView(R.id.rl_codelogin)
    RelativeLayout rl_codelogin;

    @BindView(R.id.et_code_edit)
    ClearableEditText et_code_edit;


    @BindView(R.id.rl_regiestAndCodeLogin)
    RelativeLayout rl_regiestAndCodeLogin;

    @BindView(R.id.et_login_name)
    ClearableEditText et_login_name;



    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        ll_setvice.setVisibility(View.GONE);
        ll_login.setVisibility(View.VISIBLE);

//        new getImageAsyncTask(LoginActivity.this).execute("http://ww4.sinaimg.cn/large/610dc034gw1f96kp6faayj20u00jywg9.jpg");

        DownloadTask downloadTask =  new DownloadTask();
        L.i(TAG,"---1111--->>>>>"+System.currentTimeMillis());
        downloadTask.execute("http://ww4.sinaimg.cn/large/610dc034gw1f96kp6faayj20u00jywg9.jpg");
        L.i(TAG,"---2222--->>>>>"+System.currentTimeMillis());
        try {
           File file =  downloadTask.get();
            L.i(TAG,"---3333--->>>>>"+System.currentTimeMillis());
            L.i(TAG,"getAbsolutePath:::::::::::::::"+file.getAbsolutePath());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
//    /******** 0.显示登录用户信息还是企业信息  1.登录界面 , 2.企业用户登录 ，3.服务协议*******/
//    private int showType = 0;
//    /******** 上一个显示的类型*******/
    /******** 0.普通用户登录 ,1企业用户登录*******/
    private int loginType = 0;
    @OnClick({R.id.iv_back,R.id.tv_userMember , R.id.tv_enterpriseMember, R.id.tv_contract,R.id.tv_userregister,R.id.tv_codelogin,R.id.tv_login})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                if(ll_setvice.getVisibility()==View.VISIBLE){
                    ll_setvice.setVisibility(View.GONE);
                    ll_login.setVisibility(View.VISIBLE);
                    iv_back.setVisibility(View.VISIBLE);
                }else if(ll_login_init.getVisibility()==View.GONE){
                    if(rl_regiestAndCodeLogin.getVisibility()==View.GONE){
                        rl_regiestAndCodeLogin.setVisibility(View.VISIBLE);
                        pvl_login_password.setVisibility(View.VISIBLE);
                        rl_codelogin.setVisibility(View.GONE);
                    }else{
                        ll_login_init.setVisibility(View.VISIBLE);
                        ll_login_into.setVisibility(View.GONE);
                        iv_userHeader.setBackgroundResource(R.drawable.iv_login_head);
                        iv_back.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            case R.id.tv_userMember:
                if(ll_login_into.getVisibility()==View.GONE){
                    ll_login_init.setVisibility(View.GONE);
                    ll_login_into.setVisibility(View.VISIBLE);
                    loginType = 0;
                    iv_userHeader.setBackgroundResource(R.drawable.iv_login_enterprisehead);
                    iv_back.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_enterpriseMember:
                if(ll_login_into.getVisibility()==View.GONE){
                    ll_login_init.setVisibility(View.GONE);
                    ll_login_into.setVisibility(View.VISIBLE);
                    loginType = 1;
                    iv_userHeader.setBackgroundResource(R.drawable.iv_login_userhead);
                    iv_back.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_contract:
                ll_setvice.setVisibility(View.VISIBLE);
                ll_login.setVisibility(View.GONE);
                iv_back.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_userregister:
                pvl_login_password.setVisibility(View.VISIBLE);
                rl_codelogin.setVisibility(View.VISIBLE);
                rl_regiestAndCodeLogin.setVisibility(View.GONE);
                break;
            case R.id.tv_codelogin:
                pvl_login_password.setVisibility(View.GONE);
                rl_codelogin.setVisibility(View.VISIBLE);
                rl_regiestAndCodeLogin.setVisibility(View.GONE);
                break;
            case R.id.tv_login:
                toLogin();
                break;
        }
    }

    private void toLogin(){
        String phone = et_login_name.getText().toString().trim();
        String password = pvl_login_password.getPwd().trim();
        String code = et_code_edit.getText().toString().trim();
        if(StringUtils.isEmpty(phone)){
            showToast("请输入手机号");
            return;
        }
        if(pvl_login_password.getVisibility()==View.GONE){//验证码登录
            if(StringUtils.isEmpty(code)){
                showToast("请输入验证码");
                return;
            }
            mPresenter.login(phone,code,"1");
        }else{
            if(rl_codelogin.getVisibility()==View.GONE){//正常登录
                L.i(TAG,"密码登录");
                if(StringUtils.isEmpty(password)){
                    showToast("请输入密码");
                    return;
                }
                mPresenter.login(phone,password,"2");
            }else{//用户注册登录

                if(StringUtils.isEmpty(password)){
                    showToast("请输入密码");
                    return;
                }
                if(StringUtils.isEmpty(code)){
                    showToast("请输入验证码");
                    return;
                }
                mPresenter.regiest(phone,password,code);
            }
        }
    }

    public void projectGroupListSuccess(ProjectGroupListBean projectGroupListBean){




        toActivity(ImportProjectActivity.class);
    }
}
