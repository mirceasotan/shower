package com.mircea.sotan.repository.networking;

import android.support.annotation.Nullable;

/**
 *
 */
public class NetworkError {
    private int httpCode = -1;
    private String code;

    public NetworkError(int httpCode, @Nullable String code) {
        this.httpCode = httpCode;
        this.code = code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(httpCode);

        if (code != null) {
            builder.append(code);
        }

        return builder.toString();
    }
}
