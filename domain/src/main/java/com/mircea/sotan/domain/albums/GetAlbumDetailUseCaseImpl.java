package com.mircea.sotan.domain.albums;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.repository.DataListener;
import com.mircea.sotan.repository.apis.AlbumsRestApi;
import com.mircea.sotan.repository.networking.ApiListener;
import com.mircea.sotan.repository.networking.NetworkError;
import com.mircea.sotan.repository.networking.ResponseContainer;

import javax.inject.Inject;

/**
 * @author mirceasotan
 */
public class GetAlbumDetailUseCaseImpl implements GetAlbumDetailsUseCase {
    private final AlbumsRestApi albumsRestApi;

    @Inject
    public GetAlbumDetailUseCaseImpl(AlbumsRestApi albumsRestApi) {
        this.albumsRestApi = albumsRestApi;
    }


    @Override
    public void getAlbumDetail(@NonNull String id, @Nullable final DataListener<FullAlbum> dataListener) {
        albumsRestApi.getAlbumDetailsAsync(id, new ApiListener<FullAlbum>() {
            @Override
            public void onResponse(ResponseContainer<FullAlbum> apiResponse) {
                if (dataListener != null) {
                    dataListener.onResponse(apiResponse.getData());
                }
            }

            @Override
            public void onError(NetworkError networkError) {
                if (dataListener != null) {
                    dataListener.onError(networkError);
                }
            }
        });
    }
}
