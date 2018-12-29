package com.example.administrator.mybasemvp.base;


import com.example.administrator.mybasemvp.http.Http;
import com.example.administrator.mybasemvp.http.HttpService;
import com.example.administrator.mybasemvp.base.mvp.IModel;


public class BaseModel implements IModel {
    protected static HttpService httpService;

    static {
        httpService = Http.getHttpService();
    }

}
