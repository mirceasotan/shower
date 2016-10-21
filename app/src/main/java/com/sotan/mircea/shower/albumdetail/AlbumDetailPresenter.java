package com.sotan.mircea.shower.albumdetail;

import android.support.annotation.NonNull;

import com.sotan.mircea.shower.presenter.Presenter;

/**
 * @author mirceasotan
 */
public interface AlbumDetailPresenter extends Presenter<AlbumDetailView> {
    void getAlbumDetail(@NonNull String id);
}