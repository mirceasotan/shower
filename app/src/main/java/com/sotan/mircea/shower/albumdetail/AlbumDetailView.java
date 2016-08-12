package com.sotan.mircea.shower.albumdetail;

import android.support.annotation.NonNull;

import com.sotan.mircea.shower.viewModel.FullAlbumViewModel;

/**
 * @author mirceasotan
 */
public interface AlbumDetailView {
    void showAlbum(@NonNull FullAlbumViewModel fullAlbumViewModel);
}
