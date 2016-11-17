package com.mircea.sotan.domain.albums;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.domain.UseCase;
import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.repository.DataListener;

/**
 * @author mirceasotan
 */
public interface GetAlbumDetailsUseCase extends UseCase {
    void getAlbumDetail(@NonNull String id, @Nullable DataListener<FullAlbum> dataListener);
}
