package com.lanyoumobility.mobility_webview.mode.transformer;

import android.content.Context;
import android.content.Intent;


import com.lanyoumobility.mobility_webview.MyApplication;
import com.lanyoumobility.mobility_webview.mode.base.BaseBean;
import com.lanyoumobility.mobility_webview.utils.Config;
import com.lanyoumobility.mobility_webview.utils.L;
import com.lanyoumobility.mobility_webview.utils.ToastUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class SchedulerRentCarTransformer<T extends BaseBean> implements Observable.Transformer<T, T> {

    private final String TAG = "SchedulerRentCarTransformer";
    private Context context;
    public SchedulerRentCarTransformer(Context context) {
        if (context == null) {
            this.context = MyApplication.getInstance();
        } else {
            this.context = context.getApplicationContext();
        }
    }


    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<T, T>() {
                    @Override
                    public T call(T t) {
                        if("logOut".equals(t.getCode())){

                            L.i(TAG,"身份认证过期....11111....");
                            Intent intent = new Intent(Config.ACTION_FORCE_OFFLINE);
                            intent.setClassName(context,"com.lanyoumobility.manage.receiver.ForceOfflineReceiver");
                            //授权过期
                            context.sendBroadcast(intent);
                            ToastUtil.showToast(context,"授权过期");
                        }
                        return t;
                    }
                });
    }





}