package com.mircea.sotan.domain;

import android.support.annotation.Nullable;

import com.mircea.sotan.model.NewReleases;

/**
 * @author mirceasotan
 */
public interface GetNewReleasesUseCase {
    /**
     * Use case for fetching detailed profile information about the current
     * user (including the current user’s username)
     *
     * @param dataListener
     */
    void getNewReleases(@Nullable DataListener<NewReleases> dataListener);
}
