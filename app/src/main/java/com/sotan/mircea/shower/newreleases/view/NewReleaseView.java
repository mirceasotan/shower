package com.sotan.mircea.shower.newreleases.view;

import android.support.annotation.NonNull;

import com.mircea.sotan.model.NewReleases;

/**
 * @author mirceasotan
 */
public interface NewReleaseView {
    /**
     * @param newReleases
     */
    void showNewReleases(@NonNull NewReleases newReleases);

    /**
     *
     */
    void showNewReleasesNoConnectionError();

    /**
     *
     */
    void showNewReleasesApiError();
}
