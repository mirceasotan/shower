package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.ApiRequestListener;
import com.mircea.sotan.repository.networking.ApiResponse;
import com.mircea.sotan.repository.networking.NetworkError;
import com.mircea.sotan.repository.networking.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit implementation of the {@link UserRestApi}
 *
 * @author mircea on 4/4/16
 */
public class RetrofitUserRestApi implements UserRestApi {
    private final Retrofit retrofit;
    private String token = "";

    public RetrofitUserRestApi(@NonNull String token, @NonNull String baseUrl) {
        this.retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
        this.token = token;
    }


    /**
     * See {@link UserRestApi#getCurrentUserAsync(String, ApiRequestListener)}
     */
    @Override
    public void getCurrentUserAsync(@NonNull String uri,
                                    @Nullable final ApiRequestListener<PublicUser> listener) {
        UserService userService = retrofit.create(UserService.class);
        Call<PublicUser> call = userService.getCurrentUser(uri,
                AUTHORIZATION_HEADER_TEMPLATE.replace("{token}", token));

        if (listener == null) {
            return;
        }

        call.enqueue(new Callback<PublicUser>() {
            @Override
            public void onResponse(Call<PublicUser> call, Response<PublicUser> response) {
                if (response != null && response.isSuccessful()) {
                    listener.onResponse(new ApiResponse<>(response.body(), response.code()));
                } else {
                    listener.onError(createNetworkErrorFromResponse(response));
                }
            }

            @Override
            public void onFailure(Call<PublicUser> call, Throwable t) {
                listener.onError(new NetworkError(RestApi.DEFAULT_HTTP_CONNECTION_ERROR_CODE,
                        RestApi.NO_INTERNET_CONNECTION_MESSAGE));
            }
        });
    }

    /**
     * Converts Retrofit network response into a {@link NetworkError} that can be passed and easily
     * interpreted by the upper layers via {@link ApiRequestListener}
     *
     * @param response Network response to be converted into a {@link NetworkError}
     * @return a {@link NetworkError} object that contains a status code and a status message
     */
    @NonNull
    private NetworkError createNetworkErrorFromResponse(@Nullable Response response) {
        if (response != null && response.raw() != null) {
            return new NetworkError(response.raw().code(), response.raw().message());
        } else {
            return new NetworkError(RestApi.DEFAULT_HTTP_CONNECTION_ERROR_CODE,
                    RestApi.UNKNOWN_STATUS);
        }
    }
}
