package com.sotan.mircea.shower.newreleases.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.model.SimpleAlbum;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.misc.RecyclerItemClickListener;
import com.sotan.mircea.shower.misc.RecyclerViewScrollListener;
import com.sotan.mircea.shower.misc.ScrollEndListener;
import com.sotan.mircea.shower.misc.VerticalSpaceItemDecoration;
import com.sotan.mircea.shower.albumdetail.AlbumClickCallback;
import com.sotan.mircea.shower.newreleases.presenter.NewReleasesPresenter;
import com.sotan.mircea.shower.presenter.Presenter;
import com.sotan.mircea.shower.view.BaseFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author mirceasotan
 */
public class NewReleasesFragment extends BaseFragment implements NewReleaseView,
        RecyclerItemClickListener, ScrollEndListener {
    private static final byte SPAN_1 = 1;
    private static final byte SPAN_2 = 2;
    private static final byte SPAN_DECISION_FACTOR = 3;
    @Inject
    NewReleasesPresenter presenter;
    @Bind(R.id.new_releases_recylcer_view)
    RecyclerView recyclerView;
    private NewReleasesAdapter newReleasesAdapter;
    private RecyclerViewScrollListener scrollListener;
    private AlbumClickCallback albumClickCallback;

    public static NewReleasesFragment newInstance() {
        return new NewReleasesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShowerApp.getInjector().inject(this);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            albumClickCallback = (AlbumClickCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement AlbumClickCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_releases_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, getView());
        final GridLayoutManager layoutManager = createGridLayoutManager();
        initRecyclerView(layoutManager);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getNewReleases();
    }

    @Override
    public Presenter<NewReleaseView> getPresenter() {
        return presenter;
    }

    @Override
    public Object getMvpView() {
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showNewReleases(@NonNull NewReleases newReleases) {
        scrollListener.setLoading(true);
        if (newReleasesAdapter == null) {
            newReleasesAdapter = new NewReleasesAdapter(newReleases.getSimpleAlbums().getItems(),
                    getContext(), this);
            newReleasesAdapter.setHasStableIds(true);
            recyclerView.setAdapter(newReleasesAdapter);
        } else {
            newReleasesAdapter.getAlbumList().addAll(newReleases.getSimpleAlbums().getItems());
            newReleasesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showNewReleasesNoConnectionError() {

    }

    @Override
    public void showNewReleasesApiError() {

    }

    @Override
    public void onItemClick(SimpleAlbum simpleAlbum) {
        albumClickCallback.onAlbumClicked(simpleAlbum);
    }

    private void initRecyclerView(final GridLayoutManager layoutManager) {
        int valueInPixels = (int) getResources().getDimension(R.dimen.space4);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(valueInPixels));
        scrollListener = new RecyclerViewScrollListener(layoutManager, this);
        recyclerView.addOnScrollListener(scrollListener);
    }

    @NonNull
    private GridLayoutManager createGridLayoutManager() {
        // use a grid layout manager
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_2,
                GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % SPAN_DECISION_FACTOR == 0 ? SPAN_2 : SPAN_1;
            }
        });

        return layoutManager;
    }

    @Override
    public void onScrollEnded() {
        presenter.getNewReleases();
    }
}
