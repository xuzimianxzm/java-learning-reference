package com.xuzimian.globaldemo.common.basic.io.socket.proxy.http;


public class RequestInfo {
    private Integer port;
    private String host;
    private HttpRequestMethod method;
    private String requestData;

    public RequestInfo() {
    }

    public RequestInfo(String requestData) {
        this.requestData = requestData;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public HttpRequestMethod getMethod() {
        return method;
    }

    public void setMethod(HttpRequestMethod method) {
        this.method = method;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }
}
