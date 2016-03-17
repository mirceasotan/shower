package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mircea
 */
public class BasePaging<T> extends GsonObject {
    @SerializedName("href")
    private String href;
    @SerializedName("items")
    private List<T> items;
    @SerializedName("limit")
    private int limit;
    @SerializedName("next")
    private int next;
    @SerializedName("total")
    private int total;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
