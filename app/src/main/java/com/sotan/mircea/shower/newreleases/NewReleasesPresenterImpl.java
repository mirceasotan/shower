package com.sotan.mircea.shower.newreleases;

import android.support.annotation.NonNull;

import com.mircea.sotan.domain.DataListener;
import com.mircea.sotan.domain.GetNewReleasesUseCase;
import com.mircea.sotan.model.BasePaging;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.model.SimpleAlbum;
import com.mircea.sotan.repository.networking.NetworkError;
import com.mircea.sotan.repository.networking.RestApi;
import com.sotan.mircea.shower.presenter.PresenterImpl;
import com.sotan.mircea.shower.viewModel.SimpleAlbumViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author mirceasotan
 */
public class NewReleasesPresenterImpl extends PresenterImpl<NewReleaseView>
        implements NewReleasesPresenter {
    private final GetNewReleasesUseCase newReleasesUseCase;
    private static final int DEFAULT_LIMIT = 20;
    private int offset;
    private int limit = DEFAULT_LIMIT;

    @Inject
    public NewReleasesPresenterImpl(@NonNull GetNewReleasesUseCase newReleasesUseCase) {
        this.newReleasesUseCase = newReleasesUseCase;
    }

    @Override
    public void getNewReleases() {
        newReleasesUseCase.getNewReleases(new DataListener<NewReleases>() {
            @Override
            public void onResponse(NewReleases data) {
                if (getView() != null) {
                    List<SimpleAlbumViewModel> simpleAlbumViewModels = new ArrayList<SimpleAlbumViewModel>();
                    BasePaging<SimpleAlbum> simpleAlbumBasePaging = data.getSimpleAlbums();
                    for (SimpleAlbum simpleAlbum : simpleAlbumBasePaging.getItems())
                        simpleAlbumViewModels.add(new SimpleAlbumViewModel(simpleAlbum));
                    getView().showNewReleases(simpleAlbumViewModels);
                }

                offset = offset + limit;
            }

            @Override
            public void onError(NetworkError error) {
                if (getView() != null) {
                    if (RestApi.NO_INTERNET_CONNECTION_MESSAGE.equalsIgnoreCase(error.getCodeDescription())) {
                        getView().showNewReleasesNoConnectionError();
                    } else {
                        getView().showNewReleasesApiError();
                    }
                }
            }
        }, offset, limit);
    }
}
