package com.mircea.sotan.repository;

import android.support.annotation.Nullable;

import com.mircea.sotan.model.NewReleases;

import rx.Observable;

/**
 * @author mirceasotan
 */

public interface BrowseDataSource {
    /**
     * @param apiListener Callback used for notifying caller about request resolution
     */
    void getNewReleases(@Nullable DataListener<NewReleases> apiListener, int offset, int limit);

    /**
     *
     */
    Observable<NewReleases> getRxNewReleases(int offset, int limit);

    /**
     *
     */
    void refreshNewReleases();
}
