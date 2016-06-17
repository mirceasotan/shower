package com.sotan.mircea.shower.newreleases.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mircea.sotan.model.NewReleases;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.misc.HidingScrollListener;
import com.sotan.mircea.shower.misc.VerticalSpaceItemDecoration;
import com.sotan.mircea.shower.newreleases.presenter.NewReleasesPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author mirceasotan
 */
public class NewReleasesFragment extends Fragment implements NewReleaseView {
    @Inject
    NewReleasesPresenter presenter;
    @Bind(R.id.new_releases_recylcer_view)
    RecyclerView recyclerView;
    private NewReleasesAdapter newReleasesAdapter;

    public static NewReleasesFragment newInstance() {
        return new NewReleasesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShowerApp.getInjector().inject(this);
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

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a grid layout manager
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2,
                GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 0 ? 2 : 1;
            }
        });

        int valueInPixels = (int) getResources().getDimension(R.dimen.space4);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(valueInPixels));
        recyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {

            }

            @Override
            public void onShow() {

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!presenter.isBound()) {
            presenter.bind(this);
        }

        presenter.getNewReleases();
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
    public void onStop() {
        super.onStop();

        if (presenter.isBound()) {
            presenter.unbind();
        }
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
        if (newReleasesAdapter == null) {
            newReleasesAdapter = new NewReleasesAdapter(newReleases.getSimpleAlbums().getSimpleAlbumList(), getContext());
            recyclerView.setAdapter(newReleasesAdapter);
        } else {
            newReleasesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showNewReleasesNoConnectionError() {

    }

    @Override
    public void showNewReleasesApiError() {

    }

}
