package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.ApiRequestListener;

/**
 * Created by mircea
 */
public interface UserRestApi extends RestApi {
    /**
     * Performs a HTTP call to retrieve current user's profile information. Call is performed
     * asynchronously.
     *
     * @param uri      String representing the endpoint for retrieving user data
     * @param listener Contract used to pass API call resolution to upper
     *                 layers in a format that they can understand
     */
    void getCurrentUserAsync(@NonNull String uri, @Nullable ApiRequestListener<PublicUser> listener);
}
