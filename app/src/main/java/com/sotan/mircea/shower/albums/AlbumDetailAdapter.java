package com.sotan.mircea.shower.albums;

import android.support.annotation.NonNull;

import com.sotan.mircea.shower.view.MapDataBinderAdapter;
import com.sotan.mircea.shower.viewModel.TrackViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mirceasotan
 */
class AlbumDetailAdapter extends MapDataBinderAdapter<AlbumDetailAdapter.Types> {

    enum Types {
        TRACK,
        COPYRIGHT
    }

    private AlbumDetailAdapter(@NonNull List<TrackViewModel> tracks, @NonNull String copyright, int textColor) {
        putBinder(Types.TRACK, new SimpleTrackBinder(tracks, textColor));
        putBinder(Types.COPYRIGHT, new CopyrightBinder(copyright, textColor));
    }

    @Override
    public Types getEnumFromPosition(int position) {
        if (position == getItemCount() - 1) {
            return Types.COPYRIGHT;
        } else {
            return Types.TRACK;
        }
    }

    @Override
    public Types getEnumFromOrdinal(int ordinal) {
        return Types.values()[ordinal];
    }

    public static class Builder {
        private List<TrackViewModel> trackViewModels = new ArrayList<>();
        private String copyright;
        private int textColor;

        AlbumDetailAdapter.Builder withTracks(@NonNull List<TrackViewModel> trackViewModels) {
            this.trackViewModels = trackViewModels;
            return this;
        }

        AlbumDetailAdapter.Builder withCopyRight(@NonNull String copyright) {
            this.copyright = copyright;
            return this;
        }

        AlbumDetailAdapter.Builder withTextColor(int color) {
            this.textColor = color;
            return this;
        }

        public AlbumDetailAdapter build() {
            return new AlbumDetailAdapter(trackViewModels, copyright, textColor);
        }
    }
}
