package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author mirceasotan
 */
public class SimpleAlbums extends GsonObject {
    @SerializedName("href")
    private String href;
    @SerializedName("items")
    private List<SimpleAlbum> simpleAlbumList;

    public String getHref() {
        return href;
    }

    public List<SimpleAlbum> getSimpleAlbumList() {
        return simpleAlbumList;
    }
}
