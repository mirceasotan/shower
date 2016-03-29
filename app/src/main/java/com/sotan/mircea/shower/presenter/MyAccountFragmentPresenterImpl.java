package com.sotan.mircea.shower.presenter;

import com.mircea.sotan.domain.DataListener;
import com.mircea.sotan.domain.GetUserUseCase;
import com.mircea.sotan.model.PublicUser;

import javax.inject.Inject;

/**
 * Created by mircea
 */
public class MyAccountFragmentPresenterImpl extends Presenter
        implements MyAccountFragmentPresenter.Callback {
    @Inject
    GetUserUseCase userUseCase;

    @Override
    public void getUser() {
        userUseCase.getUser(new DataListener<PublicUser>() {
            @Override
            public void onResponse(PublicUser data) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
