package com.sotan.mircea.shower.browse;

import com.sotan.mircea.shower.ErrorAction;

import rx.android.schedulers.AndroidSchedulers;


/**
 * @author mirceasotan
 */
public class RxNewReleasesFragment extends BaseNewReleasesFragment {

    public static RxNewReleasesFragment newInstance() {
        return new RxNewReleasesFragment();
    }

    @Override
    protected void load() {
        presenter.getRxNewReleases().observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showNewReleases, new ErrorAction() {
                    @Override
                    public void onConnectionError() {
                        showNewReleasesNoConnectionError();
                    }

                    @Override
                    public void onApiError() {
                        showNewReleasesApiError();
                    }
                });
    }
}
