package com.mircea.sotan.repository.networking;

/**
 * @author mirceasotan
 */
public abstract class Listener<T> {
    /**
     * @param apiResponse
     */
    public abstract void onResponse(ResponseContainer<T> apiResponse);

    /**
     * @param error
     */
    public abstract void onError(NetworkError error);
}
