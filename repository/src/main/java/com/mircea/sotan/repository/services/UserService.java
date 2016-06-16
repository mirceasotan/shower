package com.mircea.sotan.repository.services;

import com.mircea.sotan.model.PublicUser;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mircea
 */
public interface UserService {
    @GET("/v1/me")
    Call<PublicUser> getCurrentUser();
}
