package com.mircea.sotan.repository;

import android.support.annotation.Nullable;

import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.apis.BrowseRestApi;
import com.mircea.sotan.repository.cache.BrowseCache;
import com.mircea.sotan.repository.networking.ApiListener;
import com.mircea.sotan.repository.networking.NetworkError;
import com.mircea.sotan.repository.networking.ResponseContainer;

import rx.Observable;

/**
 * @author mirceasotan
 */

public class BrowseRepository implements BrowseDataSource {

    private final BrowseRestApi api;
    private final BrowseCache cache;
    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    private boolean cacheIsDirty = false;

    public BrowseRepository(BrowseRestApi api, BrowseCache cache) {
        this.api = api;
        this.cache = cache;
    }

    @Override
    public void getNewReleases(@Nullable DataListener<NewReleases> listener, int offset, int limit) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset cannot be a negative number");
        }

        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be a numebr greater than 0");
        }

        if (cache.getNewReleases() != null && !cacheIsDirty && listener != null) {
            listener.onResponse(cache.getNewReleases());
            return;
        }

        api.getNewReleasesAsync(new ApiListener<NewReleases>() {
            @Override
            public void onResponse(ResponseContainer<NewReleases> apiResponse) {
                if (listener != null) {
                    listener.onResponse(apiResponse.getData());
                }
            }

            @Override
            public void onError(NetworkError networkError) {
                if (listener != null) {
                    listener.onError(networkError);
                }
            }
        }, offset, limit);
    }

    @Override
    public Observable<NewReleases> getRxNewReleases(int offset, int limit) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset cannot be a negative number");
        }

        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be a numebr greater than 0");
        }

        if (cache.getNewReleases() != null && !cacheIsDirty) {
            return Observable.just(cache.getNewReleases());
        }


        return api.getRxNewReleasesAsync(offset, limit);
    }

    @Override
    public void refreshNewReleases() {
        cacheIsDirty = true;
    }

}
