package com.sotan.mircea.shower.presenter;

import android.support.annotation.Nullable;

import com.sotan.mircea.shower.presenter.contracts.MVPView;

import java.lang.ref.WeakReference;

/**
 * Created by mircea
 */
public class PresenterImpl implements Presenter {

    protected WeakReference<MVPView> view;

    @Override
    public void bind(MVPView view) {
        this.view = new WeakReference<>(view);
    }

    public void unbind() {
        view = null;
    }

    @Override
    public boolean isBound() {
        return view != null && view.get() != null;
    }

    @Nullable
    public MVPView getView() {
        if (view != null) {
            return view.get();
        }

        return null;
    }

}
