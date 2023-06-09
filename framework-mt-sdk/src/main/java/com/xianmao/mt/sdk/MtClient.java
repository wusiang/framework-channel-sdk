package com.xianmao.mt.sdk;

import com.xianmao.common.sdk.AbstractClient;
import com.xianmao.common.sdk.AbstractRequest;
import com.xianmao.common.sdk.exception.ChannelSdkException;
import com.xianmao.mt.sdk.models.TestRequest;
import com.xianmao.mt.sdk.models.TestResponse;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MtClient extends AbstractClient {


    private String host;

    private static final Integer ok = 200;

    public MtClient(String host) {
        this.host = host;
    }


    /**
     * 测试方法
     *
     * @param req
     * @return
     * @throws ChannelSdkException
     */
    public TestResponse test(TestRequest req) throws ChannelSdkException {
        MtResponse<TestResponse> rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<MtResponse<TestResponse>>() {
            }.getType();
            rspStr = this.execute(this.host, req);
            rsp = this.gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new ChannelSdkException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        if (rsp.getCode() == null ? ok != null : !rsp.getCode().equals(ok)) {
            throw new ChannelSdkException(rsp.getMessage(), "", String.valueOf(rsp.getCode()));
        }
        return rsp.data;
    }
}
