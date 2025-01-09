package com.xianmao.mt.sdk.models;

import com.xianmao.common.sdk.CommonResponse;

public abstract class TianmaoBaseResonse extends CommonResponse {

    public String data;

    private Integer code;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
