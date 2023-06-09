package com.xianmao.common.sdk;

import java.util.HashMap;

public abstract class AbstractRequest {

    public abstract EndpointEnum getEndPointEnum();

    protected abstract void toMap(HashMap<String, String> map, String prefix);

    protected <V> void setParamSimple(HashMap<String, String> map, String key, V value) {
        if (value != null) {

            key = key.substring(0, 1).toUpperCase() + key.substring(1);
            key = key.replace("_", ".");
            map.put(key, String.valueOf(value));
        }
    }
}
