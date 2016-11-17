package com.mircea.sotan.repository.services;

import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.RestApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by mircea
 */
public interface UserService {
    /**
     * Get detailed profile information about the current user (including the current user’s username).
     *
     * @param authorization
     * @return Required. A valid access token from the Spotify Accounts service: see the Web API
     * Authorization Guide for details. The access token must have been issued on behalf of the
     * current user. Reading the user's email address requires the user-read-email scope; reading
     * country and product subscription level requires the user-read-private scope. Reading the
     * user's birthdate requires the user-read-birthdate scope.
     */
    @GET("v1/me")
    Call<PublicUser> getCurrentUser(@Header(RestApi.HttpHeader.AUTHORIZATION) String authorization);
}
