package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.Category;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.networking.*;
import com.mircea.sotan.repository.services.BrowseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * @author mirceasotan
 */
public class BrowseRestApiImpl implements BrowseRestApi {

    private final BrowseService browseService;
    private final Log log;

    public BrowseRestApiImpl(@NonNull Retrofit retrofit, Log log) {
        browseService = retrofit.create(BrowseService.class);
        this.log = log;
    }

    @Override
    public void getNewReleasesAsync(@Nullable Listener<NewReleases> listener) {
        Call<NewReleases> call = browseService.getNewReleases();
        RestApi<NewReleases> restApi = new RestApi<>(log);
        restApi.enqueueAsync(call, listener);
    }

    @Override
    public void getCategoriesAsync(@Nullable Listener<List<Category>> listener) {
        Call<List<Category>> call = browseService.getCategories();
        RestApi<List<Category>> restApi = new RestApi<>(log);
        restApi.enqueueAsync(call, listener);
    }

}
