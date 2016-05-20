package com.sotan.mircea.shower.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.logger.GAEvent;
import com.sotan.mircea.shower.logger.Logger;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by mircea
 */
public class MyAccountActivity extends BaseActivity {
    @Inject
    @Named("GTMLogger")
    public Logger gtmLogger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account_layout);

        ShowerApp.getInjector().inject(this);

        gtmLogger.log(new GAEvent("My Account"));

        if (savedInstanceState == null) {
            replaceFragment(MyAccountFragment.newInstance(), R.id.my_account_frame_layout);
        }
    }
}
