package com.sotan.mircea.shower.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mircea
 */
@Module
public class AndroidModule {

    private Context context;

    public AndroidModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    @Named("token")
    public SharedPreferences provideTokenPreferences(Context context) {
        return context.getSharedPreferences("token", Context.MODE_PRIVATE);
    }

    @Provides
    @Named("user")
    public SharedPreferences provideUserPreferences(Context context) {
        return context.getSharedPreferences("user", Context.MODE_PRIVATE);
    }
}
