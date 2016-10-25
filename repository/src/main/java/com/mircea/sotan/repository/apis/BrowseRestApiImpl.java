package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.Category;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.networking.Listener;
import com.mircea.sotan.repository.networking.RequestLog;
import com.mircea.sotan.repository.networking.RestApi;
import com.mircea.sotan.repository.networking.TokenStorage;
import com.mircea.sotan.repository.services.BrowseService;

import java.util.List;

import retrofit2.Call;
import rx.Observable;

/**
 * @author mirceasotan
 */
public class BrowseRestApiImpl extends RestApi implements BrowseRestApi {

    private final BrowseService browseService;

    public BrowseRestApiImpl(@NonNull BrowseService service, @NonNull RequestLog requestLog,
                             @NonNull TokenStorage tokenStorage) {
        super(requestLog, tokenStorage);
        browseService = service;
    }

    @Override
    public void getNewReleasesAsync(@Nullable Listener<NewReleases> listener, int offset, int limit) {
        Call<NewReleases> call = browseService.getNewReleases(offset, limit, tokenStorage.getAuthToken());
        enqueueAsync(call, listener);
    }

    @Override
    public Observable<NewReleases> getRxNewReleasesAsync(int offset, int limit) {
        Observable<NewReleases> newReleasesObservable = browseService.getRxNewReleases(offset, limit,
                tokenStorage.getAuthToken());
        enqueueRxAsync(newReleasesObservable);
        return newReleasesObservable;
    }

    @Override
    public void getCategoriesAsync(@Nullable Listener<List<Category>> listener) {
        Call<List<Category>> call = browseService.getCategories();
        enqueueAsync(call, listener);
    }
}
