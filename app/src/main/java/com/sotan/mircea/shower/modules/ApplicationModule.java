package com.sotan.mircea.shower.modules;

import android.content.Context;

import com.mircea.sotan.domain.GetNewReleasesUseCase;
import com.mircea.sotan.domain.GetNewReleasesUseCaseImpl;
import com.mircea.sotan.domain.GetUserUseCase;
import com.mircea.sotan.repository.apis.BrowseRestApi;
import com.sotan.mircea.shower.ConfigurationManager;
import com.sotan.mircea.shower.logger.GTMLogger;
import com.sotan.mircea.shower.logger.Logger;
import com.sotan.mircea.shower.me.MyAccountFragmentPresenter;
import com.sotan.mircea.shower.me.MyAccountFragmentPresenterImpl;
import com.sotan.mircea.shower.navigation.NavigationActivityPresenter;
import com.sotan.mircea.shower.navigation.NavigationActivityPresenterImpl;
import com.sotan.mircea.shower.newreleases.presenter.NewReleasesPresenter;
import com.sotan.mircea.shower.newreleases.presenter.NewReleasesPresenterImpl;

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
    public NewReleasesPresenter provideNewReleasePresenter(GetNewReleasesUseCase useCase) {
        return new NewReleasesPresenterImpl(useCase);
    }

    @Provides
    public GetNewReleasesUseCase providesGetNewReleaseUseCase(BrowseRestApi restApi) {
        return new GetNewReleasesUseCaseImpl(restApi);
    }

    @Singleton
    @Provides
    @Named("GTMLogger")
    public Logger provideLogger(Context context) {
        return new GTMLogger(context);
    }
}
