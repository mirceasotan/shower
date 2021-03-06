package com.sotan.mircea.shower.navigation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sotan.mircea.shower.ConfigConstants;
import com.sotan.mircea.shower.ConfigurationManager;
import com.sotan.mircea.shower.presenter.PresenterImpl;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

/**
 * Created by mircea
 */
public class NavigationActivityPresenterImpl extends PresenterImpl
        implements NavigationActivityPresenter {

    @NonNull
    ConfigurationManager configurationManager;

    public NavigationActivityPresenterImpl(@NonNull ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    @Nullable
    public AuthenticationRequest buildAuthenticationRequest() {
        String clientId = configurationManager.getValueForKey(ConfigConstants.CLIENT_ID);
        String redirectUri = configurationManager.getValueForKey(ConfigConstants.REDIRECT_URI);

        if (clientId == null || redirectUri == null) {
            return null;
        }

        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(clientId, AuthenticationResponse.Type.TOKEN, redirectUri);
        builder.setScopes(new String[]{"streaming", "user-read-private", "user-read-birthdate", "user-read-email"});

        return builder.build();
    }

    @Override
    public void onHandleSignInMenuItemClick() {

    }

    @Override
    public void onHandleMyAccountMenuItemClick() {
        if (getView() == null) {
            return;
        }
    }
}
