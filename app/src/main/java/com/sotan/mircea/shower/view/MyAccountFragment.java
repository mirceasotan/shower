package com.sotan.mircea.shower.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mircea.sotan.model.PublicUser;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;
import com.sotan.mircea.shower.presenter.MyAccountFragmentPresenter;
import com.sotan.mircea.shower.presenter.contracts.MyAccountFragmentView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mircea
 */
public class MyAccountFragment extends Fragment implements MyAccountFragmentView {

    @Inject
    MyAccountFragmentPresenter presenter;
    @Bind(R.id.imageView)
    ImageView myAccountImageView;


    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShowerApp.getInjector().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_account_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter.bind(this);
        presenter.getUser();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showUser(PublicUser publicUser) {
        Picasso.with(getContext()).load(publicUser.getImages().get(0).getUrl()).into(myAccountImageView);

    }

    @Override
    public void showUserError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("error")
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null);
        builder.create().show();
    }
}
