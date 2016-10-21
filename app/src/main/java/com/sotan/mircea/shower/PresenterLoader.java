package com.sotan.mircea.shower;

import android.content.Context;
import android.support.v4.content.Loader;

import com.sotan.mircea.shower.presenter.Presenter;

/**
 * Created by mirceasotan
 */

public class PresenterLoader<T extends Presenter> extends Loader<T> {

    private T presenter;
    private PresenterFactory<T> presenterFactory;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */

    public PresenterLoader(Context context, PresenterFactory<T> presenterFactory) {
        super(context);
        this.presenterFactory = presenterFactory;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        presenter = presenterFactory.create();
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        super.onReset();
        presenter = null;
    }
}
