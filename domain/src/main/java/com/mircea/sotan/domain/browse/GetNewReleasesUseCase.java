package com.mircea.sotan.domain.browse;

import android.support.annotation.Nullable;

import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.DataListener;

import rx.Observable;

/**
 * Use case for getting a list of new album releases featured
 * in Spotify (shown, for example, on a Spotify player’s “Browse” tab).
 *
 * @author mirceasotan
 */
public interface GetNewReleasesUseCase {

    /**
     * @param dataListener Callback for delivering the result to caller
     * @param offset       number of items to be skipped
     * @param limit        number of items to be taken
     */
    void getNewReleases(@Nullable DataListener<NewReleases> dataListener, int offset, int limit);

    /**
     * @param offset number of items to be skipped
     * @param limit  number of items to be taken
     * @return New Releases Observable
     */
    Observable<NewReleases> getRxNewReleases(int offset, int limit);
}
