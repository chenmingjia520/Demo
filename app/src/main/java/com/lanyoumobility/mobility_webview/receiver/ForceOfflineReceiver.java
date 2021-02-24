package com.lanyoumobility.mobility_webview.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lanyoumobility.mobility_webview.utils.ActivityController;
import com.lanyoumobility.mobility_webview.utils.L;
import com.lanyoumobility.mobility_webview.ui.activity.LoginActivity;


/**
 * Created by EZ on 2017/2/22.
 */

public class ForceOfflineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, LoginActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        L.i("MainActivity","启动登录界面");
        ActivityController.getCurrActivity().startActivity(intent1); // 重新启动LoginActivity
        ActivityController.finishOtherActivity(LoginActivity.class); // 销毁所有活动
    }
}
