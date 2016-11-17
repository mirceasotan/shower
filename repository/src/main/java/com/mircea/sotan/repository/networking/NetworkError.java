package com.mircea.sotan.repository.networking;

import android.support.annotation.Nullable;

import retrofit2.Response;

/**
 *
 */
public class NetworkError implements com.mircea.sotan.repository.Error {
    public static final String NO_INTERNET_CONNECTION_MESSAGE = "No Internet Connection";
    private static final int DEFAULT_HTTP_CONNECTION_ERROR_CODE = -1;
    private int httpCode;
    private String codeDescription;

    public NetworkError(int httpCode, @Nullable String code) {
        this.httpCode = httpCode;
        this.codeDescription = code;
    }

    public NetworkError(@Nullable Throwable t) {
        //TODO create error from exc
    }

    public NetworkError(@Nullable Response response) {
        //TODO create error from response
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(httpCode);

        if (codeDescription != null) {
            builder.append(" : ").append(codeDescription);
        }

        return builder.toString();
    }

    @Override
    public String getDescription() {
        return toString();
    }
}
