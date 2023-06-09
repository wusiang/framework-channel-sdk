package com.xianmao.common.sdk;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class TCLog implements Interceptor {
    private Logger logger = null;

    public TCLog(String name) {
        logger = LoggerFactory.getLogger(name);
    }

    public void info(final String str) {
        logger.info(str);
    }

    public void info(final String str, final Throwable t) {
        logger.info(str, t);
    }

    public void debug(final String str) {
        logger.debug(str);
    }

    public void debug(final String str, final Throwable t) {
        logger.debug(str, t);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String req = ("send request, request url: " + request.url() + ". request headers information: " + request.headers().toString());
        req = req.replaceAll("\n", ";");
        this.info(req);
        Response response = chain.proceed(request);
        String resp = ("recieve response, response url: " + response.request().url() + ", response headers: " + response.headers().toString() + ",response body information: " + response.body().toString());
        resp = resp.replaceAll("\n", ";");
        this.info(resp);
        return response;
    }

}