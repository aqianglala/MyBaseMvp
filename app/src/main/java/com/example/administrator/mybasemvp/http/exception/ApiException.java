package com.example.administrator.mybasemvp.http.exception;


public class ApiException extends RuntimeException {

    public int code;
    public String message;

    ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}
