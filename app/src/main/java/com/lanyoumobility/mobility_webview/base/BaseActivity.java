package com.lanyoumobility.mobility_webview.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


import com.lanyoumobility.mobility_webview.BuildConfig;
import com.lanyoumobility.mobility_webview.R;
import com.lanyoumobility.mobility_webview.widget.ProgressDialogs;
import com.lanyoumobility.mobility_webview.permissionlib.PermissionActivity;
import com.lanyoumobility.mobility_webview.presenter.base.BaseOldPresenter;
import com.lanyoumobility.mobility_webview.utils.CallDialogUtil;
import com.lanyoumobility.mobility_webview.utils.L;
import com.lanyoumobility.mobility_webview.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by EZ on 2016/10/8.
 */

public abstract class BaseActivity<V, T extends BaseOldPresenter<V>> extends PermissionActivity implements BaseView {

    public List<Bitmap> listBitMap = new ArrayList<>();

    private   final String TAG = "BaseActivity";
    public Context mContext;
    protected T mPresenter;
    protected ProgressDialogs mProgressDialogs;
    private boolean isFirstInit = true;
    protected boolean isMainActivity = true;
    private boolean isChangedBar;

    public void addBitMap(Bitmap bitmap){
        listBitMap.add(bitmap);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mContext = this;
//        //设置沉浸式状态栏
//        if (isMainActivity) {
//            fullScreen(this);
//        } else {
//            fullScreen2(this);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            //实现状态栏图标和文字颜色为暗色
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }

        //根据状态栏颜色来决定状态栏文字用黑色还是白色
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);
        //设置布局
        if (getLayoutResId() != 0) {
            setContentView(getLayoutResId());
            //setBar();
        }

        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
        mProgressDialogs = new ProgressDialogs(this);

        initCreateData(savedInstanceState);
        initView();
        initData();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void showLoading() {
        showLoading("");
    }

    @Override
    public void showLoading(String msg) {
        if (mProgressDialogs != null) {
            mProgressDialogs.showDialog("");
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialogs != null) {
            mProgressDialogs.closeDialog();
        }
    }


    public void onSucceed() {
        hideLoading();
    }

    public void onError() {
        hideLoading();
        showToast(R.string.network_failure);
    }

    protected void initView() {
    }

    protected void initData() {
    }


    protected void initCreateData(Bundle savedInstanceState) {
    }
    protected abstract T initPresenter();

    protected abstract int getLayoutResId();



    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getRunningActivityName();
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        if (mProgressDialogs != null) {
            mProgressDialogs.closeDialog();
            mProgressDialogs = null;
        }
        for(Bitmap bitmap :listBitMap){
            if(bitmap!=null&&!bitmap.isRecycled()){
                bitmap.recycle();	// 回收bitmap的内存
                bitmap = null;
            }
        }
        super.onDestroy();
    }




    public void toActivity(Class toClass) {
        toActivity(toClass, null);
    }

    protected void toActivity(Class toClass, Bundle bundle) {
        toActivity(toClass, bundle, -1);
    }

    protected void toActivity(Class toClass, int requestCode) {
        toActivity(toClass, null, requestCode);
    }

    protected void toActivity(Class toClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, toClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (requestCode != -1) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }



    protected void callMobile(final String mobile) {
        checkPermission(new CheckPermListener() {
            @Override
            public void superPermission() {
                CallDialogUtil.call(mobile);
            }
        }, R.string.permission_call, Manifest.permission.CALL_PHONE);
    }


    protected void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//21
            //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //如果为全透明模式，取消设置Window半透明的Flag
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        } else {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            attributes.flags |= flagTranslucentStatus;
            //attributes.flags |= flagTranslucentNavigation;
            window.setAttributes(attributes);
        }
    }

    protected void fullScreen2(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//19
            //设置无标题
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //设置全屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //取消全屏设置
            // getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }






    protected void getRunningActivityName() {
        if (BuildConfig.DEBUG) {
            String activityName = this.getClass().getSimpleName();
            L.i(TAG, "###Activity==============【当前Aty】: " + activityName);
        }
    }



    private Toast toase = null;
    /**
     * 吐司(系统自带):字符串类型
     *
     * @param msg
     */
    public  void showToast( String msg) {
        if(toase==null){
            toase = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        toase.setText(msg);
        toase.show();
    }


    /**
     * 吐司(系统自带): ID类型(xml中)
     *
     * @param msgResId
     */
    public  void showToast( int msgResId) {
        if(toase==null){
            toase = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        toase.setText(msgResId);
        toase.show();
    }



    /**
     * 获取屏幕分辨率
     *
     * @return int[]
     * disPlays[0] = dm.widthPixels;
     * disPlays[1] = dm.heightPixels;
     */
    ArrayList<Integer> disPlays;
    public ArrayList getDisPlayWidthHeight() {
        if (disPlays == null) {
            disPlays = new ArrayList<Integer>(2);
            DisplayMetrics dm = getResources().getDisplayMetrics();
            disPlays.add(dm.widthPixels);
            disPlays.add(dm.heightPixels);
        }
        return disPlays;
    }


    public  int getMaxPage(int total, long pageSize){
        try {
            double num = (double) (total)/pageSize;
            return (int) (Math.ceil(num));
        }catch (Exception e){
        }
        return 0;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
