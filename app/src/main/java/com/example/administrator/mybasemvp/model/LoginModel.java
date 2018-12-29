package com.example.administrator.mybasemvp.model;

import androidx.annotation.NonNull;

import com.example.administrator.mybasemvp.base.BaseActivity;
import com.example.administrator.mybasemvp.base.BaseApp;
import com.example.administrator.mybasemvp.base.BaseModel;
import com.example.administrator.mybasemvp.model.entities.LoginBean;
import com.example.administrator.mybasemvp.http.exception.ApiException;
import com.example.administrator.mybasemvp.http.subscriber.CommonSubscriber;
import com.example.administrator.mybasemvp.http.transformer.RxHelper;


public class LoginModel extends BaseModel {
    private boolean isLogin = false;

    public boolean login(BaseActivity activity, @NonNull String username, @NonNull String pwd, @NonNull final InfoHint
            infoHint) {

        httpService.login(username, pwd)
                .compose(RxHelper.io_main(activity))
                .subscribe(new CommonSubscriber<LoginBean>(BaseApp.getContext()) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        isLogin = true;
                        infoHint.successInfo(loginBean.getToken());
                    }

                    @Override
                    protected void onError(ApiException e) {
                        super.onError(e);
                        isLogin = false;
                        infoHint.failInfo(e.message);
                    }
                });
        return isLogin;
    }


    //通过接口产生信息回调
    public interface InfoHint {
        void successInfo(String str);

        void failInfo(String str);

    }

}
