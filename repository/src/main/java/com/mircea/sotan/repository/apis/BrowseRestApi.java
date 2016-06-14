package com.mircea.sotan.repository.apis;

import android.support.annotation.Nullable;

import com.mircea.sotan.model.Category;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.networking.Listener;

import java.util.List;

/**
 * @author mirceasotan
 */
public interface BrowseRestApi {
    /**
     * @param listener Callback used for notifying caller about request resolution
     */
    void getNewReleasesAsync(@Nullable Listener<NewReleases> listener);

    /**
     * @param listener Callback used for notifying caller about request resolution
     */
    void getCategoriesAsync(@Nullable Listener<List<Category>> listener);
}
