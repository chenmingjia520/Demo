package com.lanyoumobility.mobility_webview.presenter.base;

import android.content.Context;

import com.lanyoumobility.mobility_webview.mode.transformer.SchedulerRentCarTransformer;

import java.io.Serializable;

import rx.Observable;
import rx.Observer;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by EZ on 2016/10/10.
 */

public class BaseOldPresenter<T> {

    protected CompositeSubscription compositeSubscription;

    public T mView;

    public BaseOldPresenter() {
        compositeSubscription = new CompositeSubscription();
    }

    public void attach(T view) {
        this.mView = view;
    }

    public void detach() {
        if(compositeSubscription!=null)
        compositeSubscription.unsubscribe();
        mView = null;
    }

    public <T extends Serializable> void observerListener(Observable<T> observable, Context context, Observer oberver) {
        compositeSubscription.add(observable.compose(new SchedulerRentCarTransformer(context)).subscribe(oberver));
    }



}
