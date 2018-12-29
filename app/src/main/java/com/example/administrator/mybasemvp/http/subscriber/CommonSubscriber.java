package com.example.administrator.mybasemvp.http.subscriber;

import android.content.Context;

import com.example.administrator.mybasemvp.base.BaseSubscriber;
import com.example.administrator.mybasemvp.http.exception.ApiException;
import com.example.administrator.mybasemvp.utils.NetworkUtil;
import com.orhanobut.logger.Logger;


import io.reactivex.disposables.Disposable;


public abstract class CommonSubscriber<T> extends BaseSubscriber<T> {

    private Context context;

    protected CommonSubscriber(Context context) {
        this.context = context;
    }

    private static final String TAG = "CommonSubscriber";

    @Override
    public void onSubscribe(Disposable d) {
        if (!NetworkUtil.isNetworkAvailable(context)) {
            Logger.t(TAG).e("网络不可用");
        } else {
            Logger.t(TAG).e("网络可用");
        }
    }

    @Override
    protected void onError(ApiException e) {
        Logger.t(TAG).e("错误信息为 " + "code:" + e.code + "   message:" + e.message);
    }

    @Override
    public void onComplete() {
        Logger.t(TAG).e("成功了");
    }
}
