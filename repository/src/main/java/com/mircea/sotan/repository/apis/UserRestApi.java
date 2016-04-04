package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.ApiRequestListener;

/**
 * Created by mircea
 */
public interface UserRestApi {
    void getCurrentUser(@NonNull String uri, @Nullable ApiRequestListener<PublicUser> listener);
}
