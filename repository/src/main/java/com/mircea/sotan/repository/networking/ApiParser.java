package com.mircea.sotan.repository.networking;

/**
 * Created by mircea
 */
public interface ApiParser<T> {
    public T parse(String body);
}
