package com.mircea.sotan.domain.browse;

import android.support.annotation.Nullable;

import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.BrowseDataSource;
import com.mircea.sotan.repository.DataListener;
import com.mircea.sotan.repository.Error;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author mirceasotan
 */
public class GetNewReleasesUseCaseImpl implements GetNewReleasesUseCase {

    private final BrowseDataSource repository;

    @Inject
    public GetNewReleasesUseCaseImpl(BrowseDataSource repository) {
        this.repository = repository;
    }

    @Override
    public void getNewReleases(@Nullable final DataListener<NewReleases> dataListener, int offset,
                               int limit) {

        repository.getNewReleases(new com.mircea.sotan.repository.DataListener<NewReleases>() {
            @Override
            public void onResponse(NewReleases data) {
                if (dataListener != null) {
                    dataListener.onResponse(data);
                }
            }

            @Override
            public void onError(Error error) {
                if (dataListener != null) {
                    dataListener.onError(error);
                }
            }


        }, offset, limit);

    }

    @Override
    public Observable<NewReleases> getRxNewReleases(int offset, int limit) {
        return repository.getRxNewReleases(offset, limit);
    }
}
