package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.repository.networking.Listener;
import com.mircea.sotan.repository.networking.RequestLog;
import com.mircea.sotan.repository.networking.RestApi;
import com.mircea.sotan.repository.networking.TokenStorage;
import com.mircea.sotan.repository.services.AlbumsService;

import retrofit2.Call;

/**
 * @author mirceasotan
 */
public class AlbumsRestApiImpl extends RestApi implements AlbumsRestApi {
    private final AlbumsService albumsService;

    public AlbumsRestApiImpl(@NonNull AlbumsService service, @NonNull RequestLog requestLog, TokenStorage tokenStorage) {
        super(requestLog, tokenStorage);
        albumsService = service;
    }

    @Override
    public void getAlbumDetailsAsync(@NonNull String id, @Nullable Listener<FullAlbum> listener) {
        Call<FullAlbum> call = albumsService.getAlbumDetail(id, tokenStorage.getAuthToken());
        enqueueAsync(call, listener);
    }
}
