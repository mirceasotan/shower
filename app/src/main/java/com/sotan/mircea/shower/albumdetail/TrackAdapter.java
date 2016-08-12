package com.sotan.mircea.shower.albumdetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.viewModel.TrackViewModel;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author mirceasotan
 */
class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {
    private final List<TrackViewModel> tracks;
    private int color;

    TrackAdapter(@NonNull Context context, @NonNull List<TrackViewModel> tracks, int textColor) {
        this.tracks = tracks;
        this.color = textColor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_row_layout, parent, false);
        return new ViewHolder(v, color);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrackViewModel simpleTrack = tracks.get(position);
        holder.trackNameTextView.setText(simpleTrack.getName());
        holder.trackNumberTextView.setText(simpleTrack.getNumber());

        if (simpleTrack.isExplicit()) {
            holder.trackExplicitImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView trackNameTextView;
        private TextView trackNumberTextView;
        private ImageView trackExplicitImageView;

        ViewHolder(View itemView, int color) {
            super(itemView);
            trackNameTextView = ButterKnife.findById(itemView, R.id.track_name_textView);
            trackNumberTextView = ButterKnife.findById(itemView, R.id.track_number_textView);
            trackExplicitImageView = ButterKnife.findById(itemView, R.id.track_explicit_imageView);
            trackNumberTextView.setTextColor(color);
            trackNameTextView.setTextColor(color);
        }
    }
}
