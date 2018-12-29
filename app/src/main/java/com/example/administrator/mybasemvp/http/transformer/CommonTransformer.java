package com.example.administrator.mybasemvp.http.transformer;


import com.example.administrator.mybasemvp.base.BaseHttpResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CommonTransformer<T> implements ObservableTransformer<BaseHttpResult<T>, T> {

    @Override
    public ObservableSource<T> apply(Observable<BaseHttpResult<T>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(ErrorTransformer.<T>getInstance());
    }
}

