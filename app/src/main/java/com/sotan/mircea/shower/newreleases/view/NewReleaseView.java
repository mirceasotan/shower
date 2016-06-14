package com.sotan.mircea.shower.newreleases.view;

import android.support.annotation.NonNull;

import com.mircea.sotan.model.NewReleases;
import com.sotan.mircea.shower.presenter.contracts.MVPView;

/**
 * @author mirceasotan
 */
public interface NewReleaseView extends MVPView {
    /**
     * @param newReleases
     */
    void showNewReleases(@NonNull NewReleases newReleases);

    /**
     *
     */
    void showNewReleasesNoConnectionError();

    /**
     */
    void showNewReleasesApiError();
}
