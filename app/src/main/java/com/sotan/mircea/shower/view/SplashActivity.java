package com.sotan.mircea.shower.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.sotan.mircea.shower.ActivityConstants;
import com.sotan.mircea.shower.ConfigConstants;
import com.sotan.mircea.shower.ConfigurationManager;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;

/**
 * Created by mirceasotan
 */
public class SplashActivity extends Activity {
    @Inject
    @Named("token")
    SharedPreferences tokenPreferences;
    @Inject
    ConfigurationManager configurationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShowerApp.getInjector().inject(this);

        setContentView(R.layout.splash_activity);
        TextView splashTextView = ButterKnife.findById(this, R.id.splash_text);
        Typeface type = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        splashTextView.setTypeface(type);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                AuthenticationRequest request = buildAuthenticationRequest();

                if (request == null) {
                    //((NavigationActivityView) getView()).showLoginRequestError();
                } else {
                    AuthenticationClient.openLoginActivity(SplashActivity.this,
                            ActivityConstants.SPOTIFY_LOGIN_REQ_CODE, request);
                }

            }
        }, 2000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check if result comes from the correct activity
        if (requestCode == ActivityConstants.SPOTIFY_LOGIN_REQ_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

            switch (response.getType()) {
                // ResponseContainer was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    tokenPreferences.edit().putString("token", response.getAccessToken()).apply();
                    startActivity(new Intent(this, NavigationActivity.class));
                    finish();
                    break;

                // Auth flow returned an error
                case ERROR:
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
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

}
