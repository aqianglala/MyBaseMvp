package com.example.administrator.mybasemvp.base;


import com.example.administrator.mybasemvp.http.exception.ApiException;

import io.reactivex.Observer;


public abstract class BaseSubscriber<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        ApiException apiException = (ApiException) e;
        onError(apiException);
    }

    protected abstract void onError(ApiException e);

}
