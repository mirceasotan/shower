package com.mircea.sotan.repository.networking;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author mirceasotan
 */
public class RestApi<T> {
    public static final int DEFAULT_HTTP_CONNECTION_ERROR_CODE = -1;
    public static final String NO_INTERNET_CONNECTION_MESSAGE = "No Internet Connection";
    private final Log log;

    public RestApi(@NonNull Log log) {
        this.log = log;
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

    /**
     * @param call
     * @param listener
     */
    public void enqueueAsync(@NonNull Call<T> call, @Nullable final Listener<T> listener) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {


                if (response != null && response.isSuccessful()) {
                    if (listener == null) {
                        return;
                    }

                    ResponseContainer responseContainer = new ResponseContainer<>(response.body(),
                            response.code());

                    if (log.isLoggingEnabled()) {
                        log.log("Response : " + response.code());
                    }

                    listener.onResponse(new ResponseContainer<>(response.body(), response.code()));
                } else {
                    NetworkError error = createNetworkErrorFromResponse(response);

                    if (log.isLoggingEnabled()) {
                        log.log(error.toString());
                    }

                    if (listener == null) {
                        return;
                    }

                    listener.onError(error);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                NetworkError error = new NetworkError(DEFAULT_HTTP_CONNECTION_ERROR_CODE,
                        NO_INTERNET_CONNECTION_MESSAGE);

                if (log.isLoggingEnabled()) {
                    log.log(error.toString());
                }

                if (listener == null) {
                    return;
                }

                listener.onError(error);
            }
        });
    }


}

