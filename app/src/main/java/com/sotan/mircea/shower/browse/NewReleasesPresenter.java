package com.sotan.mircea.shower.browse;

import com.sotan.mircea.shower.presenter.Presenter;

/**
 * @author mirceasotan
 */
public interface NewReleasesPresenter extends Presenter<NewReleaseView> {
    /**
     *
     */
    void getNewReleases();
}
