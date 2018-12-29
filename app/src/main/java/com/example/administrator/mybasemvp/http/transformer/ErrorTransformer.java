package com.example.administrator.mybasemvp.http.transformer;


import com.example.administrator.mybasemvp.base.BaseHttpResult;
import com.example.administrator.mybasemvp.http.exception.ErrorType;
import com.example.administrator.mybasemvp.http.exception.ExceptionEngine;
import com.example.administrator.mybasemvp.http.exception.ServerException;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;


public class ErrorTransformer<T> implements ObservableTransformer<BaseHttpResult<T>, T> {

    private static ErrorTransformer errorTransformer = null;
    private static final String TAG = "ErrorTransformer";

    @Override
    public ObservableSource<T> apply(Observable<BaseHttpResult<T>> upstream) {
        return upstream.map(new Function<BaseHttpResult<T>, T>() {
            @Override
            public T apply(BaseHttpResult<T> httpResult) throws Exception {
                if (httpResult == null)
                    throw new ServerException(ErrorType.EMPTY_BEAN, "解析对象为空");

                Logger.t(TAG).e( httpResult.toString());

                if (httpResult.getStatus() != ErrorType.SUCCESS)
                    throw new ServerException(httpResult.getStatus(), httpResult.getMessage());
                return httpResult.getData();
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
            @Override
            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
                //ExceptionEngine为处理异常的驱动器throwable
                throwable.printStackTrace();
                return Observable.error(ExceptionEngine.handleException(throwable));
            }
        });
    }

    /**
     * @return 线程安全, 双层校验
     */
    public static <T> ErrorTransformer<T> getInstance() {

        if (errorTransformer == null) {
            synchronized (ErrorTransformer.class) {
                if (errorTransformer == null) {
                    errorTransformer = new ErrorTransformer<>();
                }
            }
        }
        return errorTransformer;

    }

}
