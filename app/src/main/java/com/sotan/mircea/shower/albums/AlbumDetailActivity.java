package com.sotan.mircea.shower.albums;

import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.sotan.mircea.shower.DividerItemDecoration;
import com.sotan.mircea.shower.DrawableUtils;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.view.BaseActivity;
import com.sotan.mircea.shower.viewModel.ColorViewModel;
import com.sotan.mircea.shower.widget.GradientSquareImageView;
import com.sotan.mircea.shower.widget.ThinTextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author mirceasotan
 */
public class AlbumDetailActivity extends BaseActivity implements AlbumDetailContract.AlbumDetailView {
    public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";
    public static final String VIEW_NAME_HEADER_IMAGE2 = "detail:header:image2";
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
    @Bind(R.id.album_title_textView)
    ThinTextView titleTextView;
    @Bind(R.id.album_author_textView)
    ThinTextView artistTextView;
    @Bind(R.id.album_desc_textView)
    ThinTextView descTextView;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Inject
    AlbumDetailContract.AlbumDetailPresenter presenter;

    private AlbumDetailAdapter adapter;
    private SimpleAlbumViewModel simpleAlbumViewModel;
    private Drawable d1;
    private Drawable d2;
    private boolean pauseToPlayEnabled;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simpleAlbumViewModel = SimpleAlbumViewModel.fromJson(getIntent()
                .getStringExtra(ALBUM_KEY), SimpleAlbumViewModel.class);

        ColorViewModel colorViewModel = simpleAlbumViewModel.getColorViewModel();

        configureStatusBar(colorViewModel);

        setContentView(R.layout.base_activity_layout);

        ButterKnife.bind(this);

        ShowerApp.getInjector().inject(this);

        ViewCompat.setTransitionName(albumImageView, VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(recyclerView, VIEW_NAME_HEADER_IMAGE2);

        configureRecyclerView(colorViewModel);
        configureToolbar(colorViewModel);
        configureFab(colorViewModel);

        loadAlbumImage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bind(this);
        presenter.getAlbumDetail(simpleAlbumViewModel.getId());
    }

    @Override
    public void showAlbum(@NonNull FullAlbumViewModel fullAlbumViewModel) {
        descTextView.setText(fullAlbumViewModel.getDescription());
        artistTextView.setText(fullAlbumViewModel.getArtist());

        if (adapter == null) {
            AlbumDetailAdapter.Builder builder = new AlbumDetailAdapter.Builder();
            adapter = builder.withTracks(fullAlbumViewModel.getTrackViewModels())
                    .withTextColor(simpleAlbumViewModel.getColorViewModel().getTextColor())
                    .withCopyRight(fullAlbumViewModel.getCopyright())
                    .build();

            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AnimatedVectorDrawable drawable;

            if (!pauseToPlayEnabled) {
                drawable = (AnimatedVectorDrawable) d1;
            } else {
                drawable = (AnimatedVectorDrawable) d2;
            }

            if (!drawable.isRunning()) {
                fab.setImageDrawable(drawable);
                drawable.start();
            }

            pauseToPlayEnabled = !pauseToPlayEnabled;
        } else {
            //TODO Implement Pre 21
            Log.d("", "");
        }
    }

    private void configureStatusBar(ColorViewModel colorViewModel) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ColorUtils.setAlphaComponent(colorViewModel.getBackgroundColor(), 200));
    }

    private void configureToolbar(ColorViewModel colorViewModel) {
        int color = colorViewModel.getBackgroundColor();
        int textColor = colorViewModel.getTextColor();
        int scrimDuration = 300;
        int alpha = 100;

        setSupportActionBar(toolbar);

        Drawable drawable = DrawableUtils.getDrawable(R.drawable.ic_back, this);
        drawable.setColorFilter(textColor, PorterDuff.Mode.SRC_IN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(drawable);
        }

        toolbar.setBackgroundColor(ColorUtils.setAlphaComponent(color, alpha));

        collapsingToolbarLayout.setContentScrimColor(color);
        collapsingToolbarLayout.setScrimAnimationDuration(scrimDuration);
        collapsingToolbarLayout.setStatusBarScrimColor(color);
        collapsingToolbarLayout.setExpandedTitleColor(textColor);
        collapsingToolbarLayout.setCollapsedTitleTextColor(textColor);
        collapsingToolbarLayout.setTitle("");

        appBarLayout.setBackgroundColor(color);
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            if (verticalOffset == -appBarLayout.getTotalScrollRange()) {
                collapsingToolbarLayout.setTitle(simpleAlbumViewModel.getName());
            } else {
                collapsingToolbarLayout.setTitle("");
            }
        });

        titleTextView.setText(simpleAlbumViewModel.getName());
        titleTextView.setTextColor(textColor);
        artistTextView.setTextColor(textColor);
        descTextView.setTextColor(textColor);
    }

    private void configureRecyclerView(ColorViewModel colorViewModel) {
        recyclerView.setBackgroundColor(colorViewModel.getBackgroundColor());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                colorViewModel.getAlphaTextColor()));
    }

    private void configureFab(ColorViewModel colorViewModel) {
        fab.setBackgroundTintList(ColorStateList.valueOf(colorViewModel.getFabBackgroundColor()));

        ColorFilter colorFilter = new PorterDuffColorFilter(colorViewModel.getFabIconColor(),
                PorterDuff.Mode.SRC_IN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            d1 = DrawableUtils.getDrawable(R.drawable.animated_vector_play_to_pause, this);
            d2 = DrawableUtils.getDrawable(R.drawable.animated_vector_pause_to_play, this);
        } else {
            d1 = DrawableUtils.getDrawable(R.drawable.ic_play_vector, this);
            d2 = DrawableUtils.getDrawable(R.drawable.ic_pause_vector, this);
        }

        d1.setColorFilter(colorFilter);
        d2.setColorFilter(colorFilter);

        fab.setImageDrawable(d1);
    }

    private void loadAlbumImage() {
        Picasso.with(AlbumDetailActivity.this)
                .load(simpleAlbumViewModel.getImageUrl()).into((Target) albumImageView);
    }
}


