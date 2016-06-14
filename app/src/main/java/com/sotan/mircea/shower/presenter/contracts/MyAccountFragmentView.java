package com.sotan.mircea.shower.presenter.contracts;

import com.mircea.sotan.model.PublicUser;

/**
 * @author mircea on 4/4/16
 */
public interface MyAccountFragmentView extends MVPView{
    void showUser(PublicUser publicUser);

    void showUserError();
}
