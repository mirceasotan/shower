package com.sotan.mircea.shower.presenter;

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

    @Nullable
    public AuthenticationRequest buildAuthenticationRequest() {
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

    @Override
    public void onHandleSignInMenuItemClick() {
        if (getView() == null) {
            return;
        }

        AuthenticationRequest request = buildAuthenticationRequest();
      //  if (request == null) {
            getView().showLoginRequestError();
      //  } else {
            getView().openLoginActivity(request);
       // }
    }

    @Override
    public void onHandleMyAccountMenuItemClick() {
        if (getView() == null) {
            return;
        }
    }
}
