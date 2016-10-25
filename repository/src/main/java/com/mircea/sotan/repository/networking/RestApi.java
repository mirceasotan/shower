package com.mircea.sotan.repository.networking;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * @author mirceasotan
 */
public class RestApi {
    private static final int DEFAULT_HTTP_CONNECTION_ERROR_CODE = -1;
    public static final String NO_INTERNET_CONNECTION_MESSAGE = "No Internet Connection";
    private final Set<Call<?>> waitingCalls = new HashSet<>();
    private final RequestLog requestLog;
    private final Scheduler subscribeOnScheduler = Schedulers.newThread();
    protected final TokenStorage tokenStorage;

    public RestApi(@NonNull RequestLog requestLog, @NonNull TokenStorage tokenStorage) {
        this.requestLog = requestLog;
        this.tokenStorage = tokenStorage;
    }

    protected void enqueueRxAsync(@NonNull Observable observable) {
        observable.subscribeOn(subscribeOnScheduler);
    }

    /**
     * @param call     Retrofit Http call to be performed
     * @param listener callback for notifying observers that call finished with a resolution
     */
    protected <T> void enqueueAsync(@NonNull Call<T> call, @Nullable final Listener<T> listener) {
        StringBuilder builder = new StringBuilder("Request URL:")
                .append(" ")
                .append(call.request().method())
                .append(" ")
                .append(call.request().url().toString());

        log(builder.toString());

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response != null && response.isSuccessful()) {
                    handleSuccessResponse(response, listener);
                } else {
                    handleErrorResponse(response, listener, null);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                handleErrorResponse(null, listener, null);
            }
        });
    }

    private <T> void handleErrorResponse(Response<T> response, @Nullable Listener<T> listener,
                                         @Nullable Throwable t) {
        NetworkError error = createNetworkErrorFromResponse(response, t);
        log(error.toString());

        if (error.getHttpCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            //TODO add 401 handling
            handleUnauthorizedResponse();
        } else if (listener != null) {
            listener.onError(error);
        }
    }

    private <T> void handleSuccessResponse(Response<T> response, @Nullable Listener<T> listener) {
        ResponseContainer<T> responseContainer = new ResponseContainer<>(response.body(),
                response.code());

        log("Response : " + response.code());

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
    private NetworkError createNetworkErrorFromResponse(@Nullable retrofit2.Response response,
                                                        @Nullable Throwable t) {

        if (response == null && t == null) {
            return new NetworkError(DEFAULT_HTTP_CONNECTION_ERROR_CODE,
                    NO_INTERNET_CONNECTION_MESSAGE);
        } else if (response == null) {
            return new NetworkError(DEFAULT_HTTP_CONNECTION_ERROR_CODE, t.getMessage());
        } else if (response.raw() != null) {
            return new NetworkError(response.raw().code(), response.raw().message());
        } else {
            return new NetworkError(DEFAULT_HTTP_CONNECTION_ERROR_CODE, "Unknown Status");
        }
    }

    private void handleUnauthorizedResponse() {
    }

    private void log(String message) {
        if (requestLog.isLoggingEnabled()) {
            requestLog.log(message);
        }
    }
}

