package com.sotan.mircea.shower.presenter;

import android.support.annotation.NonNull;

import com.spotify.sdk.android.authentication.AuthenticationRequest;

/**
 * Created by mircea
 */
public interface NavigationActivityPresenter {

    interface Callback {
        void onHandleSignInMenuItemClick();
        void onHandleMyAccountMenuItemClick();
    }

    interface View {
        void openLoginActivity(@NonNull AuthenticationRequest request);

        void showLoginRequestError();
    }
}
