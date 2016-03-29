package com.mircea.sotan.repository.networking;

/**
 * Created by mircea
 */
public abstract class ApiRequestListener<T> {
    private Object tag;

    public ApiRequestListener(Object tag) {
        this.tag = tag;
    }

    public Object getTag() {
        return tag;
    }

    public abstract void onResponse(ApiResponse<T> response);

    public abstract void onError(NetworkError error);
}
