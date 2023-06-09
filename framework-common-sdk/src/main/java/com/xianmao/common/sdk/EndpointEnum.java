package com.xianmao.common.sdk;

import java.io.Serializable;


public interface EndpointEnum extends Serializable {
    /**
     * 获取code码
     */
    String getCode();

    /**
     * 获取请求方式
     */
    String getMethod();

    /**
     * 获取请求接口
     */
    String getUrl();

    /**
     * 获取接口描述
     */
    String getDesc();

    /**
     * 获取是否重试
     */
    Boolean getRetry();
}
