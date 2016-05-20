package com.sotan.mircea.shower.logger;

/**
 * Created by mirceasotan on 20/05/16.
 */
public interface Logger {
    /**
     *
     */
    void init();

    /**
     * @return
     */
    boolean isInitialized();

    /**
     * @param event
     */
    void log(Event event);
}
