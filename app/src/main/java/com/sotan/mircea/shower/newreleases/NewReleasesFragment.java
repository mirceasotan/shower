package com.sotan.mircea.shower.newreleases;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.misc.RecyclerItemClickListener;
import com.sotan.mircea.shower.misc.RecyclerViewScrollListener;
import com.sotan.mircea.shower.misc.ScrollEndListener;
import com.sotan.mircea.shower.misc.VerticalSpaceItemDecoration;
import com.sotan.mircea.shower.presenter.Presenter;
import com.sotan.mircea.shower.view.BaseFragment;
import com.sotan.mircea.shower.viewModel.SimpleAlbumViewModel;

import java.util.List;

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
    private NewReleasesUIDelegate albumClickCallback;

    @Override
    public void onItemClick(SimpleAlbumViewModel simpleAlbum, View v) {
        albumClickCallback.onNewReleaseClicked(simpleAlbum, v);
    }

    public interface NewReleasesUIDelegate {
        void onNewReleaseClicked(SimpleAlbumViewModel simpleAlbum, View v);
    }

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
            albumClickCallback = (NewReleasesUIDelegate) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NewReleasesUIDelegate");
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
    public void showNewReleases(@NonNull List<SimpleAlbumViewModel> simpleAlbumViewModels) {
        scrollListener.setLoading(true);
        if (newReleasesAdapter == null) {
            newReleasesAdapter = new NewReleasesAdapter(simpleAlbumViewModels,
                    getContext(), this);
            newReleasesAdapter.setHasStableIds(true);
            recyclerView.setAdapter(newReleasesAdapter);
        } else {
            newReleasesAdapter.getAlbumList().addAll(simpleAlbumViewModels);
            newReleasesAdapter.notifyDataSetChanged();
            Log.d("msg","data set");
        }
    }

    @Override
    public void showNewReleasesNoConnectionError() {

    }

    @Override
    public void showNewReleasesApiError() {

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
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        recyclerView.getItemAnimator().setChangeDuration(0);
    }

    @NonNull
    private GridLayoutManager createGridLayoutManager() {
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
