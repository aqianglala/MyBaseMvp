package com.example.administrator.mybasemvp.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.mybasemvp.base.mvp.IView;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements
        IView, View.OnClickListener {

    protected View view;

    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), null);
            ButterKnife.bind(this, view);
            mPresenter = loadPresenter();
            mPresenter = loadPresenter();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
            initListener();
            initData();
        } else {
            toast("多次加载布局了");
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract P loadPresenter();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract int getLayoutId();

    protected abstract void otherViewClick(View view);

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                otherViewClick(view);
                break;
        }
    }

    public void toast(String str) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
        }
    }

    public void toast(@StringRes int contentId) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Toast.makeText(activity, contentId, Toast.LENGTH_SHORT).show();
        }
    }

    public void LogE(String str) {
        Logger.t(getClass().getSimpleName()).e(str);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
