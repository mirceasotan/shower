package com.sotan.mircea.shower.presenter;

import com.sotan.mircea.shower.presenter.contracts.MVPView;

/**
 * @author mircea on 4/4/16
 */
public interface Presenter {
    void bind(MVPView view);

    void unbind();

    boolean isBound();
}
