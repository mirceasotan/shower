package com.sotan.mircea.shower;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mircea
 */
public class NavigationActivity extends AppCompatActivity {
    @Bind(R.id.home_drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toolbarToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_content_desc_open, R.string.drawer_content_desc_closed);
        drawerLayout.setDrawerListener(toolbarToggle);
        toolbarToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
