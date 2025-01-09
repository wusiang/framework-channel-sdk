package com.xianmao.mt.sdk;

import com.xianmao.common.sdk.CommonRequest;
import com.xianmao.common.sdk.CommonResponse;
import com.xianmao.common.sdk.DefaultClient;
import com.xianmao.common.sdk.exception.ChannelSdkException;

public class TianmaoClient extends DefaultClient {


    public TianmaoClient(String serverUrl) {
        super(serverUrl);
    }

    public <T extends CommonResponse> T execute(CommonRequest<T> request) throws ChannelSdkException {
        return super.execute(request);
    }
}
