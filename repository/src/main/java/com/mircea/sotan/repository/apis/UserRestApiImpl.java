package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.*;
import com.mircea.sotan.repository.services.UserService;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Retrofit implementation of the {@link UserRestApi}
 *
 * @author mircea on 4/4/16
 */
public class UserRestApiImpl implements UserRestApi {

    private final UserService userService;
    private final Log log;

    public UserRestApiImpl(@NonNull Retrofit retrofit, Log log) {
        userService = retrofit.create(UserService.class);
        this.log = log;
    }

    /**
     * See {@link UserRestApi#getCurrentUserAsync(Listener)}
     */
    @Override
    public void getCurrentUserAsync(@Nullable final Listener<PublicUser> listener) {
        Call<PublicUser> call = userService.getCurrentUser();
        com.mircea.sotan.repository.networking.RestApi<PublicUser> restApi = new com.mircea.sotan.repository.networking.RestApi<>(log);
        restApi.enqueueAsync(call, listener);
    }
}
