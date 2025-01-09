package com.xianmao.mt.sdk.models;

import com.xianmao.common.sdk.CommonResponse;


public class TianmaoLoginResponse extends TianmaoBaseResonse {
    /**
     * 身份标识
     */
    private String accessToken;
    /**
     * 过期时间（毫秒）
     */
    private Long expireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
