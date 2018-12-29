package com.example.administrator.mybasemvp.http;



import com.example.administrator.mybasemvp.base.BaseHttpResult;
import com.example.administrator.mybasemvp.model.entities.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface HttpService {
    //登录接口
    @FormUrlEncoded
    @POST("demo/login")
    Observable<BaseHttpResult<LoginBean>> login(@Field("userName") String username, @Field
            ("passWord") String pwd);
}
