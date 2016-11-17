package com.mircea.sotan.repository.services;

import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.repository.networking.RestApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * @author mirceasotan
 */
public interface AlbumsService {
    /**
     * Get Spotify catalog information for a single album.
     *
     * @param id The Spotify ID for the album.
     * @return Album Detail Call
     */
    @GET("v1/albums/{id}")
    Call<FullAlbum> getAlbumDetail(@Path("id") String id,
                                   @Header(RestApi.HttpHeader.AUTHORIZATION) String authorization);

}
