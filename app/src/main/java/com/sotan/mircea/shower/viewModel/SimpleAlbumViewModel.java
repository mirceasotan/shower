package com.sotan.mircea.shower.viewModel;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.mircea.sotan.model.GsonObject;
import com.mircea.sotan.model.SimpleAlbum;

/**
 * @author mirceasotan
 */

public class SimpleAlbumViewModel extends GsonObject {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String albumName;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("color")
    private ColorViewModel colorViewModel;

    public SimpleAlbumViewModel(@NonNull SimpleAlbum album) {
        id = album.getId();
        albumName = album.getName();
        imageUrl = album.getImages().get(0).getUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ColorViewModel getColorViewModel() {
        return colorViewModel;
    }

    public void setColorViewModel(ColorViewModel colorViewModel) {
        this.colorViewModel = colorViewModel;
    }
}
