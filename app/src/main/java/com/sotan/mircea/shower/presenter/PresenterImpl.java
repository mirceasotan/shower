package com.sotan.mircea.shower.presenter;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * @author mircea
 */
public class PresenterImpl<V> implements Presenter<V> {

    private WeakReference<V> view;

    /**
     * {@link Presenter#bind(Object)} )}
     */
    @Override
    public void bind(V view) {
        this.view = new WeakReference<>(view);
    }

    /**
     * {@link Presenter#unbind()}
     */
    public void unbind() {
        view = null;
    }

    /**
     * {@link Presenter#isBound()}
     */
    @Override
    public boolean isBound() {
        return view != null && view.get() != null;
    }

    @Nullable
    public V getView() {
        return view != null ? view.get() : null;
    }
}
