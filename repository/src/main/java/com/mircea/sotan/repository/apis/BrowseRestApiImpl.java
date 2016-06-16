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
public class BrowseRestApiImpl extends RestApi implements BrowseRestApi {

    private final BrowseService browseService;

    public BrowseRestApiImpl(@NonNull Retrofit retrofit, Log log) {
        super(log);
        browseService = retrofit.create(BrowseService.class);
    }

    @Override
    public void getNewReleasesAsync(@Nullable Listener<NewReleases> listener) {
        Call<NewReleases> call = browseService.getNewReleases();
        enqueueAsync(call, listener);
    }

    @Override
    public void getCategoriesAsync(@Nullable Listener<List<Category>> listener) {
        Call<List<Category>> call = browseService.getCategories();
        enqueueAsync(call, listener);
    }
}
