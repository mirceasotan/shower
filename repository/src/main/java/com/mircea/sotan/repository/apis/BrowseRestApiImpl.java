package com.mircea.sotan.repository.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mircea.sotan.model.Category;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.networking.ApiListener;
import com.mircea.sotan.repository.networking.RequestLog;
import com.mircea.sotan.repository.networking.RestApi;
import com.mircea.sotan.repository.networking.TokenStorage;
import com.mircea.sotan.repository.services.BrowseService;

import java.util.List;

import retrofit2.Call;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author mirceasotan
 */
public class BrowseRestApiImpl extends RestApi implements BrowseRestApi {

    private final BrowseService service;

    public BrowseRestApiImpl(@NonNull BrowseService service, @NonNull RequestLog requestLog,
                             @NonNull TokenStorage tokenStorage) {
        super(requestLog, tokenStorage);
        this.service = service;
    }

    @Override
    public void getNewReleasesAsync(@Nullable ApiListener<NewReleases> apiListener, int offset, int limit) {
        Call<NewReleases> call = service.getNewReleases(offset, limit, storage.getAuthToken());
        enqueueAsync(call, apiListener);
    }

    @Override
    public Observable<NewReleases> getRxNewReleasesAsync(int offset, int limit) {
        return service.getRxNewReleases(offset, limit,
                storage.getAuthToken()).subscribeOn(Schedulers.newThread());
    }

    @Override
    public void getCategoriesAsync(@Nullable ApiListener<List<Category>> apiListener) {
        Call<List<Category>> call = service.getCategories();
        enqueueAsync(call, apiListener);
    }
}
