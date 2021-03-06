package com.mircea.sotan.domain.profiles;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.DataListener;
import com.mircea.sotan.repository.apis.UserRestApi;
import com.mircea.sotan.repository.networking.ApiListener;
import com.mircea.sotan.repository.networking.NetworkError;
import com.mircea.sotan.repository.networking.ResponseContainer;

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
    public void getUser(@Nullable final DataListener<PublicUser> dataListener) {
        userRestApi.getCurrentUserAsync(new ApiListener<PublicUser>() {
            @Override
            public void onResponse(ResponseContainer<PublicUser> apiResponse) {
                if (dataListener == null) {
                    return;
                }

                dataListener.onResponse(apiResponse.getData());
            }

            @Override
            public void onError(NetworkError networkError) {
                if (dataListener == null) {
                    return;
                }

                dataListener.onError(networkError);
            }
        });
    }
}
