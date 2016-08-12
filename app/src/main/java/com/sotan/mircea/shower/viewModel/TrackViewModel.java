package com.sotan.mircea.shower.viewModel;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.mircea.sotan.model.GsonObject;
import com.mircea.sotan.model.SimpleTrack;

/**
 * @author mirceasotan
 */

public class TrackViewModel extends GsonObject {
    @SerializedName("explicit")
    private boolean explicit;
    @SerializedName("number")
    private String number;
    @SerializedName("name")
    private String name;

    public TrackViewModel(@NonNull SimpleTrack track) {
        explicit = track.isExplicit();
        number = track.getTrackNumber() + ".";
        name = track.getName();
    }

    public boolean isExplicit() {
        return explicit;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
