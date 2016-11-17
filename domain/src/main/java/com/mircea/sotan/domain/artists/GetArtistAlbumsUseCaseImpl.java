package com.mircea.sotan.domain.artists;

import com.mircea.sotan.model.BasePaging;
import com.mircea.sotan.model.SimpleAlbum;
import com.mircea.sotan.repository.apis.ArtistRestApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author mirceasotan
 */

public class GetArtistAlbumsUseCaseImpl implements GetArtistAlbumsUseCase {

    private final ArtistRestApi artistRestApi;

    @Inject
    public GetArtistAlbumsUseCaseImpl(ArtistRestApi artistRestApi) {
        this.artistRestApi = artistRestApi;
    }

    @Override
    public Observable<BasePaging<SimpleAlbum>> getAlbums(String id, int offset, int limit) {
        return artistRestApi.getAlbums(id, offset, limit);
    }
}
