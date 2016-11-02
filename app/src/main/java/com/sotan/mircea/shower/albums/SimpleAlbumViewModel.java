package com.sotan.mircea.shower.albums;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.mircea.sotan.model.GsonObject;
import com.mircea.sotan.model.SimpleAlbum;
import com.sotan.mircea.shower.viewModel.ColorViewModel;

/**
 * @author mirceasotan
 */

public class SimpleAlbumViewModel extends GsonObject {
    @SerializedName("id")
    private String id = "";
    @SerializedName("name")
    private String name = "Unknown";
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("color")
    private ColorViewModel colorViewModel;


    public SimpleAlbumViewModel(@NonNull SimpleAlbum album) {
        id = album.getId();
        name = album.getName();
        imageUrl = album.getImages().get(0).getUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
