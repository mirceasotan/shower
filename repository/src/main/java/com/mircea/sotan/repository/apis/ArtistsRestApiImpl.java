package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;

import com.mircea.sotan.model.Artists;
import com.mircea.sotan.model.BasePaging;
import com.mircea.sotan.model.FullArtist;
import com.mircea.sotan.model.SimpleAlbum;
import com.mircea.sotan.model.Tracks;
import com.mircea.sotan.repository.networking.RequestLog;
import com.mircea.sotan.repository.networking.RestApi;
import com.mircea.sotan.repository.networking.TokenStorage;
import com.mircea.sotan.repository.services.ArtistService;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author mirceasotan
 */

public class ArtistsRestApiImpl extends RestApi implements ArtistRestApi {

    private final ArtistService artistService;

    public ArtistsRestApiImpl(@NonNull ArtistService service, @NonNull RequestLog requestLog,
                              TokenStorage tokenStorage) {
        super(requestLog, tokenStorage);
        artistService = service;
    }

    @Override
    public Observable<BasePaging<SimpleAlbum>> getAlbums(String id, int offset, int limit) {
        return artistService.getAlbums(id, offset, limit, tokenStorage.getAuthToken())
                .subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<Tracks> getTopTracks(String id, String country) {
        return artistService.getTopTracks(id, country, tokenStorage.getAuthToken())
                .subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<Artists> getRelatedArtists(String id) {
        return artistService.getRelatedArtists(id, tokenStorage.getAuthToken())
                .subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<FullArtist> getArtist(String id) {
        return artistService.getArtist(id, tokenStorage.getAuthToken())
                .subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<Artists> getSeveralArtists(String ids) {
        return artistService.getSeveralArtists(ids, tokenStorage.getAuthToken())
                .subscribeOn(Schedulers.newThread());
    }
}
