package com.sotan.mircea.shower.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.sotan.mircea.shower.ActivityConstants;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.presenter.NavigationActivityPresenter;
import com.sotan.mircea.shower.presenter.NavigationActivityPresenterImpl;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mircea
 */
public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        NavigationActivityPresenter.View {
    @Bind(R.id.home_drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Inject
    NavigationActivityPresenterImpl presenter;
    @Inject
    @Named("token")
    SharedPreferences tokenPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
        ButterKnife.bind(this);
        ShowerApp.getInjector().inject(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toolbarToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_content_desc_open, R.string.drawer_content_desc_closed);
        drawerLayout.setDrawerListener(toolbarToggle);
        toolbarToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        presenter.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check if result comes from the correct activity
        if (requestCode == ActivityConstants.SPOTIFY_LOGIN_REQ_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    Toast.makeText(this, "token", Toast.LENGTH_SHORT).show();
                    Log.d("token", response.getAccessToken());
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


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.my_account:
                startActivity(new Intent(this, MyAccountActivity.class));
                break;
            case R.id.sign_in:
                presenter.onHandleSignInMenuItemClick();
                break;
        }

        return false;
    }

    @Override
    public void openLoginActivity(@NonNull AuthenticationRequest request) {
        AuthenticationClient.openLoginActivity(this, ActivityConstants.SPOTIFY_LOGIN_REQ_CODE, request);
    }

    @Override
    public void showLoginRequestError() {

    }
}
