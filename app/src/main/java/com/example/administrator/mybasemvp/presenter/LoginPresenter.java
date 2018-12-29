package com.example.administrator.mybasemvp.presenter;


import com.example.administrator.mybasemvp.ui.LoginActivity;
import com.example.administrator.mybasemvp.base.BasePresenter;
import com.example.administrator.mybasemvp.contract.LoginContract;
import com.example.administrator.mybasemvp.model.LoginModel;
import com.example.administrator.mybasemvp.base.mvp.IModel;
import com.orhanobut.logger.Logger;

import java.util.HashMap;


public class LoginPresenter extends BasePresenter<LoginActivity> implements
        LoginContract.LoginPresenter {

    @Override
    public void login(String name, String pwd) {
        if (!getIView().checkNull()) {
            ((LoginModel) getModelMap().get("login")).login(getIView(), name, pwd, new LoginModel
                    .InfoHint() {
                @Override
                public void successInfo(String str) {
                    getIView().loginSuccess(str);  //成功
                }

                @Override
                public void failInfo(String str) {
                    Logger.e(str);

                    getIView().loginFail(str);  //失败
                }
            });
        }
    }


    @Override
    public HashMap<String, IModel> getModelMap() {
        return loadModelMap(new LoginModel());
    }

    @Override
    public HashMap<String, IModel> loadModelMap(IModel... models) {
        HashMap<String, IModel> map = new HashMap<>();
        map.put("login", models[0]);
        return map;
    }
}
