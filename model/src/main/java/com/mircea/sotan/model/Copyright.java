package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mircea
 */
public class Copyright extends GsonObject{
    @SerializedName("text")
    private String text;
    @SerializedName("type")
    private String type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
