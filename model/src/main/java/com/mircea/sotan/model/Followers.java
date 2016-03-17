package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mircea
 */
public class Followers extends GsonObject {
    @SerializedName("total")
    private int total;
    @SerializedName("href")
    private String href;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
