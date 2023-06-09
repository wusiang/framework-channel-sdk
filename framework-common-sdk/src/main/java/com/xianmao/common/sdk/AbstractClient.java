package com.xianmao.common.sdk;

import com.xianmao.common.sdk.exception.ChannelSdkException;
import com.xianmao.common.sdk.http.HttpConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public abstract class AbstractClient {

    public static final int HTTP_RSP_OK = 200;
    public Gson gson;
    private final TCLog log;
    private final HttpConnection httpConnection;
    public static final int MAX_RETRY_TIMES = 2;

    public AbstractClient() {
        this.gson = new GsonBuilder().create();
        this.log = new TCLog(getClass().getName());
        this.httpConnection = new HttpConnection(60, 60, 60);
        this.httpConnection.addInterceptors(this.log);
    }

    protected String execute(String host, AbstractRequest request) throws ChannelSdkException {
        String strResp = null;
        EndpointEnum endpoint = request.getEndPointEnum();
        int maxRetryTimes = 0;
        if (endpoint.getRetry()) {
            maxRetryTimes = MAX_RETRY_TIMES;
        }
        int retryTimes = 0;
        while (retryTimes <= maxRetryTimes) {
            Response okRsp = doRequest(host, endpoint, request);
            try {
                assert okRsp.body() != null;
                strResp = okRsp.body().string();
                if (okRsp.code() == AbstractClient.HTTP_RSP_OK) {
                    return strResp;
                }
            } catch (IOException e) {
                if (retryTimes == maxRetryTimes) {
                    String msg = "Cannot transfer response body to string, because Content-Length is too large, or Content-Length and stream length disagree。";
                    log.info(msg);
                    throw new ChannelSdkException(msg, "", endpoint.getClass().getName());
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new ChannelSdkException(e.toString(), "", "ClientSideError");
            }
            retryTimes++;
            log.info("api:url" + endpoint.getUrl() + ",start" + retryTimes + "retry");
        }

        return strResp;
    }

    private Response doRequest(String host, EndpointEnum endpoint, AbstractRequest request) throws ChannelSdkException {
        HashMap<String, String> param = new HashMap<String, String>();
        request.toMap(param, "");
        String strParam = this.formatRequestData(param);
        String reqMethod = endpoint.getMethod();
        String url = host + endpoint.getUrl();
        if (reqMethod.equals("GET")) {
            return this.httpConnection.getRequest(url + "?" + strParam);
        } else if (reqMethod.equals("POST")) {
            return this.httpConnection.postRequest(url, strParam);
        } else {
            throw new ChannelSdkException("Method only support (GET, POST)");
        }
    }

    private String formatRequestData(Map<String, String> param) throws ChannelSdkException {
        StringBuilder strParam = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                strParam.append(URLEncoder.encode(entry.getKey(), "utf-8")).append("=").append(URLEncoder.encode(entry.getValue(), "utf-8"));
            }
        } catch (UnsupportedEncodingException e) {
            throw new ChannelSdkException(e.getClass().getName() + "-" + e.getMessage());
        }
        return strParam.toString();
    }
}
