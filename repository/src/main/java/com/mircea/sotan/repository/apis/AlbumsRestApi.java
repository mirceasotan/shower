package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.repository.networking.ApiListener;

/**
 * @author mirceasotan
 */
public interface AlbumsRestApi {
    /**
     * @param apiListener
     */
    void getAlbumDetailsAsync(@NonNull String id, @Nullable ApiListener<FullAlbum> apiListener);
}
