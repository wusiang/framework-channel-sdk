package com.xianmao.mt.sdk.models;

import com.xianmao.common.sdk.AbstractRequest;
import com.xianmao.common.sdk.EndpointEnum;
import com.xianmao.mt.sdk.endpoint.TestEndpointEnum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class TestRequest extends AbstractRequest {

    @SerializedName("SdkAppId")
    @Expose
    private Long SdkAppId;

    public Long getSdkAppId() {
        return SdkAppId;
    }

    public void setSdkAppId(Long sdkAppId) {
        SdkAppId = sdkAppId;
    }

    @Override
    public EndpointEnum getEndPointEnum() {
        return TestEndpointEnum.Test;
    }

    @Override
    protected void toMap(HashMap<String, String> map, String prefix) {
        this.setParamSimple(map, prefix + "SdkAppId", this.SdkAppId);
    }
}
