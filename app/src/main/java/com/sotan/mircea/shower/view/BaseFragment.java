package com.sotan.mircea.shower.view;

import android.support.v4.app.Fragment;

import com.sotan.mircea.shower.presenter.Presenter;

/**
 * @author mirceasotan
 */
public abstract class BaseFragment<T extends Presenter<K>, K> extends Fragment {

    public abstract T getPresenter();

    public abstract K getMvpView();

    @Override
    public void onResume() {
        super.onResume();

        if (getPresenter() != null) {
            getPresenter().bind(getMvpView());
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (getPresenter() != null && getPresenter().isBound()) {
            getPresenter().unbind();
        }
    }
}
