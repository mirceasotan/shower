package com.mircea.sotan.repository.networking;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by mircea
 */
public class ApiRequest<T> {
    private final String method;
    private final String url;
    private final ApiParser<T> parser;
    private final ApiRequestListener<T> listener;

    @StringDef({Method.GET, Method.POST, Method.PUT, Method.DELETE})
    @Retention(RetentionPolicy.SOURCE)
    @interface RequestMethod {
    }

    public interface Method {
        String GET = "GET";
        String POST = "POST";
        String PUT = "PUT";
        String DELETE = "DELETE";
    }

    @StringDef({Header.AUTH, Header.CONTENT_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    @interface RequestHeader {
    }

    public interface Header {
        String AUTH = "Authorization";
        String CONTENT_TYPE = "Content-Type";
        String ACCEPT = "Accept";
        String ACCEPT_ENCODING = "Accept-Encoding";
    }

    public ApiRequest(String url, ApiParser<T> parser) {
        this.method = Method.GET;
        this.url = url;
        this.parser = parser;
        this.listener = null;
    }

    public ApiRequest(@RequestMethod String method, String url, ApiParser<T> parser) {
        this.method = method;
        this.url = url;
        this.parser = parser;
        this.listener = null;
    }

    public ApiRequest(@RequestMethod String method, String url, ApiParser<T> parser, ApiRequestListener<T> listener) {
        this.method = method;
        this.url = url;
        this.parser = parser;
        this.listener = listener;
    }
}
