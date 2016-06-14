package com.mircea.sotan.domain;

import com.mircea.sotan.repository.networking.NetworkError;

/**
 * Created by mircea
 */
public interface DataListener<T> {
    void onResponse(T data);

    void onError(NetworkError error);
}
