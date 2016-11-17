package com.sotan.mircea.shower.browse;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.albums.SimpleAlbumViewModel;
import com.sotan.mircea.shower.misc.RecyclerItemClickListener;
import com.sotan.mircea.shower.viewModel.ColorViewModel;
import com.sotan.mircea.shower.widget.GradientSquareImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author mirceasotan
 */
class NewReleasesAdapter extends RecyclerView.Adapter<NewReleasesAdapter.ViewHolder> {
    @Nullable
    private final List<SimpleAlbumViewModel> albumList;
    @NonNull
    private final Context context;
    private RecyclerItemClickListener listener;
    private Drawable placeHolderDrawable = new ColorDrawable(Color.TRANSPARENT);

    NewReleasesAdapter(@Nullable List<SimpleAlbumViewModel> albumList, @NonNull Context context,
                       @Nullable RecyclerItemClickListener listener) {
        this.albumList = albumList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_releases_tile_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (albumList == null) {
            return;
        }

        if (holder.position == holder.getAdapterPosition()) {
            return;
        } else {
            holder.position = holder.getAdapterPosition();
        }

        final SimpleAlbumViewModel album = albumList.get(position);

        holder.parentView.setOnClickListener(v -> listener.onItemClick(album, v));

        holder.tileProgressBar.setVisibility(View.VISIBLE);
        holder.tileImageView.setImageDrawable(placeHolderDrawable);
        holder.tileImageView.setCallback(new GradientSquareImageView.Callback() {
            @Override
            public void onImageAvailable() {
                holder.tileProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onColorsAvailable(ColorViewModel colorViewModel) {
                album.setColorViewModel(colorViewModel);
                holder.parentView.setBackgroundColor(colorViewModel.getBackgroundColor());
                holder.tileTitleTextView.setTextColor(colorViewModel.getTextColor());
            }
        });

        Picasso.with(context).load(album.getImageUrl()).into((Target) holder.tileImageView);

        holder.tileTitleTextView.setText(album.getName());
    }

    @Override
    public int getItemCount() {
        return albumList == null ? 0 : albumList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        GradientSquareImageView tileImageView;
        TextView tileTitleTextView;
        ProgressBar tileProgressBar;
        View parentView;
        int position = -1;

        ViewHolder(View t) {
            super(t);
            tileImageView = ButterKnife.findById(t, R.id.tile_imageView);
            tileTitleTextView = ButterKnife.findById(t, R.id.tile_titleTextView);
            tileProgressBar = ButterKnife.findById(t, R.id.tile_imageSpinner);
            parentView = t;
        }
    }

    @Nullable
    List<SimpleAlbumViewModel> getAlbumList() {
        return albumList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
