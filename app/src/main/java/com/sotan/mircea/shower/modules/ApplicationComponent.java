package com.sotan.mircea.shower.modules;

import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.view.MyAccountActivity;
import com.sotan.mircea.shower.view.MyAccountFragment;
import com.sotan.mircea.shower.view.NavigationActivity;

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
}
