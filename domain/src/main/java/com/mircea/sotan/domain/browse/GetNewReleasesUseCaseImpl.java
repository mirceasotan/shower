package com.mircea.sotan.domain.browse;

import android.support.annotation.Nullable;

import com.mircea.sotan.domain.DataListener;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.apis.BrowseRestApi;
import com.mircea.sotan.repository.networking.Listener;
import com.mircea.sotan.repository.networking.NetworkError;
import com.mircea.sotan.repository.networking.ResponseContainer;

import javax.inject.Inject;

import rx.Observable;

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
    public void getNewReleases(@Nullable final DataListener<NewReleases> dataListener, int offset,
                               int limit) {
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
        }, offset, limit);
    }

    @Override public Observable<NewReleases> getRxNewReleases(int offset, int limit) {
        return browseRestApi.getRxNewReleasesAsync(offset, limit);
    }
}
