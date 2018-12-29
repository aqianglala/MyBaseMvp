package com.example.administrator.mybasemvp.base;

import android.os.Bundle;
import androidx.annotation.StringRes;

import android.view.View;
import android.widget.Toast;

import com.example.administrator.mybasemvp.base.mvp.IView;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements
        IView, View.OnClickListener {
    protected View view;

    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        ButterKnife.bind(this);
        mPresenter = loadPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initListener();
        initData();
    }

    protected abstract P loadPresenter();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract int getLayoutId();

    protected abstract void otherViewClick(View view);

    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                otherViewClick(view);
                break;
        }
    }

    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void toast(@StringRes int contentId) {
        Toast.makeText(this, contentId, Toast.LENGTH_SHORT).show();
    }

    public void LogE(String str) {
        Logger.t(getClass().getSimpleName()).e(str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}

