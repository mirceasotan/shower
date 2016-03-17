package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mircea
 */
public class FullArtist extends SimpleArtist {
    @SerializedName("followers")
    private Followers followers;
    @SerializedName("genres")
    private List<String> genres;
    @SerializedName("images")
    private List<Image> images;
    @SerializedName("popularity")
    private int popularity;

    public Followers getFollowers() {
        return followers;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
