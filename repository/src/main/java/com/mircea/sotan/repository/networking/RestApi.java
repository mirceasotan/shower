package com.mircea.sotan.repository.networking;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.net.HttpURLConnection;

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

    private final RequestLog requestLog;
    private final Scheduler subscribeOnScheduler = Schedulers.newThread();
    protected final TokenStorage tokenStorage;

    public RestApi(@NonNull RequestLog requestLog, @NonNull TokenStorage tokenStorage) {
        this.requestLog = requestLog;
        this.tokenStorage = tokenStorage;
    }

    protected Observable configureObservableExecutionThread(@NonNull Observable observable) {
        return observable.subscribeOn(subscribeOnScheduler);
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
                    handleErrorResponse(new NetworkError(response), listener);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                handleErrorResponse(new NetworkError(t), listener);
            }
        });
    }

    private <T> void handleErrorResponse(@NonNull NetworkError error, @Nullable Listener<T> listener) {
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


    private void handleUnauthorizedResponse() {
    }

    private void log(String message) {
        if (requestLog.isLoggingEnabled()) {
            requestLog.log(message);
        }
    }
}

