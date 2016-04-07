package com.sotan.mircea.shower.presenter.contracts;

import android.support.annotation.NonNull;

import com.spotify.sdk.android.authentication.AuthenticationRequest;

/**
 * @author mircea on 4/4/16
 */
public interface NavigationActivityView extends MVPView {
    void openLoginActivity(@NonNull AuthenticationRequest request);

    void showLoginRequestError();
}
