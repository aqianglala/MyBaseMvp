package com.example.administrator.mybasemvp.contract;


public class LoginContract {

    public interface LoginView {
        String getUserName();

        String getPwd();

        void loginSuccess(String str);

        void loginFail(String failMsg);
    }


    public interface LoginPresenter {
        void login(String name, String pwd);
    }
}
