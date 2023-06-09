package com.xianmao.mt.sdk.models;

import com.xianmao.common.sdk.AbstractRequest;
import com.xianmao.common.sdk.EndpointEnum;

import java.util.HashMap;

public class TestInfo extends AbstractRequest {

    private String name;

    @Override
    public EndpointEnum getEndPointEnum() {
        return null;
    }

    @Override
    protected void toMap(HashMap<String, String> map, String prefix) {

    }
}
