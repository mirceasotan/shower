package com.sotan.mircea.shower.me;

import com.mircea.sotan.domain.DataListener;
import com.mircea.sotan.domain.GetUserUseCase;
import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.NetworkError;
import com.sotan.mircea.shower.presenter.PresenterImpl;

import javax.inject.Inject;

/**
 * @author mircea
 */
public class MyAccountFragmentPresenterImpl extends PresenterImpl<MyAccountView>
        implements MyAccountFragmentPresenter {

    private GetUserUseCase userUseCase;

    @Inject
    public MyAccountFragmentPresenterImpl(GetUserUseCase getUserUseCase) {
        this.userUseCase = getUserUseCase;
    }

    @Override
    public void getUser() {
        userUseCase.getUser(new DataListener<PublicUser>() {
            @Override
            public void onResponse(PublicUser data) {
                if (getView() != null) {
                    getView().showUser(data);
                }
            }

            @Override
            public void onError(NetworkError error) {
                if (getView() != null) {
                    getView().showUserError();
                }
            }
        });
    }
}
