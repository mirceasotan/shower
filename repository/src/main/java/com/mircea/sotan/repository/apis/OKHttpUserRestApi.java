package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.ApiRequestListener;

/**
 * @author mircea on 4/4/16
 */
public class OKHttpUserRestApi implements UserRestApi {
    @Override
    public void getCurrentUserAsync(@NonNull String uri, @Nullable ApiRequestListener<PublicUser> listener) {

    }
}
