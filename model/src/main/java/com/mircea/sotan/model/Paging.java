package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;


/**
 * Created by mircea
 */
public class Paging extends BasePaging {
    @SerializedName("offset")
    private int offset;
    @SerializedName("prev")
    private int prev;


    public int getOffset() {
        return offset;
    }

    public int getPrev() {
        return prev;
    }
}
