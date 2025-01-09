package com.xianmao.mt.sdk.models;


import com.xianmao.common.sdk.CommonRequest;
import com.xianmao.common.sdk.EndpointEnum;
import com.xianmao.mt.sdk.endpoint.TianmaoEndpointEnum;

import java.util.HashMap;

public class TianmaoLoginRequest extends CommonRequest<TianmaoLoginResponse> {

    private String appId;

    /**
     * 账户
     */
    private String account;
    /**
     * 密码
     */
    private String password;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public EndpointEnum getEndPointEnum() {
        return TianmaoEndpointEnum.LoginIn;
    }

    @Override
    protected void toMap(HashMap<String, String> map, String prefix) {
        this.setParamSimple(map, "appId", this.appId);
        this.setParamSimple(map, "account", this.account);
        this.setParamSimple(map, "password", this.password);
    }

    @Override
    public Class<TianmaoLoginResponse> getResponseClass() {
        return TianmaoLoginResponse.class;
    }

}
