package com.mircea.sotan.domain;

import android.support.annotation.Nullable;

import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.apis.BrowseRestApi;
import com.mircea.sotan.repository.networking.Listener;
import com.mircea.sotan.repository.networking.NetworkError;
import com.mircea.sotan.repository.networking.ResponseContainer;

import javax.inject.Inject;

/**
 * @author mirceasotan
 */
public class GetNewReleasesUseCaseImpl implements GetNewReleasesUseCase {

    private final BrowseRestApi browseRestApi;

    @Inject
    public GetNewReleasesUseCaseImpl(BrowseRestApi browseRestApi) {
        this.browseRestApi = browseRestApi;
    }

    @Override
    public void getNewReleases(@Nullable final DataListener<NewReleases> dataListener) {
        browseRestApi.getNewReleasesAsync(new Listener<NewReleases>() {
            @Override
            public void onResponse(ResponseContainer<NewReleases> apiResponse) {
                if (dataListener != null) {
                    dataListener.onResponse(apiResponse.getData());
                }
            }

            @Override
            public void onError(NetworkError error) {
                if (dataListener != null) {
                    dataListener.onError(error);
                }
            }
        });
    }
}
