package com.sotan.mircea.shower.view;

import android.support.v4.app.Fragment;

import com.sotan.mircea.shower.presenter.Presenter;

/**
 * @author mirceasotan
 */
public abstract class BaseFragment extends Fragment {

    public abstract  Presenter getPresenter();

    public abstract Object getMvpView();

    @Override
    public void onResume() {
        super.onResume();

        if (getPresenter() != null) {
            getPresenter().bind(getMvpView());
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (getPresenter() != null && getPresenter().isBound()) {
            getPresenter().unbind();
        }
    }
}
