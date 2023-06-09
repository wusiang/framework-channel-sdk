package com.xianmao.common.sdk.http;

import com.xianmao.common.sdk.exception.ChannelSdkException;
import okhttp3.*;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

;

public class HttpConnection {

    private OkHttpClient client;
    private static final OkHttpClient clientSingleton = new OkHttpClient();

    public HttpConnection(Integer connTimeout, Integer readTimeout, Integer writeTimeout) {
        this.client = clientSingleton.newBuilder()
                .connectTimeout(connTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .build();
    }

    public void addInterceptors(Interceptor interceptor) {
        this.client = this.client.newBuilder().addInterceptor(interceptor).build();
    }

    public void setProxy(Proxy proxy) {
        this.client = this.client.newBuilder().proxy(proxy).build();
    }

    public void setProxyAuthenticator(Authenticator authenticator) {
        this.client = this.client.newBuilder().proxyAuthenticator(authenticator).build();
    }

    @Deprecated
    public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.client = this.client.newBuilder().sslSocketFactory(sslSocketFactory).build();
    }

    public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory, X509TrustManager trustManager) {
        this.client = this.client.newBuilder().sslSocketFactory(sslSocketFactory, trustManager).build();
    }

    public Response doRequest(Request request) throws ChannelSdkException {
        Response response = null;
        try {
            response = this.client.newCall(request).execute();
        } catch (IOException e) {
            throw new ChannelSdkException(e.getClass().getName() + "-" + e.getMessage());
        }
        return response;
    }

    public Response getRequest(String url) throws ChannelSdkException {
        Request request = null;
        try {
            request = new Request.Builder().url(url).get().build();
        } catch (IllegalArgumentException e) {
            throw new ChannelSdkException(e.getClass().getName() + "-" + e.getMessage());
        }

        return this.doRequest(request);
    }

    public Response getRequest(String url, Headers headers) throws ChannelSdkException {
        Request request = null;
        try {
            request = new Request.Builder().url(url).headers(headers).get().build();
        } catch (IllegalArgumentException e) {
            throw new ChannelSdkException(e.getClass().getName() + "-" + e.getMessage());
        }

        return this.doRequest(request);
    }

    public Response postRequest(String url, String body) throws ChannelSdkException {
        MediaType contentType = MediaType.parse("application/x-www-form-urlencoded");
        Request request = null;
        try {
            request = new Request.Builder().url(url).post(RequestBody.create(contentType, body)).build();
        } catch (IllegalArgumentException e) {
            throw new ChannelSdkException(e.getClass().getName() + "-" + e.getMessage());
        }

        return this.doRequest(request);
    }

    public Response postRequest(String url, String body, Headers headers)
            throws ChannelSdkException {
        MediaType contentType = MediaType.parse(headers.get("Content-Type"));
        Request request = null;
        try {
            request =
                    new Request.Builder()
                            .url(url)
                            .post(RequestBody.create(contentType, body))
                            .headers(headers)
                            .build();
        } catch (IllegalArgumentException e) {
            throw new ChannelSdkException(e.getClass().getName() + "-" + e.getMessage());
        }

        return this.doRequest(request);
    }

    public Response postRequest(String url, byte[] body, Headers headers)
            throws ChannelSdkException {
        MediaType contentType = MediaType.parse(headers.get("Content-Type"));
        Request request = null;
        try {
            request =
                    new Request.Builder()
                            .url(url)
                            .post(RequestBody.create(contentType, body))
                            .headers(headers)
                            .build();
        } catch (IllegalArgumentException e) {
            throw new ChannelSdkException(e.getClass().getName() + "-" + e.getMessage());
        }

        return this.doRequest(request);
    }
}
