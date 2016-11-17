package com.sotan.mircea.shower.albums;

import android.support.annotation.NonNull;

import com.sotan.mircea.shower.presenter.Presenter;

/**
 * @author mirceasotan
 */

public interface AlbumDetailContract {

    interface AlbumDetailView {
        void showAlbum(@NonNull FullAlbumViewModel fullAlbumViewModel);
    }

    interface AlbumDetailPresenter extends Presenter<AlbumDetailView> {
        void getAlbumDetail(@NonNull String id);
    }
}
