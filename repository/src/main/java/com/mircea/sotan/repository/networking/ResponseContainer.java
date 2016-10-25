package com.mircea.sotan.repository.networking;

import android.support.annotation.NonNull;

/**
 * Created by mircea
 */
public class ResponseContainer<T> {
    private int httpCode;
    private T data;

    public ResponseContainer(T data, int httpCode) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseContainer<?> that = (ResponseContainer<?>) o;

        return httpCode == that.httpCode && (data != null ? data.equals(that.data) : that.data == null);
    }

    @Override
    public int hashCode() {
        int result = httpCode;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
