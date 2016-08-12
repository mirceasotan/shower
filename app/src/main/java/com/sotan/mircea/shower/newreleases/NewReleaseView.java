package com.sotan.mircea.shower.newreleases;

import android.support.annotation.NonNull;

import com.sotan.mircea.shower.viewModel.SimpleAlbumViewModel;

import java.util.List;

/**
 * @author mirceasotan
 */
public interface NewReleaseView {
    /**
     * @param newReleases
     */
    void showNewReleases(@NonNull List<SimpleAlbumViewModel> simpleAlbumViewModels);

    /**
     *
     */
    void showNewReleasesNoConnectionError();

    /**
     *
     */
    void showNewReleasesApiError();
}
