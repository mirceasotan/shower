package com.sotan.mircea.shower.misc;

import android.view.View;

import com.sotan.mircea.shower.viewModel.SimpleAlbumViewModel;

/**
 * @author mirceasotan
 */
public interface RecyclerItemClickListener {
    void onItemClick(SimpleAlbumViewModel simpleAlbum, View v);
}
