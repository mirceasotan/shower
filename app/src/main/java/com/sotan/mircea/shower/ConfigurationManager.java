package com.sotan.mircea.shower;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by mircea
 */
@Singleton
public class ConfigurationManager {

    @NonNull
    private Properties appProperties;

    @Inject
    public ConfigurationManager(@NonNull Context context) {
        appProperties = getAppConfigProperties(context);
    }

    @NonNull
    private Properties getAppConfigProperties(@NonNull Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.app_config);
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return p;
    }

    @Nullable
    public String getValueForKey(@NonNull String key) {
        return (String) appProperties.get(key);
    }

    @NonNull
    public Properties getAppProperties() {
        return appProperties;
    }
}


