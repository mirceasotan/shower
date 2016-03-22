package com.mircea.sotan.repository;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by mircea
 */
public class Request<T> {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CREATE, UPDATE, FETCH, DELETE})
    public @interface Type {
    }

    public static final int CREATE = 0;
    public static final int UPDATE = 1;
    public static final int FETCH = 2;
    public static final int DELETE = 3;

    @NonNull
    private String uri;

    public Request(Type type, @NonNull String uri) {
        this.uri = uri;
    }
}
