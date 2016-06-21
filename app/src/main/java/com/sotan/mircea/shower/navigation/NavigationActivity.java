package com.sotan.mircea.shower.navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.mircea.sotan.model.SimpleAlbum;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.albumdetail.AlbumDetailActivity;
import com.sotan.mircea.shower.albumdetail.AlbumDetailFragment;
import com.sotan.mircea.shower.logger.GAEvent;
import com.sotan.mircea.shower.logger.GTMLogger;
import com.sotan.mircea.shower.logger.Logger;
import com.sotan.mircea.shower.me.MyAccountActivity;
import com.sotan.mircea.shower.albumdetail.AlbumClickCallback;
import com.sotan.mircea.shower.newreleases.view.NewReleasesFragment;
import com.sotan.mircea.shower.view.BaseActivity;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mircea
 */
public class NavigationActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        NavigationActivityView, AlbumClickCallback {
    @Bind(R.id.home_drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Inject
    NavigationActivityPresenter presenter;
    @Inject
    @Named("token")
    SharedPreferences tokenPreferences;
    @Inject
    @Named("GTMLogger")
    Logger logger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
        ButterKnife.bind(this);
        ShowerApp.getInjector().inject(this);
        initActionBar();

        if (savedInstanceState == null) {
            replaceFragment(NewReleasesFragment.newInstance(),
                    R.id.navigation_activity_fragment_container, false);
        }

        ((GTMLogger) logger).setId("KLP-8989");
        logger.init();
        ((GTMLogger) logger).setId("GTM-KLQ6C4");
        logger.init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        logger.log(new GAEvent("Home Screen"));
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
            case R.id.me:
                logger.log(new GAEvent.GAEventBuilder().withCategory("Navigation")
                        .withAction("Navigation menu item click")
                        .withLabel("My Account")
                        .withScreenName("Home").build());
                startActivity(new Intent(this, MyAccountActivity.class));
                break;
            case R.id.sign_out:
                // presenter.onHandleSignInMenuItemClick();
                break;
        }

        return false;
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toolbarToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_content_desc_open, R.string.drawer_content_desc_closed);
        drawerLayout.setDrawerListener(toolbarToggle);
        toolbarToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onAlbumClicked(SimpleAlbum simpleAlbum) {
        Bundle b = new Bundle();
        b.putString(AlbumDetailFragment.ALBUM_KEY, simpleAlbum.toJson());
        Intent i = new Intent(this, AlbumDetailActivity.class);
        i.putExtras(b);
        startActivity(i);
    }
}
