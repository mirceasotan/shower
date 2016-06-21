package com.sotan.mircea.shower.albumdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.mircea.sotan.model.SimpleAlbum;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.view.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author mirceasotan
 */
public class AlbumDetailActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private SimpleAlbum simpleAlbum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            if (getIntent().hasExtra(AlbumDetailFragment.ALBUM_KEY))
                replaceFragment(AlbumDetailFragment
                                .newInstance(getIntent().getStringExtra(AlbumDetailFragment.ALBUM_KEY)),
                        R.id.frame_container);
        }
    }
}
