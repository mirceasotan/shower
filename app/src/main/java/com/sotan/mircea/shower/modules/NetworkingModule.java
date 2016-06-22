package com.sotan.mircea.shower.modules;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mircea.sotan.repository.apis.AlbumsRestApi;
import com.mircea.sotan.repository.apis.AlbumsRestApiImpl;
import com.mircea.sotan.repository.apis.BrowseRestApi;
import com.mircea.sotan.repository.apis.BrowseRestApiImpl;
import com.mircea.sotan.repository.apis.UserRestApi;
import com.mircea.sotan.repository.apis.UserRestApiImpl;
import com.sotan.mircea.shower.AppLog;
import com.sotan.mircea.shower.ConfigConstants;
import com.sotan.mircea.shower.ConfigurationManager;
import com.sotan.mircea.shower.ShowerApp;

import java.io.IOException;
import java.util.Set;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mircea
 */
@Module
public class NetworkingModule {

    @Provides
    public UserRestApi provideRetrofitUserRestApi(@NonNull Retrofit retrofit,
                                                  com.mircea.sotan.repository.networking.Log log) {
        return new UserRestApiImpl(retrofit, log);
    }

    @Provides
    public BrowseRestApi provideRetrofitBrowseRestApi(@NonNull Retrofit retrofit,
                                                      com.mircea.sotan.repository.networking.Log log) {
        return new BrowseRestApiImpl(retrofit, log);
    }

    @Provides
    public AlbumsRestApi provideRetrofitAlbumsRestApi(@NonNull Retrofit retrofit,
                                                      com.mircea.sotan.repository.networking.Log log) {
        return new AlbumsRestApiImpl(retrofit, log);
    }

    @Singleton
    @Provides
    public OkHttpClient provideOKHttpClient(@Named("token") final SharedPreferences preferences) {
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                return processRequest(chain, preferences);
                            }
                        }).build();
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

    private Response processRequest(Interceptor.Chain chain, @Named("token") SharedPreferences preferences)
            throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer {token}".replace("{token}",
                        preferences.getString("token", "")))
                .build();

        Headers headers = request.headers();
        Set<String> names = headers.names();

        for (String name : names) {
            String value = headers.get(name);
            Log.d(ShowerApp.TAG, name + " : " + value);
        }

        return chain.proceed(request);
    }

    @Provides
    com.mircea.sotan.repository.networking.Log provideLog() {
        return new AppLog();
    }

}
