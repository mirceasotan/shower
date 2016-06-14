package com.mircea.sotan.repository.networking;

import android.support.annotation.NonNull;

/**
 * @author mirceasotan
 */
public interface Log {

    boolean isLoggingEnabled();

    void log(@NonNull String message);
}
