package com.example.springbootdemo.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResp<T> implements Serializable {
    private static final long serialVersionUID = 9211889136173018364L;

    private static final int SUCCESS_CODE = 200;

    private static final String SUCCESS_MSG = "success";


   //Api对象的三个属性
    private int code = SUCCESS_CODE;
    private String msg = SUCCESS_MSG;
    private T data;

    public static <T> ApiResp<T> ok(T data){
        ApiResp<T> apiResp = new ApiResp<>();
        apiResp.setData(data);
        return apiResp;
    }

    public static <T> ApiResp<T> ok(){
        ApiResp<T> apiResp = new ApiResp<>();
        apiResp.setData((T) "");
        return apiResp;
    }
}
