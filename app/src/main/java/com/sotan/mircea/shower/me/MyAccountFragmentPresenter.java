package com.sotan.mircea.shower.me;

import com.sotan.mircea.shower.presenter.Presenter;

/**
 * Created by mircea
 */
public interface MyAccountFragmentPresenter extends Presenter<MyAccountView> {
    void getUser();
}
