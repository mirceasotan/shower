package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mircea
 */
public class Cursor extends GsonObject {
    @SerializedName("after")
    private String after;

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}
