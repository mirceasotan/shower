package com.sotan.mircea.shower.presenter;

/**
 * @author mircea on 4/4/16
 */
public interface Presenter<V> {
    /**
     * Bounds  presenter to a view in order to send events for updating the UI
     *
     * @param view
     */
    void bind(V view);

    /**
     * Releases this presenter from it's view
     */
    void unbind();

    /**
     * Checks is presenter is bound to a view
     *
     * @return True if presenter is bound, false otherwise
     */
    boolean isBound();
}
