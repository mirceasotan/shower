package com.sotan.mircea.shower;

import android.content.SharedPreferences;

import com.mircea.sotan.repository.networking.TokenStorage;

/**
 * @author mirceasotan
 */
public class AppTokenStorage implements TokenStorage {
    private final SharedPreferences preferences;

    public AppTokenStorage(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public String getAuthToken() {
        return preferences.getString("token", "");
    }
}
