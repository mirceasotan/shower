package com.sotan.mircea.shower.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Created by mircea
 */
public class Presenter {

    protected WeakReference<NavigationActivityPresenter.View> view;

    public void bind(@NonNull NavigationActivityPresenter.View view) {
        this.view = new WeakReference<>(view);
    }

    public void unbind() {
        view = null;
    }

    public boolean isBoundToView() {
        return view != null && view.get() != null;
    }

    @Nullable
    public NavigationActivityPresenter.View getView() {
        if (view != null) {
            return view.get();
        }

        return null;
    }

}
