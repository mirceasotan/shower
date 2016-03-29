package com.sotan.mircea.shower.view;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mircea
 */
public class BaseActivity extends AppCompatActivity {

    protected void replaceFragment(@NonNull Fragment fragment, @IdRes int containerId) {
        replaceFragment(fragment, containerId, false);
    }

    protected void replaceFragment(@NonNull Fragment fragment, @IdRes int containerId, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

}
