package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author mirceasotan
 */
public class Tracks extends BasePaging<SimpleTrack> {
    @SerializedName("tracks")
    private BasePaging<SimpleTrack> tracks;

    public BasePaging<SimpleTrack> getTracks() {
        return tracks;
    }
}
