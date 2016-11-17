package com.mircea.sotan.repository.apis;

import android.support.annotation.Nullable;

import com.mircea.sotan.model.Category;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.networking.ApiListener;

import java.util.List;

import rx.Observable;

/**
 * @author mirceasotan
 */
public interface BrowseRestApi {
    /**
     * @param apiListener Callback used for notifying caller about request resolution
     */
    void getNewReleasesAsync(@Nullable ApiListener<NewReleases> apiListener, int offset, int limit);

    /**
     */
    Observable<NewReleases> getRxNewReleasesAsync(int offset, int limit);

    /**
     * @param apiListener Callback used for notifying caller about request resolution
     */
    void getCategoriesAsync(@Nullable ApiListener<List<Category>> apiListener);
}
