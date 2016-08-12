package com.mircea.sotan.repository.services;

import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.HttpHeader;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by mircea
 */
public interface UserService {
    @GET("/v1/me")
    Call<PublicUser> getCurrentUser(@Header(HttpHeader.AUTHORIZATION) String authorization);
}
