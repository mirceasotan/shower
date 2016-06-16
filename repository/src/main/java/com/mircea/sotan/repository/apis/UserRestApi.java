package com.mircea.sotan.repository.apis;

import android.support.annotation.Nullable;

import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.networking.Listener;

/**
 * Created by mircea
 */
public interface UserRestApi {
    /**
     * Performs a HTTP call to retrieve current user's profile information. Call is performed
     * asynchronously.
     *
     * @param listener Contract used to pass API call resolution to upper
     *                 layers in a format that they can understand
     */
    void getCurrentUserAsync(@Nullable Listener<NewReleases> listener);
}
