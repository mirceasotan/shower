package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.repository.networking.Listener;
import com.mircea.sotan.repository.networking.Log;
import com.mircea.sotan.repository.networking.RestApi;
import com.mircea.sotan.repository.services.AlbumsService;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * @author mirceasotan
 */
public class AlbumsRestApiImpl extends RestApi implements AlbumsRestApi {
    private final AlbumsService albumsService;

    public AlbumsRestApiImpl(@NonNull Retrofit retrofit, @NonNull Log log) {
        super(log);
        albumsService = retrofit.create(AlbumsService.class);
    }

    @Override
    public void getAlbumDetailsAsync(@NonNull String id, @Nullable Listener<FullAlbum> listener) {
        Call<FullAlbum> call = albumsService.getAlbumDetail(id);
        enqueueAsync(call, listener);
    }
}
