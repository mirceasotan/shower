package com.mircea.sotan.repository.networking;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author mirceasotan
 */
public class RestApi {
    public static final int DEFAULT_HTTP_CONNECTION_ERROR_CODE = -1;
    public static final String NO_INTERNET_CONNECTION_MESSAGE = "No Internet Connection";
    public final Set<Call<?>> waitingCalls = new HashSet<>();
    private final Log log;

    public RestApi(@NonNull Log log) {
        this.log = log;
    }

    /**
     * @param call     Retrofit Http call to be performed
     * @param listener callback for notifying observers that call finished with a resolution
     */
    public <T> void enqueueAsync(@NonNull Call<T> call, @Nullable final Listener<T> listener) {
        if (log.isLoggingEnabled()) {
            log.log(call.request().url().toString());
        }

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response != null && response.isSuccessful()) {
                    handleSuccessResponse(response, listener);
                } else {
                    handleErrorResponse(response, listener);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                handleErrorResponse(null, listener);
            }
        });
    }

    private <T> void handleErrorResponse(Response<T> response, @Nullable Listener<T> listener) {
        NetworkError error;
        if (response == null) {
            error = new NetworkError(DEFAULT_HTTP_CONNECTION_ERROR_CODE,
                    NO_INTERNET_CONNECTION_MESSAGE);
        } else {
            error = createNetworkErrorFromResponse(response);
        }

        if (error.getHttpCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {

        } else {
            if (listener != null) {
                listener.onError(error);
            }
        }

        if (log.isLoggingEnabled()) {
            log.log(error.toString());
        }
    }

    private <T> void handleSuccessResponse(Response<T> response, @Nullable Listener<T> listener) {
        ResponseContainer<T> responseContainer = new ResponseContainer<>(response.body(),
                response.code());

        if (log.isLoggingEnabled()) {
            log.log("Response : " + response.code());
        }

        if (listener != null) {
            listener.onResponse(responseContainer);
        }
    }

    /**
     * Converts Retrofit network response into a {@link NetworkError} that can be passed and easily
     * interpreted by the upper layers via {@link Listener}
     *
     * @param response Network response to be converted into a {@link NetworkError}
     * @return a {@link NetworkError} object that contains a status code and a status message
     */
    @NonNull
    private NetworkError createNetworkErrorFromResponse(@Nullable retrofit2.Response response) {
        if (response != null && response.raw() != null) {
            return new NetworkError(response.raw().code(), response.raw().message());
        } else {
            return new NetworkError(DEFAULT_HTTP_CONNECTION_ERROR_CODE, "Unknown Status");
        }
    }

    private void handleUnauthorizedResponse(@NonNull Call call) {
        waitingCalls.add(call);
    }
}

