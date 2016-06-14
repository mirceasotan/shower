package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author mirceasotan
 */
public class NewReleases extends GsonObject {
    @SerializedName("albums")
    private SimpleAlbums simpleAlbums;

    public SimpleAlbums getSimpleAlbums() {
        return simpleAlbums;
    }
}
