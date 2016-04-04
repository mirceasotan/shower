package com.sotan.mircea.shower.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sotan.mircea.shower.R;

/**
 * Created by mircea
 */
public class MyAccountActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account_layout);

        if (savedInstanceState == null) {
            replaceFragment(MyAccountFragment.newInstance(), R.id.my_account_frame_layout);
        }
    }
}
