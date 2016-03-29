package com.mircea.sotan.domain;

/**
 * Created by mircea
 */
public interface DataListener<T> {
    void onResponse(T data);

    void onError(String errorMessage);
}
