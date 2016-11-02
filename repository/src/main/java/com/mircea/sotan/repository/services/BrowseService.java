package com.mircea.sotan.repository.services;

import com.mircea.sotan.model.Category;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.networking.HttpHeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author mirceasotan
 */
public interface BrowseService {
    /**
     * Get a list of new album releases featured in Spotify (shown, for example,
     * on a Spotify player’s “Browse” tab).
     *
     * @return New Releases API call
     */
    @GET("v1/browse/new-releases")
    Call<NewReleases> getNewReleases(@Query("offset") int offset,
                                     @Query("limit") int limit,
                                     @Header(HttpHeader.AUTHORIZATION) String authorization);


    /**
     * Get a list of new album releases featured in Spotify (shown, for example,
     * on a Spotify player’s “Browse” tab).
     *
     * @return New Releases Observable
     */
    @GET("v1/browse/new-releases")
    Observable<NewReleases> getRxNewReleases(@Query("offset") int offset,
                                             @Query("limit") int limit,
                                             @Header(HttpHeader.AUTHORIZATION) String authorization);

    /**
     * Get a list of categories used to tag items in Spotify (on, for example,
     * the Spotify player’s “Browse” tab).
     *
     * @return Categories API call
     */
    @GET("v1/browse/categories")
    Call<List<Category>> getCategories();
}
