package com.sotan.mircea.shower;

import android.support.annotation.NonNull;

import com.mircea.sotan.repository.networking.Log;

/**
 * @author mirceasotan
 */
public class AppLog implements Log {
    @Override
    public boolean isLoggingEnabled() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void log(@NonNull String message) {
        android.util.Log.d(ShowerApp.TAG, message);
    }
}
