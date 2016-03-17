package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;


/**
 * Created by mircea
 */
public class PublicUser extends GsonObject {
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("external_urls")
    private Map<String, String> externalUrls;
    @SerializedName("followers")
    private Followers followers;
    @SerializedName("href")
    private String href;
    @SerializedName("id")
    private String id;
    @SerializedName("images")
    private List<Image> images;
    @SerializedName("type")
    private String type;
    @SerializedName("uri")
    private String uri;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Map<String, String> getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(Map<String, String> externalUrls) {
        this.externalUrls = externalUrls;
    }

    public Followers getFollowers() {
        return followers;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
