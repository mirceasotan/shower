package com.mircea.sotan.repository;

/**
 * @author mirceasotan
 */
public abstract class DataListener<T> {

    public abstract void onResponse(T data);

    /**
     * @param error
     */
    public abstract void onError(Error error);
}
