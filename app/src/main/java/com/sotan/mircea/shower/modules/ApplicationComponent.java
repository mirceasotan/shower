package com.sotan.mircea.shower.modules;

import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.me.MyAccountActivity;
import com.sotan.mircea.shower.me.MyAccountFragment;
import com.sotan.mircea.shower.navigation.NavigationActivity;
import com.sotan.mircea.shower.newreleases.view.NewReleasesFragment;
import com.sotan.mircea.shower.view.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mircea
 */
@Singleton
@Component(modules = {AndroidModule.class, ApplicationModule.class, NetworkingModule.class, DomainModule.class})
public interface ApplicationComponent {
    void inject(NavigationActivity activity);

    void inject(MyAccountFragment fragment);

    void inject(ShowerApp showerApp);

    void inject(MyAccountActivity myAccountActivity);

    void inject(SplashActivity splashActivity);

    void inject(NewReleasesFragment newReleasesFragment);


}
