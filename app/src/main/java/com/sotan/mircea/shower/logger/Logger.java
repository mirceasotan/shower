package com.sotan.mircea.shower.logger;

/**
 * Created by mirceasotan
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
