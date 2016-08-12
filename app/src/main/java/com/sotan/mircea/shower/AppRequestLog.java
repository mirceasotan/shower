package com.sotan.mircea.shower;

import android.support.annotation.NonNull;

import com.mircea.sotan.repository.networking.RequestLog;

/**
 * @author mirceasotan
 */
public class AppRequestLog implements RequestLog {
    @Override
    public boolean isLoggingEnabled() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void log(@NonNull String message) {
        android.util.Log.d(ShowerApp.TAG, message);
    }
}
