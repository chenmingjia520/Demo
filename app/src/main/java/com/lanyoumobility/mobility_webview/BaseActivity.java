package com.lanyoumobility.mobility_webview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import com.lanyoumobility.mobility_webview.dialog.ProgressDialogs;
import com.lanyoumobility.mobility_webview.interfaces.CheckPermListener;

import java.util.List;


public class BaseActivity extends Activity  implements
        EasyPermissions.PermissionCallbacks{

    protected ProgressDialogs mProgressDialogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialogs = new ProgressDialogs(this);
    }



    public void showLoading(String msg) {
        if (mProgressDialogs != null) {
            mProgressDialogs.showDialog(msg);
        }
    }


    public void hideLoading() {
        if (mProgressDialogs != null) {
            mProgressDialogs.closeDialog();
        }
    }




    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {


        return super.onCreateView(name, context, attrs);
    }

    protected static final int RC_PERM = 123;
    /**
     * 权限回调接口
     */
    private CheckPermListener mListener;
    public void checkPermission(CheckPermListener listener, int resString, String... mPerms) {
        mListener = listener;
        if (EasyPermissions.hasPermissions(this, mPerms)) {
            if (mListener != null)
                mListener.superPermission();
        } else {
            EasyPermissions.requestPermissions(this, getString(resString), RC_PERM, mPerms);
        }
    }


    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsAllGranted() {
        if (mListener != null)
            mListener.superPermission();//同意了全部权限的回调
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,
                getString(R.string.perm_tip),
                R.string.setting, R.string.cancel, null, perms);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProgressDialogs != null) {
            mProgressDialogs.closeDialog();
            mProgressDialogs = null;
        }
    }
}
