package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mircea
 */
public class FullAlbum extends SimpleAlbum {
    @SerializedName("copyrights")
    private List<Copyright> copyrights = new ArrayList<>();
    @SerializedName("external_ids")
    private Map<String, String> externalIds = new HashMap<>();
    @SerializedName("popularity")
    private int popularity;
    @SerializedName("release_date")
    private String releaseDate = "";
    @SerializedName("release_date_precision")
    private String releaseDatePrecision = "";
    @SerializedName("tracks")
    private BasePaging<SimpleTrack> tracks = new BasePaging<>();
    @SerializedName("artists")
    private List<SimpleArtist> artists = new ArrayList<>();
    @SerializedName("genres")
    private List<String> genres = new ArrayList<>();

    public List<Copyright> getCopyrights() {
        return copyrights;
    }

    public Map<String, String> getExternalIds() {
        return externalIds;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getReleaseDatePrecision() {
        return releaseDatePrecision;
    }

    public BasePaging<SimpleTrack> getTracks() {
        return tracks;
    }


    public List<SimpleArtist> getArtists() {
        return artists;
    }

    public List<String> getGenres() {
        return genres;
    }
}
