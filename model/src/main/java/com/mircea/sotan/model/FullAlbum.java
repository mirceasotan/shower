package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by mircea
 */
public class FullAlbum extends SimpleAlbum {
    @SerializedName("copyrights")
    private List<Copyright> copyrights;
    @SerializedName("external_ids")
    private Map<String, String> externalIds;
    @SerializedName("popularity")
    private int popularity;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("release_date_precision")
    private String releaseDatePrecision;
    @SerializedName("tracks")
    private BasePaging<SimpleTrack> tracks;

    public List<Copyright> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<Copyright> copyrights) {
        this.copyrights = copyrights;
    }

    public Map<String, String> getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(Map<String, String> externalIds) {
        this.externalIds = externalIds;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDatePrecision() {
        return releaseDatePrecision;
    }

    public void setReleaseDatePrecision(String releaseDatePrecision) {
        this.releaseDatePrecision = releaseDatePrecision;
    }

    public BasePaging<SimpleTrack> getTracks() {
        return tracks;
    }

    public void setTracks(BasePaging<SimpleTrack> tracks) {
        this.tracks = tracks;
    }
}
