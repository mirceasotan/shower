package com.mircea.sotan.repository.networking;

public class NetworkError {

    private int httpCode = -1;
    private String code;

    public NetworkError() {
        super();
    }

    public NetworkError(int httpCode, String code) {
        this.httpCode = httpCode;
        this.code = code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
