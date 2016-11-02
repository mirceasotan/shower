package com.mircea.sotan.repository.services;

import com.mircea.sotan.model.Artists;
import com.mircea.sotan.model.BasePaging;
import com.mircea.sotan.model.FullArtist;
import com.mircea.sotan.model.SimpleAlbum;
import com.mircea.sotan.model.Tracks;
import com.mircea.sotan.repository.networking.HttpHeader;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author mirceasotan
 */

public interface ArtistService {

    /**
     * Get Spotify catalog information about an artist’s albums. Optional parameters can be
     * specified in the query string to filter and sort the response.
     *
     * @param id The Spotify ID for the artist.
     * @return On success, the HTTP status code in the response header is 200 OK and the response
     * body contains an array of simplified album objects (wrapped in a paging object) in JSON
     * format.On error, the header status code is an error code and the response body contains an
     * error object.
     */
    @GET("v1/artists/{id}/albums")
    Observable<BasePaging<SimpleAlbum>> getAlbums(@Path("id") String id,
                                                  @Query("offset") int offset,
                                                  @Query("limit") int limit,
                                                  @Header(HttpHeader.AUTHORIZATION) String authorization);

    /**
     * Get Spotify catalog information about an artist’s top tracks by country.
     *
     * @param id      The Spotify ID for the artist.
     * @param country Required. The country: an ISO 3166-1 alpha-2 country code.
     * @return On success, the HTTP status code in the response header is 200 OK and the response
     * body contains an object whose key is "tracks" and whose value is an array of up to 10 track
     * objects in JSON format. On error, the header status code is an error code and the response
     * body contains an error object. The 10 maximum tracks are the ones displayed by the Spotify
     * app. If you want to fetch more artist’s top tracks, an alternative way is to use Echo Nest
     * song/search to accomplish this. It will accept a Spotify artist id for input and give you
     * Spotify id’s in the output.
     */
    @GET("v1/artists/{id}/top-tracks")
    Observable<Tracks> getTopTracks(@Path("id") String id,
                                    @Query("country") String country,
                                    @Header(HttpHeader.AUTHORIZATION) String authorization);

    /**
     * Get Spotify catalog information about artists similar to a given artist. Similarity is based
     * on analysis of the Spotify community’s listening history.
     *
     * @param id The Spotify ID for the artist.
     * @return On success, the HTTP status code in the response header is 200 OK and the response
     * body contains an object whose key is "artists" and whose value is an array of up to 20 artist
     * objects in JSON format. On error, the header status code is an error code and the response
     * body contains an error object.
     */
    @GET("v1/artists/{id}/related-artists")
    Observable<Artists> getRelatedArtists(@Path("id") String id,
                                          @Header(HttpHeader.AUTHORIZATION) String authorization);

    /**
     * Get Spotify catalog information for a single artist identified by their unique Spotify ID.
     *
     * @param id The Spotify ID for the artist.
     * @return On success, the HTTP status code in the response header is 200 OK and the response
     * body contains an artist object in JSON format. On error, the header status code is an error
     * code and the response body contains an error object.
     */
    @GET("v1/artists/{id}")
    Observable<FullArtist> getArtist(@Path("id") String id,
                                     @Header(HttpHeader.AUTHORIZATION) String authorization);

    /**
     * Get Spotify catalog information for several artists based on their Spotify IDs.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the artists. Maximum: 50 IDs.
     * @return On success, the HTTP status code in the response header is 200 OK and the response
     * body contains an object whose key is "artists" and whose value is an array of artist objects
     * in JSON format. Objects are returned in the order requested. If an object is not found, a
     * null value is returned in the appropriate position. Duplicate ids in the query will result
     * in duplicate objects in the response. On error, the header status code is an error code and
     * the response body contains an error object.
     */
    @GET("v1/artists")
    Observable<Artists> getSeveralArtists(@Query("ids") String ids,
                                          @Header(HttpHeader.AUTHORIZATION) String authorization);


}
