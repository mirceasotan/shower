package com.sotan.mircea.shower.me;

import com.mircea.sotan.domain.DataListener;
import com.mircea.sotan.domain.GetUserUseCase;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.networking.NetworkError;
import com.sotan.mircea.shower.presenter.PresenterImpl;

import javax.inject.Inject;

/**
 * Created by mircea
 */
public class MyAccountFragmentPresenterImpl extends PresenterImpl
        implements MyAccountFragmentPresenter {

    private GetUserUseCase userUseCase;

    @Inject
    public MyAccountFragmentPresenterImpl(GetUserUseCase getUserUseCase) {
        this.userUseCase = getUserUseCase;
    }

    @Override
    public void getUser() {
        userUseCase.getUser(new DataListener<NewReleases>() {
            @Override
            public void onResponse(NewReleases data) {
                if (getView() == null) {
                    return;
                }

                ((MyAccountFragmentView) getView()).showUser(data);
            }

            @Override
            public void onError(NetworkError error) {
                if (getView() == null) {
                    return;
                }

                ((MyAccountFragmentView) getView()).showUserError();
            }
        });
    }
}
