package com.mircea.sotan.repository.apis;

import com.mircea.sotan.model.Artists;
import com.mircea.sotan.model.BasePaging;
import com.mircea.sotan.model.FullArtist;
import com.mircea.sotan.model.SimpleAlbum;
import com.mircea.sotan.model.Tracks;

import retrofit2.http.Path;
import rx.Observable;

/**
 * @author mirceasotan
 */

public interface ArtistRestApi {

    /**
     * See {@link com.mircea.sotan.repository.services.ArtistService#getAlbums}
     */
    Observable<BasePaging<SimpleAlbum>> getAlbums(@Path("id") String id, int offset, int limit);

    /**
     * See {@link com.mircea.sotan.repository.services.ArtistService#getTopTracks}
     */
    Observable<Tracks> getTopTracks(String id, String country);

    /**
     * See {@link com.mircea.sotan.repository.services.ArtistService#getRelatedArtists}
     */
    Observable<Artists> getRelatedArtists(String id);

    /**
     * See {@link com.mircea.sotan.repository.services.ArtistService#getArtist}
     */
    Observable<FullArtist> getArtist(String id);

    /**
     * See {@link com.mircea.sotan.repository.services.ArtistService#getSeveralArtists}
     */
    Observable<Artists> getSeveralArtists(String ids);
}
