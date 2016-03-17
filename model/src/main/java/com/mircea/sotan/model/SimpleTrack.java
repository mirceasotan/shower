package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by mircea
 */
public class SimpleTrack extends GsonObject {
    @SerializedName("artists")
    private List<SimpleArtist> artists;
    @SerializedName("available_markets")
    private List<String> availableMarkets;
    @SerializedName("disc_number")
    private int discNumber;
    @SerializedName("duration_ms")
    private int duration;
    @SerializedName("explicit")
    private boolean explicit;
    @SerializedName("external_urls")
    private Map<String, String> externalUrls;
    @SerializedName("href")
    private String href;
    @SerializedName("id")
    private String id;
    @SerializedName("is_playable")
    private boolean playable;
    @SerializedName("linked_from")
    private TrackLink trackLink;
    @SerializedName("name")
    private String name;
    @SerializedName("preview_url")
    private String previewUrl;
    @SerializedName("track_number")
    private int trackNumber;
    @SerializedName("type")
    private String type;
    @SerializedName("uri")
    private String uri;

    public List<SimpleArtist> getArtists() {
        return artists;
    }

    public void setArtists(List<SimpleArtist> artists) {
        this.artists = artists;
    }

    public List<String> getAvailableMarkets() {
        return availableMarkets;
    }

    public void setAvailableMarkets(List<String> availableMarkets) {
        this.availableMarkets = availableMarkets;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(int discNumber) {
        this.discNumber = discNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

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

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public TrackLink getTrackLink() {
        return trackLink;
    }

    public void setTrackLink(TrackLink trackLink) {
        this.trackLink = trackLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
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
