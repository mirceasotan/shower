package com.sotan.mircea.shower.browse;

import android.support.annotation.NonNull;

import com.sotan.mircea.shower.albums.SimpleAlbumViewModel;
import com.sotan.mircea.shower.presenter.Presenter;

import java.util.List;

import rx.Observable;

/**
 * @author mirceasotan
 */

public interface NewReleasesContract {

    interface NewReleaseView {
        /**
         *
         * @param viewModels
         */
        void showNewReleases(@NonNull List<SimpleAlbumViewModel> viewModels);

        /**
         *
         */
        void showNewReleasesNoConnectionError();

        /**
         *
         */
        void showNewReleasesApiError();
    }


    interface NewReleasesPresenter extends Presenter<NewReleaseView> {
        /**
         *
         */
        void getNewReleases();

        /**
         *
         * @return
         */
        Observable<List<SimpleAlbumViewModel>> getRxNewReleases();
    }
}
