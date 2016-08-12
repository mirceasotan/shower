package com.mircea.sotan.repository.networking;

import android.support.annotation.Nullable;

/**
 *
 */
public class NetworkError {
    private int httpCode;
    private String codeDescription;

    public NetworkError(int httpCode, @Nullable String code) {
        this.httpCode = httpCode;
        this.codeDescription = code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(httpCode);

        if (codeDescription != null) {
            builder.append(" : ").append(codeDescription);
        }

        return builder.toString();
    }
}
