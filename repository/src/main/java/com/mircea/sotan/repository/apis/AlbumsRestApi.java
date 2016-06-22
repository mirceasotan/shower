package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.repository.networking.Listener;

/**
 * @author mirceasotan
 */
public interface AlbumsRestApi {
    /**
     * @param listener
     */
    void getAlbumDetailsAsync(@NonNull String id, @Nullable Listener<FullAlbum> listener);
}
