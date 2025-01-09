package com.xianmao.mt.sdk.endpoint;

import com.xianmao.common.sdk.EndpointEnum;

public enum TianmaoEndpointEnum implements EndpointEnum {

    LoginIn("Login", "POST", "/tianmao/token/login", "登录", false),
    ;
    private final String code;
    private final String method;
    private final String url;
    private final String desc;
    private final Boolean retry;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public Boolean getRetry() {
        return retry;
    }

    TianmaoEndpointEnum(String code, String method, String url, String desc, Boolean retry) {
        this.code = code;
        this.method = method;
        this.url = url;
        this.desc = desc;
        this.retry = retry;
    }
}
