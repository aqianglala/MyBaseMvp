package com.example.administrator.mybasemvp.model.entities;


public class LoginBean {

    /**
     * userId : admin
     * token : success
     */

    private String userId;

    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" +
                "userId='" + userId + '\'' +
                ", token='" + token + '\'' + "}";
    }
}
