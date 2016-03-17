package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by mircea
 */
public class FullTrack extends SimpleTrack {
    @SerializedName("album")
    private List<SimpleAlbum> albums;
    @SerializedName("external_ids")
    private Map<String, Integer> externalIds;
    @SerializedName("popularity")
    private int popularity;

    public List<SimpleAlbum> getAlbums() {
        return albums;
    }

    public void setAlbums(List<SimpleAlbum> albums) {
        this.albums = albums;
    }

    public Map<String, Integer> getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(Map<String, Integer> externalIds) {
        this.externalIds = externalIds;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
