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

    private final RequestLog log;
    private final Scheduler scheduler = Schedulers.newThread();
    protected final TokenStorage storage;

    public interface HttpHeader {
        String AUTHORIZATION = "Authorization";
    }

    public RestApi(@NonNull RequestLog log, @NonNull TokenStorage storage) {
        this.log = log;
        this.storage = storage;
    }

    protected Observable configureObservableExecutionThread(@NonNull Observable observable) {
        return observable.subscribeOn(scheduler);
    }

    /**
     * @param call        Retrofit Http call to be performed
     * @param apiListener callback for notifying observers that call finished with a resolution
     */
    protected <T> void enqueueAsync(@NonNull Call<T> call, @Nullable final ApiListener<T> apiListener) {
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
                    handleSuccessResponse(response, apiListener);
                } else {
                    handleErrorResponse(new NetworkError(response), apiListener);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                handleErrorResponse(new NetworkError(t), apiListener);
            }
        });
    }

    private <T> void handleErrorResponse(@NonNull NetworkError networkError, @Nullable ApiListener<T> apiListener) {
        log(networkError.toString());

        if (networkError.getHttpCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            //TODO add 401 handling
            handleUnauthorizedResponse();
        } else if (apiListener != null) {
            apiListener.onError(networkError);
        }
    }

    private <T> void handleSuccessResponse(Response<T> response, @Nullable ApiListener<T> apiListener) {
        ResponseContainer<T> responseContainer = new ResponseContainer<>(response.body(),
                response.code());

        log("Response : " + response.code());

        if (apiListener != null) {
            apiListener.onResponse(responseContainer);
        }
    }


    private void handleUnauthorizedResponse() {
    }

    private void log(String message) {
        if (log.isLoggingEnabled()) {
            log.log(message);
        }
    }
}

