package com.sotan.mircea.shower.newreleases.presenter;

import com.sotan.mircea.shower.newreleases.view.NewReleaseView;
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
