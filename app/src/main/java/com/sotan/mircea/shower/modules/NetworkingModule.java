package com.sotan.mircea.shower.modules;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.mircea.sotan.repository.apis.AlbumsRestApi;
import com.mircea.sotan.repository.apis.AlbumsRestApiImpl;
import com.mircea.sotan.repository.apis.BrowseRestApi;
import com.mircea.sotan.repository.apis.BrowseRestApiImpl;
import com.mircea.sotan.repository.apis.UserRestApi;
import com.mircea.sotan.repository.apis.UserRestApiImpl;
import com.mircea.sotan.repository.networking.RequestLog;
import com.mircea.sotan.repository.networking.TokenStorage;
import com.mircea.sotan.repository.services.AlbumsService;
import com.mircea.sotan.repository.services.BrowseService;
import com.mircea.sotan.repository.services.UserService;
import com.sotan.mircea.shower.AppRequestLog;
import com.sotan.mircea.shower.AppTokenStorage;
import com.sotan.mircea.shower.ConfigConstants;
import com.sotan.mircea.shower.ConfigurationManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author mirceasotan
 */
@Module
public class NetworkingModule {

    @Provides
    public UserRestApi provideRetrofitUserRestApi(@NonNull UserService service,
                                                  @NonNull RequestLog log, TokenStorage storage) {
        return new UserRestApiImpl(service, log, storage);
    }

    @Provides
    public BrowseRestApi provideRetrofitBrowseRestApi(@NonNull BrowseService service,
                                                      @NonNull RequestLog log, TokenStorage storage) {
        return new BrowseRestApiImpl(service, log, storage);
    }

    @Provides
    public AlbumsRestApi provideRetrofitAlbumsRestApi(@NonNull AlbumsService service,
                                                      @NonNull RequestLog requestLog, TokenStorage storage) {
        return new AlbumsRestApiImpl(service, requestLog, storage);
    }

    @Provides
    public AlbumsService provideAlbumsService(@NonNull Retrofit retrofit) {
        return retrofit.create(AlbumsService.class);
    }

    @Provides
    public BrowseService provideBrowseService(@NonNull Retrofit retrofit) {
        return retrofit.create(BrowseService.class);
    }

    @Provides
    public UserService provideUserService(@NonNull Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    @Provides
    public OkHttpClient provideOKHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(@NonNull ConfigurationManager manager, @NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(manager.getValueForKey(ConfigConstants.BASE_URI))
                .client(client)
                .build();

    }


    @Provides
    public RequestLog provideLog() {
        return new AppRequestLog();
    }

    @Provides
    public TokenStorage provideTokenStorage(@Named("token") SharedPreferences preferences) {
        return new AppTokenStorage(preferences);
    }

}
