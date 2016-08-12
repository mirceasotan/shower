package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.Listener;
import com.mircea.sotan.repository.networking.RequestLog;
import com.mircea.sotan.repository.networking.RestApi;
import com.mircea.sotan.repository.networking.TokenStorage;
import com.mircea.sotan.repository.services.UserService;

import retrofit2.Call;

/**
 * Retrofit implementation of the {@link UserRestApi}
 *
 * @author mircea on 4/4/16
 */
public class UserRestApiImpl extends RestApi implements UserRestApi {
    private final UserService userService;

    public UserRestApiImpl(@NonNull UserService service, RequestLog requestLog, TokenStorage tokenStorage) {
        super(requestLog, tokenStorage);
        userService = service;
    }

    /**
     * See {@link UserRestApi#getCurrentUserAsync(Listener)}
     */
    @Override
    public void getCurrentUserAsync(@Nullable final Listener<PublicUser> listener) {
        Call<PublicUser> call = userService.getCurrentUser(tokenStorage.getAuthToken());
        enqueueAsync(call, listener);
    }
}
