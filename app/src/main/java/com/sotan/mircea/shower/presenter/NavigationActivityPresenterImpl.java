package com.sotan.mircea.shower.presenter;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sotan.mircea.shower.ConfigConstants;
import com.sotan.mircea.shower.ConfigurationManager;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

/**
 * Created by mircea
 */
public class NavigationActivityPresenterImpl extends Presenter
        implements NavigationActivityPresenter.Callback {

    @NonNull
    ConfigurationManager configurationManager;

    public NavigationActivityPresenterImpl(@NonNull ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    @Override
    public void onNavigationViewMenuItemClick(@IdRes int itemId) {
        AuthenticationRequest request = buildAuthenticationRequest();
        if (getView() == null) {
            return;
        }

        if (request == null) {
            getView().showLoginRequestError();
        } else {
            getView().openLoginActivity(request);
        }
    }

    @Nullable
    private AuthenticationRequest buildAuthenticationRequest() {
        String clientId = configurationManager.getValueForKey(ConfigConstants.CLIENT_ID);
        String redirectUri = configurationManager.getValueForKey(ConfigConstants.REDIRECT_URI);

        if (clientId == null || redirectUri == null) {
            return null;
        }

        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(clientId, AuthenticationResponse.Type.TOKEN, redirectUri);
        builder.setScopes(new String[]{"streaming"});

        return builder.build();
    }
}
