package com.sotan.mircea.shower.browse;

/**
 * @author mirceasotan
 */
public class NewReleasesFragment extends BaseNewReleasesFragment {

    public static NewReleasesFragment newInstance() {
        return new NewReleasesFragment();
    }

    @Override
    protected void load() {
        presenter.getNewReleases();
    }
}
