package com.sotan.mircea.shower.navigation;

import com.sotan.mircea.shower.presenter.Presenter;

/**
 * Created by mircea
 */
public interface NavigationActivityPresenter extends Presenter {
    void onHandleSignInMenuItemClick();

    void onHandleMyAccountMenuItemClick();
}
