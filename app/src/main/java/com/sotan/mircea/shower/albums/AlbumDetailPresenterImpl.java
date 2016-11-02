package com.sotan.mircea.shower.albums;

import android.support.annotation.NonNull;

import com.mircea.sotan.domain.DataListener;
import com.mircea.sotan.domain.albums.GetAlbumDetailsUseCase;
import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.repository.networking.NetworkError;
import com.sotan.mircea.shower.presenter.PresenterImpl;

import javax.inject.Inject;

/**
 * @author mirceasotan
 */
public class AlbumDetailPresenterImpl extends PresenterImpl<AlbumDetailView> implements AlbumDetailPresenter {

    private final GetAlbumDetailsUseCase albumDetailsUseCase;

    @Inject
    public AlbumDetailPresenterImpl(GetAlbumDetailsUseCase albumDetailsUseCase) {
        this.albumDetailsUseCase = albumDetailsUseCase;
    }

    @Override
    public void getAlbumDetail(@NonNull String id) {
        albumDetailsUseCase.getAlbumDetail(id, new DataListener<FullAlbum>() {
            @Override
            public void onResponse(FullAlbum data) {
                if (getView() != null) {
                    getView().showAlbum(new FullAlbumViewModel(data));
                }
            }

            @Override
            public void onError(NetworkError error) {

            }
        });
    }
}
