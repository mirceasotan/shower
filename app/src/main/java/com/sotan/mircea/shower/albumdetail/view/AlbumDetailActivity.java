package com.sotan.mircea.shower.albumdetail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.mircea.sotan.model.FullAlbum;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.albumdetail.presenter.AlbumDetailPresenter;
import com.sotan.mircea.shower.view.BaseActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author mirceasotan
 */
public class AlbumDetailActivity extends BaseActivity implements AlbumDetailView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.album_detail_imageView)
    ImageView albumImageView;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.tracks_listView)
    ListView trackListView;
    @Inject
    AlbumDetailPresenter presenter;
    public static final String ALBUM_ID_KEY = "albumId";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        ButterKnife.bind(this);
        ShowerApp.getInjector().inject(this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();

        presenter.bind(this);

        if (i.hasExtra(ALBUM_ID_KEY)) {
            presenter.getAlbumDetail(i.getStringExtra(ALBUM_ID_KEY));
        }
    }

    @Override
    public void showAlbum(FullAlbum fullAlbum) {
        Picasso.with(this).load(fullAlbum.getImages().get(0).getUrl()).into(albumImageView);
        setTitle(fullAlbum);
        TrackAdapter adapter = new TrackAdapter(this, android.R.layout.simple_list_item_1, fullAlbum.getTracks().getItems());
        trackListView.setAdapter(adapter);

    }

    private void setTitle(FullAlbum fullAlbum) {
        if (!TextUtils.isEmpty(fullAlbum.getName()) && getSupportActionBar() != null) {
            toolbar.setTitle(fullAlbum.getName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
