package com.sotan.mircea.shower.albumdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;

import com.mircea.sotan.model.FullAlbum;
import com.sotan.mircea.shower.DividerItemDecoration;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.view.BaseActivity;
import com.sotan.mircea.shower.viewModel.FullAlbumViewModel;
import com.sotan.mircea.shower.viewModel.SimpleAlbumViewModel;
import com.sotan.mircea.shower.widget.GradientSquareImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author mirceasotan
 */
public class AlbumDetailActivity extends BaseActivity implements AlbumDetailView {
    public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";
    public static final String VIEW_NAME_HEADER_IMAGE2 = "detail:header:image2";
    public static final String ALBUM_ID_KEY = "albumId";
    public static final String ALBUM_IMAGE_URL_KEY = "albumImageUrl";
    public static final String ALBUM_COLOR_GRADIENT_KEY = "grad";
    public static final String ALBUM_KEY = "album";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.album_detail_imageView)
    GradientSquareImageView albumImageView;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.album_detail_recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.coo_bar_layout)
    CoordinatorLayout coordinatorLayout;
    @Inject
    AlbumDetailPresenter presenter;

    private TrackAdapter adapter;
    private SimpleAlbumViewModel simpleAlbumViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simpleAlbumViewModel = SimpleAlbumViewModel.fromJson(getIntent()
                .getStringExtra(ALBUM_KEY), SimpleAlbumViewModel.class);
        setContentView(R.layout.base_activity_layout);
        ButterKnife.bind(this);
        ShowerApp.getInjector().inject(this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int color = simpleAlbumViewModel.getColorViewModel().getMainColor();

        setTitle("");
        toolbar.setTitle("");

        ViewCompat.setTransitionName(albumImageView, VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(recyclerView, VIEW_NAME_HEADER_IMAGE2);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                simpleAlbumViewModel.getColorViewModel().getAlphaTextColor()));

        recyclerView.setBackgroundColor(color);
        appBarLayout.setBackgroundColor(color);

        loadAlbumImage();
    }

    private void loadAlbumImage() {
        Picasso.with(AlbumDetailActivity.this).load(simpleAlbumViewModel.getImageUrl()).into((Target) albumImageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bind(this);
        presenter.getAlbumDetail(simpleAlbumViewModel.getId());
    }

    private void setTitle(@NonNull FullAlbum fullAlbum) {
        if (!TextUtils.isEmpty(fullAlbum.getName()) && getSupportActionBar() != null) {
            toolbar.setTitle(fullAlbum.getName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showAlbum(@NonNull FullAlbumViewModel fullAlbumViewModel) {
        if (adapter == null) {
            adapter = new TrackAdapter(this, fullAlbumViewModel.getTrackViewModels(),
                    simpleAlbumViewModel.getColorViewModel().getTextColor());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
