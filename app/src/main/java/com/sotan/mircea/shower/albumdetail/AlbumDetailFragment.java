package com.sotan.mircea.shower.albumdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mircea.sotan.model.SimpleAlbum;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.presenter.Presenter;
import com.sotan.mircea.shower.view.BaseFragment;

/**
 * @author mirceasotan
 */
public class AlbumDetailFragment extends BaseFragment {

    public static final String ALBUM_KEY = "album";

    private SimpleAlbum simpleAlbum;

    public static AlbumDetailFragment newInstance(SimpleAlbum simpleAlbum) {
        return newInstance(simpleAlbum.toJson());
    }

    public static AlbumDetailFragment newInstance(String simpleAlbum) {
        AlbumDetailFragment albumDetailFragment = new AlbumDetailFragment();
        Bundle b = new Bundle();
        b.putString(ALBUM_KEY, simpleAlbum);
        albumDetailFragment.setArguments(b);
        return albumDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b != null && b.getString(ALBUM_KEY) != null) {
            simpleAlbum = SimpleAlbum.fromJson(b.getString(ALBUM_KEY), SimpleAlbum.class);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.album_detail_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

    @Override
    public Object getMvpView() {
        return null;
    }
}
