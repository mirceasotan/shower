package com.sotan.mircea.shower.modules;

import android.content.SharedPreferences;

import com.mircea.sotan.repository.apis.RetrofitUserRestApi;
import com.mircea.sotan.repository.apis.UserRestApi;
import com.sotan.mircea.shower.ConfigConstants;
import com.sotan.mircea.shower.ConfigurationManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mircea
 */
@Module
public class NetworkingModule {

    @Provides
    public UserRestApi provideRetrofitUserRestApi(ConfigurationManager configurationManager, @Named("token") SharedPreferences preferences) {
        return new RetrofitUserRestApi(preferences.getString("token", ""), configurationManager.getValueForKey(ConfigConstants.BASE_URI));
    }

}
