package com.sotan.mircea.shower.modules;

import android.content.Context;

import com.mircea.sotan.domain.albums.GetAlbumDetailsUseCase;
import com.mircea.sotan.domain.browse.GetNewReleasesUseCase;
import com.mircea.sotan.domain.profiles.GetUserUseCase;
import com.sotan.mircea.shower.ConfigurationManager;
import com.sotan.mircea.shower.albums.AlbumDetailContract;
import com.sotan.mircea.shower.albums.AlbumDetailPresenterImpl;
import com.sotan.mircea.shower.browse.NewReleasesContract;
import com.sotan.mircea.shower.logger.GTMLogger;
import com.sotan.mircea.shower.logger.Logger;
import com.sotan.mircea.shower.me.MyAccountFragmentPresenter;
import com.sotan.mircea.shower.me.MyAccountFragmentPresenterImpl;
import com.sotan.mircea.shower.navigation.NavigationActivityPresenter;
import com.sotan.mircea.shower.navigation.NavigationActivityPresenterImpl;
import com.sotan.mircea.shower.browse.NewReleasesPresenterImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mircea
 */
@Module
public class ApplicationModule {

    @Singleton
    @Provides
    public ConfigurationManager providesConfigurationManager(Context context) {
        return new ConfigurationManager(context);
    }

    @Provides
    public NavigationActivityPresenter providesNavigationActivityPresenter(ConfigurationManager manager) {
        return new NavigationActivityPresenterImpl(manager);
    }

    @Provides
    public MyAccountFragmentPresenter providesMyAccountFragmentPresenter(GetUserUseCase getUserUseCase) {
        return new MyAccountFragmentPresenterImpl(getUserUseCase);
    }

    @Provides
    public NewReleasesContract.NewReleasesPresenter provideNewReleasePresenter(GetNewReleasesUseCase useCase) {
        return new NewReleasesPresenterImpl(useCase);
    }

    @Provides
    public AlbumDetailContract.AlbumDetailPresenter provideAlbumDetailsPresenter(GetAlbumDetailsUseCase useCase) {
        return new AlbumDetailPresenterImpl(useCase);
    }


    @Singleton
    @Provides
    @Named("GTMLogger")
    public Logger provideLogger(Context context) {
        return new GTMLogger(context, "GTM-KLQ6C4");
    }
}
