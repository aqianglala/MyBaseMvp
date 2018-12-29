package com.example.administrator.mybasemvp.base;


import com.example.administrator.mybasemvp.base.mvp.IModel;
import com.example.administrator.mybasemvp.base.mvp.IPresenter;
import com.example.administrator.mybasemvp.base.mvp.IView;

import java.lang.ref.WeakReference;
import java.util.HashMap;


public abstract class BasePresenter<V extends IView> implements IPresenter {
    private WeakReference actReference;
    protected V iView;

    public abstract HashMap<String, IModel> getModelMap();

    @Override
    public void attachView(IView iView) {
        actReference = new WeakReference(iView);
    }

    @Override
    public void detachView() {
        if (actReference != null) {
            actReference.clear();
            actReference = null;
        }
    }

    @Override
    public V getIView() {
        return (V) actReference.get();
    }

    public abstract HashMap<String, IModel> loadModelMap(IModel... models);

}
