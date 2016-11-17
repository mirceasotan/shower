package com.sotan.mircea.shower.browse;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.albums.SimpleAlbumViewModel;
import com.sotan.mircea.shower.misc.RecyclerItemClickListener;
import com.sotan.mircea.shower.misc.RecyclerViewScrollListener;
import com.sotan.mircea.shower.misc.ScrollEndListener;
import com.sotan.mircea.shower.misc.VerticalSpaceItemDecoration;
import com.sotan.mircea.shower.view.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author mirceasotan
 */

public abstract class BaseNewReleasesFragment
        extends BaseFragment<NewReleasesContract.NewReleasesPresenter, NewReleasesContract.NewReleaseView>
        implements NewReleasesContract.NewReleaseView,
        RecyclerItemClickListener, ScrollEndListener {
    private static final byte SPAN_1 = 1;
    private static final byte SPAN_2 = 2;
    private static final byte SPAN_DECISION_FACTOR = 3;

    @Inject
    NewReleasesContract.NewReleasesPresenter presenter;
    @Bind(R.id.new_releases_recylcer_view)
    RecyclerView recyclerView;

    private NewReleasesAdapter adapter;
    private RecyclerViewScrollListener scrollListener;
    private NewReleasesUIDelegate delegate;

    public interface NewReleasesUIDelegate {
        void onNewReleaseClicked(SimpleAlbumViewModel simpleAlbum, View v);
    }

    protected abstract void load();

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
            delegate = (NewReleasesUIDelegate) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NewReleasesUIDelegate");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        load();
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
    public void onItemClick(SimpleAlbumViewModel simpleAlbum, View v) {
        delegate.onNewReleaseClicked(simpleAlbum, v);
    }

    @Override
    public NewReleasesContract.NewReleasesPresenter getPresenter() {
        return presenter;
    }

    @Override
    public NewReleasesContract.NewReleaseView getMvpView() {
        return this;
    }

    @Override
    public void showNewReleases(@NonNull List<SimpleAlbumViewModel> viewModels) {
        scrollListener.setLoading(true);
        if (adapter == null) {
            adapter = new NewReleasesAdapter(viewModels,
                    getContext(), this);
            adapter.setHasStableIds(true);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.getAlbumList().addAll(viewModels);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showNewReleasesNoConnectionError() {
        Toast.makeText(getActivity(), "Connection NetworkError", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNewReleasesApiError() {
        Toast.makeText(getActivity(), "API NetworkError", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScrollEnded() {
        load();
    }

    private void initRecyclerView(final GridLayoutManager manager) {
        int valueInPixels = (int) getResources().getDimension(R.dimen.space4);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(valueInPixels));
        scrollListener = new RecyclerViewScrollListener(manager, this);
        recyclerView.addOnScrollListener(scrollListener);
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();

        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        recyclerView.getItemAnimator().setChangeDuration(0);
    }

    @NonNull
    private GridLayoutManager createGridLayoutManager() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), SPAN_2,
                GridLayoutManager.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % SPAN_DECISION_FACTOR == 0 ? SPAN_2 : SPAN_1;
            }
        });

        return manager;
    }

}
