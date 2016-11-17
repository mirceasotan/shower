package com.mircea.sotan.repository.networking;

/**
 * @author mirceasotan
 */
public abstract class ApiListener<T> {
    /**
     * @param apiResponse
     */
    public abstract void onResponse(ResponseContainer<T> apiResponse);

    /**
     * @param networkError
     */
    public abstract void onError(NetworkError networkError);
}
