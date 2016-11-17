package com.sotan.mircea.shower.albums;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.view.DataBinder;
import com.sotan.mircea.shower.viewModel.TrackViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @author mirceasotan
 */

public class SimpleTrackBinder implements DataBinder<SimpleTrackBinder.TrackViewHolder> {

    private final List<TrackViewModel> data = new ArrayList<>();
    private int textColor;

    public SimpleTrackBinder(List<TrackViewModel> data, int textColor) {
        this.data.addAll(data);
        this.textColor = textColor;
    }

    @Override
    public TrackViewHolder onCreateNewViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_row_layout, parent, false);

        return new TrackViewHolder(v, textColor);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        TrackViewModel simpleTrack = data.get(position);

        holder.nameTextView.setText(simpleTrack.getName());
        holder.numberTextView.setText(simpleTrack.getNumber());

        if (simpleTrack.isExplicit()) {
            holder.explicitImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override public int getItemCount() {
        return data.size();
    }

    static class TrackViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView numberTextView;
        private ImageView explicitImageView;

        TrackViewHolder(View itemView, int color) {
            super(itemView);
            nameTextView = ButterKnife.findById(itemView, R.id.track_name_textView);
            numberTextView = ButterKnife.findById(itemView, R.id.track_number_textView);
            explicitImageView = ButterKnife.findById(itemView, R.id.track_explicit_imageView);
            numberTextView.setTextColor(color);
            nameTextView.setTextColor(color);
        }
    }
}
