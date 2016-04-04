package com.mircea.sotan.repository.networking;

import android.support.annotation.NonNull;

/**
 * Created by mircea
 */
public class ApiResponse<T> {
    int httpCode;
    T data;

    public ApiResponse(T data, int httpCode) {
        this.data = data;
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    @NonNull
    public T getData() {
        return data;
    }
}
