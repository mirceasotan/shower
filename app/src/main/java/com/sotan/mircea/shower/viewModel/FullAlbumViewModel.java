package com.sotan.mircea.shower.viewModel;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.model.SimpleTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mirceasotan
 */

public class FullAlbumViewModel extends SimpleAlbumViewModel {
    @SerializedName("tracks")
    private List<TrackViewModel> trackViewModels;

    public FullAlbumViewModel(@NonNull FullAlbum album) {
        super(album);

        trackViewModels = new ArrayList<>();

        for (SimpleTrack track : album.getTracks().getItems()) {
            trackViewModels.add(new TrackViewModel(track));
        }
    }

    public List<TrackViewModel> getTrackViewModels() {
        return trackViewModels;
    }
}
