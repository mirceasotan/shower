package com.sotan.mircea.shower.browse;

import com.sotan.mircea.shower.presenter.Presenter;
import com.sotan.mircea.shower.albums.SimpleAlbumViewModel;

import java.util.List;

import rx.Observable;

/**
 * @author mirceasotan
 */
public interface NewReleasesPresenter extends Presenter<NewReleaseView> {
    /**
     *
     */
    void getNewReleases();

    Observable<List<SimpleAlbumViewModel>> getRxNewReleases();
}
