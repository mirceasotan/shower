package com.mircea.sotan.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.apis.UserRestApi;
import com.mircea.sotan.repository.networking.ResponseContainer;
import com.mircea.sotan.repository.networking.Listener;
import com.mircea.sotan.repository.networking.NetworkError;

import javax.inject.Inject;

/**
 * @author mirceasotan
 */
public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserRestApi userRestApi;

    @Inject
    public GetUserUseCaseImpl(@NonNull UserRestApi userRestApi) {
        this.userRestApi = userRestApi;
    }

    @Override
    public void getUser(@Nullable final DataListener<NewReleases> dataListener) {
        userRestApi.getCurrentUserAsync(new Listener<NewReleases>() {
            @Override
            public void onResponse(ResponseContainer<NewReleases> apiResponse) {
                if (dataListener == null) {
                    return;
                }

                dataListener.onResponse(apiResponse.getData());
            }

            @Override
            public void onError(NetworkError error) {
                if (dataListener == null) {
                    return;
                }

                dataListener.onError(error);
            }
        });
    }
}
