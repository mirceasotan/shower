package com.mircea.sotan.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.FullAlbum;

/**
 * @author mirceasotan
 */
public interface GetAlbumDetailsUseCase extends UseCase {
    void getAlbumDetail(@NonNull String id, @Nullable DataListener<FullAlbum> dataListener);
}
