package com.mircea.sotan.repository.networking;

import android.support.annotation.NonNull;

/**
 * @author mirceasotan
 */
public interface RequestLog {

    /**
     * Indicates if logging is enabled. Usually used for enabling or disabling console logging
     *
     * @return True if logging is enabled, false otherwise
     */
    boolean isLoggingEnabled();

    /**
     * Logs the given message. Usually used for console logging
     *
     * @param message String value to be logged
     */
    void log(@NonNull String message);
}
