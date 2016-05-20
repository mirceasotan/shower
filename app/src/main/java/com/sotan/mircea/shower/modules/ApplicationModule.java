package com.sotan.mircea.shower.modules;

import android.content.Context;

import com.mircea.sotan.domain.GetUserUseCase;
import com.sotan.mircea.shower.ConfigurationManager;
import com.sotan.mircea.shower.logger.GTMLogger;
import com.sotan.mircea.shower.logger.Logger;
import com.sotan.mircea.shower.presenter.MyAccountFragmentPresenter;
import com.sotan.mircea.shower.presenter.MyAccountFragmentPresenterImpl;
import com.sotan.mircea.shower.presenter.NavigationActivityPresenterImpl;

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
    public NavigationActivityPresenterImpl providesNavigationActivityPresenter(ConfigurationManager manager) {
        return new NavigationActivityPresenterImpl(manager);
    }


    @Provides
    public MyAccountFragmentPresenter providesMyAccountFragmentPresenter(GetUserUseCase getUserUseCase) {
        return new MyAccountFragmentPresenterImpl(getUserUseCase);
    }

    @Singleton
    @Provides
    @Named("GTMLogger")
    public Logger provideLogger(Context context) {
        return new GTMLogger(context);
    }
}
