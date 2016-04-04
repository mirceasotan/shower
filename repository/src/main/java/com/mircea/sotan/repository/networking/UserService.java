package com.mircea.sotan.repository.networking;

import com.mircea.sotan.model.PublicUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Created by mircea
 */
public interface UserService {
    @GET
    Call<PublicUser> getCurrentUser(@Url String url, @Header("Authorization") String auth);
}
