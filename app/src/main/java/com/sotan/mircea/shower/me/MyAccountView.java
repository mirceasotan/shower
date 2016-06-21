package com.sotan.mircea.shower.me;

import com.mircea.sotan.model.PublicUser;

/**
 * @author mircea on 4/4/16
 */
public interface MyAccountView {
    void showUser(PublicUser publicUser);

    void showUserError();
}
