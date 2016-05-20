package com.sotan.mircea.shower;

import android.app.Application;

import com.sotan.mircea.shower.logger.Logger;
import com.sotan.mircea.shower.modules.AndroidModule;
import com.sotan.mircea.shower.modules.ApplicationComponent;
import com.sotan.mircea.shower.modules.ApplicationModule;
import com.sotan.mircea.shower.modules.DaggerApplicationComponent;
import com.sotan.mircea.shower.modules.DomainModule;
import com.sotan.mircea.shower.modules.NetworkingModule;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by mircea
 */
public class ShowerApp extends Application {

    public static String TAG = "ShowerApp";

    private static ApplicationComponent injector;

    @Inject
    @Named("GTMLogger")
    public Logger gtmLogger;

    public static ApplicationComponent getInjector() {
        return injector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        getInjector().inject(this);
        gtmLogger.init();
    }

    private void initDagger() {
        injector = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .applicationModule(new ApplicationModule())
                .networkingModule(new NetworkingModule())
                .domainModule(new DomainModule())
                .build();
    }
}
