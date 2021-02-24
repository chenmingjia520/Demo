package com.lanyoumobility.mobility_webview.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lanyoumobility.mobility_webview.R;
import com.lanyoumobility.mobility_webview.base.BaseActivity;
import com.lanyoumobility.mobility_webview.presenter.WelcomePresenter;
import com.lanyoumobility.mobility_webview.update.AppUtil;
import com.lanyoumobility.mobility_webview.update.bean.UpdateInfoBean;
import com.lanyoumobility.mobility_webview.update.listener.ProgressListener;
import com.lanyoumobility.mobility_webview.update.util.HttpUtils;
import com.lanyoumobility.mobility_webview.utils.Config;
import com.lanyoumobility.mobility_webview.utils.StringUtils;

import java.io.File;


/**
 * 作者： chenmingjia
 * 时间： 2020/12/18.
 */
public class WelcomeActivity extends BaseActivity<WelcomeActivity, WelcomePresenter> {

    private final String TAG = "WelcomeActivity";



    @Override
    protected WelcomePresenter initPresenter() {
        return new WelcomePresenter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        super.initView();
        showCheckPermissionDialog();
    }

    //显示获取权限弹窗
    private void showCheckPermissionDialog() {
        //获取权限后，执行闪屏页正常启动的流程
        checkPermission(this::toSatrtActivity, R.string.permission_storage,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE);//Manifest.permission.CALL_PHONE
    }



    private Handler handler = new Handler();

    private void toSatrtActivity(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String token = Config.getAccessToken();
                if(!StringUtils.isEmpty(token)){
                    toActivity(MainActivity.class);
                }else{
                    toActivity(LoginActivity.class);
                }
                finish();
            }
        },50);
    }




    //当前下载进度
    private int mCurrDownloadProgress;
    // 显示下载进度
    private void showDownloadDialog(String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.dialog_download, null);
        builder.setView(view);
        final ProgressBar pbDownload = view.findViewById(R.id.pb_download);
        final TextView tvPercent = view.findViewById(R.id.tv_download_per);
        final TextView tvInstall = view.findViewById(R.id.tv_download_install);
        //监听下载进度
        final ProgressListener progressListener = new ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                int progress = (int) ((100 * bytesRead) / contentLength);
                pbDownload.setProgress(progress);
                if (mCurrDownloadProgress != progress) {
                    mCurrDownloadProgress = progress;
                    runOnUiThread(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            tvPercent.setText(mCurrDownloadProgress + "%");
                        }
                    });
                }
                if (progress >=100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvInstall.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        };
        tvInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //下载成功后，点击安装apk
                AppUtil.installApk(WelcomeActivity.this,
                        new File(Config.PATHS_APK, Config.APK_NAME));
            }
        });

        builder.setCancelable(false);
        builder.show();

        HttpUtils.downloadFile(getApplicationContext(), url, progressListener);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppUtil.REQUEST_CODE_INSTALL_APK_PERMISSION && resultCode == RESULT_OK){
            //获取APK安装权限成功后返回，点击安装apk
            AppUtil.installApk(WelcomeActivity.this,
                    new File(Config.PATHS_APK, Config.APK_NAME));
        }
    }




    public void getAppAdministrationbyIdSuccess(UpdateInfoBean.DataBean data){
        if(data!=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.update_hint))
                    .setCancelable(false)
                    .setMessage(getString(R.string.find_new_version) + data.getVersionNumber() + "\n" + data.getUpdateContent());

            if ("1".equals(data.getIsForceUpdate()) ) {
                //强制更新
                builder.setPositiveButton(getString(R.string.update_now), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showDownloadDialog(data.getDownloadPath());
                    }
                });
                builder.setCancelable(false);
            } else {
                //非强制更新
                builder.setPositiveButton(getString(R.string.update_now), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showDownloadDialog(data.getDownloadPath());
                    }
                });
                builder.setNegativeButton(getString(R.string.temporarily_not_update), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //mPresenter.getCarType();
                    }
                });
            }
            builder.show();
        }else{
            toSatrtActivity();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
