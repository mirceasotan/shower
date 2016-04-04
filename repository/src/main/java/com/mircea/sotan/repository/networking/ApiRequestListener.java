package com.mircea.sotan.repository.networking;

/**
 * Created by mircea
 */
public abstract class ApiRequestListener<T> {
    public abstract void onResponse(ApiResponse<T> response);

    public abstract void onError(NetworkError error);
}
