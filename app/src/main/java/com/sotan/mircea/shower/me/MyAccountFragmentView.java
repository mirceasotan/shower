package com.sotan.mircea.shower.me;

import com.mircea.sotan.model.NewReleases;
import com.sotan.mircea.shower.presenter.contracts.MVPView;

/**
 * @author mircea on 4/4/16
 */
public interface MyAccountFragmentView extends MVPView {
    void showUser(NewReleases publicUser);

    void showUserError();
}