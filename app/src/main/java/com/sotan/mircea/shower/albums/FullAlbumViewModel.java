package com.sotan.mircea.shower.albums;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.model.SimpleArtist;
import com.mircea.sotan.model.SimpleTrack;
import com.sotan.mircea.shower.viewModel.TrackViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mirceasotan
 */

public class FullAlbumViewModel extends SimpleAlbumViewModel {
    @SerializedName("tracks")
    private List<TrackViewModel> trackViewModels = new ArrayList<>();
    @SerializedName("artist")
    private String artist = "";
    @SerializedName("description")
    private String description = "";
    @SerializedName("copyright")
    private String copyright;

    public FullAlbumViewModel(@NonNull FullAlbum album) {
        super(album);

        buildTrackViewModels(album);
        buildArtist(album);
        buildDescription(album);
        this.copyright = album.getCopyrights().get(0).getText();
    }

    public List<TrackViewModel> getTrackViewModels() {
        return trackViewModels;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    private void buildTrackViewModels(@NonNull FullAlbum album) {
        trackViewModels = new ArrayList<>();

        for (SimpleTrack track : album.getTracks().getItems()) {
            trackViewModels.add(new TrackViewModel(track));
        }
    }

    private void buildArtist(@NonNull FullAlbum album) {
        StringBuilder builder = new StringBuilder();

        for (SimpleArtist artist : album.getArtists()) {
            if (!TextUtils.isEmpty(artist.getName())) {
                builder.append(artist.getName()).append(" \u2022");
            }
        }

        if (builder.subSequence(builder.length() - 2, builder.length()).equals(" \u2022")) {
            builder.replace(builder.length() - 2, builder.length(), "");
        }

        artist = builder.toString();
    }

    private void buildDescription(@NonNull FullAlbum album) {
        StringBuilder builder;
        builder = new StringBuilder();

        for (String genre : album.getGenres()) {
            if (!TextUtils.isEmpty(genre)) {
                builder.append(genre).append(" \u2022");
            }
        }

        if (!TextUtils.isEmpty(album.getAlbumType())) {
            builder.append(album.getAlbumType()).append(" \u2022");
        }

        if (!TextUtils.isEmpty(album.getReleaseDate())) {
            builder.append(" ").append(album.getReleaseDate());
        }

        if (builder.subSequence(builder.length() - 2, builder.length()).equals(" \u2022")) {
            builder.replace(builder.length() - 2, builder.length(), "");
        }

        description = builder.toString();
    }

    public String getCopyright() {
        return copyright;
    }
}
