package com.sotan.mircea.shower.modules;

import com.sotan.mircea.shower.view.NavigationActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mircea
 */
@Singleton
@Component(modules = {AndroidModule.class, ApplicationModule.class, NetworkingModule.class})
public interface ApplicationComponent {
    void inject(NavigationActivity activity);
}
