package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mircea
 */
public class Category extends GsonObject {
    @SerializedName("href")
    private String href;
    @SerializedName("icons")
    private List<Image> icons;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Image> getIcons() {
        return icons;
    }

    public void setIcons(List<Image> icons) {
        this.icons = icons;
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

    public void setName(String name) {
        this.name = name;
    }
}
