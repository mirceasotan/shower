package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by mircea
 */
public class TrackLink extends GsonObject {
    @SerializedName("external_urls")
    private Map<String, String> externalUrls;
    @SerializedName("href")
    private String href;
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("uri")
    private String uri;

    public Map<String, String> getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(Map<String, String> externalUrls) {
        this.externalUrls = externalUrls;
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
