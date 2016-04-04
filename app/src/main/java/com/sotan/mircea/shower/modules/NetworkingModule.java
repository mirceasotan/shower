package com.sotan.mircea.shower.modules;

import android.content.SharedPreferences;

import com.mircea.sotan.repository.apis.OKHttpUserRestApi;
import com.mircea.sotan.repository.apis.RetrofitUserRestApi;
import com.mircea.sotan.repository.apis.UserRestApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mircea
 */
@Module
public class NetworkingModule {

    @Provides
    @Singleton
    @Named("retrofit")
    UserRestApi provideRetrofitUserRestApi(@Named("token") SharedPreferences preferences) {
        return new RetrofitUserRestApi(preferences.getString("", ""));
    }

    @Provides
    @Singleton
    @Named("okHttp")
    UserRestApi provideOKHttpUserRestApi() {
        return new OKHttpUserRestApi();
    }

}
