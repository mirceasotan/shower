package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.NewReleases;
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
public class UserRestApiImpl extends RestApi implements UserRestApi {
    private final UserService userService;

    public UserRestApiImpl(@NonNull Retrofit retrofit, Log log) {
        super(log);
        userService = retrofit.create(UserService.class);
    }

    /**
     * See {@link UserRestApi#getCurrentUserAsync(Listener)}
     */
    @Override
    public void getCurrentUserAsync(@Nullable final Listener<PublicUser> listener) {
        Call<PublicUser> call = userService.getCurrentUser();
        enqueueAsync(call, listener);
    }
}
