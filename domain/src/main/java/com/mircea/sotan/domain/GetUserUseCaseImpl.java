package com.mircea.sotan.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.apis.UserRestApi;
import com.mircea.sotan.repository.networking.ApiRequestListener;
import com.mircea.sotan.repository.networking.ApiResponse;
import com.mircea.sotan.repository.networking.NetworkError;

import javax.inject.Inject;

/**
 * Created by mircea
 */
public class GetUserUseCaseImpl implements GetUserUseCase {

    private UserRestApi userRestApi;
    private String uri;

    @Inject
    public GetUserUseCaseImpl(@NonNull String uri, @NonNull UserRestApi userRestApi) {
        this.uri = uri;
        this.userRestApi = userRestApi;
    }

    @Override
    public void getUser(@Nullable final DataListener<PublicUser> dataListener) {
        userRestApi.getCurrentUserAsync(uri, new ApiRequestListener<PublicUser>() {
            @Override
            public void onResponse(ApiResponse<PublicUser> response) {
                if (dataListener == null) {
                    return;
                }

                dataListener.onResponse(response.getData());
            }

            @Override
            public void onError(NetworkError error) {
                if (dataListener == null) {
                    return;
                }

                dataListener.onError(error.getHttpCode() + " " + error.getCode());
            }
        });
    }
}
