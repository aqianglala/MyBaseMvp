package com.example.administrator.mybasemvp.http.transformer;

import com.example.administrator.mybasemvp.base.BaseHttpResult;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle3.components.support.RxFragment;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RxHelper<T> {

    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }
//
//    public static <T> ObservableTransformer<T, T> io_main(RxAppCompatActivity activity) {
//        return upstream ->
//                upstream.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .compose(RxLifecycleAndroid.bindActivity(activity.lifecycle()));
//    }
//
//    public static <T> ObservableTransformer<T, T> io_main(RxFragment fragment) {
//        return upstream ->
//                upstream.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .compose(RxLifecycleAndroid.bindFragment(fragment.lifecycle()));
//    }

    public static <T> ObservableTransformer<BaseHttpResult<T>, T> io_main(RxAppCompatActivity activity) {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycleAndroid.bindActivity(activity.lifecycle()))
                        .compose(ErrorTransformer.<T>getInstance());
    }

    public static <T> ObservableTransformer<BaseHttpResult<T>, T> io_main(RxFragment fragment) {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycleAndroid.bindFragment(fragment.lifecycle()))
                        .compose(ErrorTransformer.<T>getInstance());
    }

}