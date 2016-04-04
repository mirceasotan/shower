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
 * @author mircea on 4/4/16
 */
public class RetrofitUserRestApi implements UserRestApi {

    private static final String AUTHORIZATION_HEADER_TEMPLATE = "Authorization {Bearer}";
    private final Retrofit retrofit;
    private String token = "";

    public RetrofitUserRestApi(@NonNull String token) {
        this.retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.token = token;
    }


    @Override
    public void getCurrentUser(@NonNull String uri, @Nullable final ApiRequestListener<PublicUser> listener) {
        UserService userService = retrofit.create(UserService.class);
        Call<PublicUser> call = userService.getCurrentUser(uri, AUTHORIZATION_HEADER_TEMPLATE.replace("{Bearer}", token));
        call.enqueue(new Callback<PublicUser>() {
            @Override
            public void onResponse(Call<PublicUser> call, Response<PublicUser> response) {
                if (listener == null) {
                    return;
                }

                listener.onResponse(new ApiResponse<>(response.body(), response.code()));
            }

            @Override
            public void onFailure(Call<PublicUser> call, Throwable t) {
                if (listener == null) {
                    return;
                }

                listener.onError(new NetworkError());
            }
        });
    }
}
