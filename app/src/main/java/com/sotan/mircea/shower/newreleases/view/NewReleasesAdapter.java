package com.sotan.mircea.shower.newreleases.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mircea.sotan.model.SimpleAlbum;
import com.sotan.mircea.shower.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author mirceasotan
 */
public class NewReleasesAdapter extends RecyclerView.Adapter<NewReleasesAdapter.ViewHolder> {
    @Nullable
    private final List<SimpleAlbum> albumList;
    @NonNull
    private final Context context;
    @NonNull
    private final Typeface type;

    public NewReleasesAdapter(@Nullable List<SimpleAlbum> albumList, @NonNull Context context) {
        this.albumList = albumList;
        this.context = context;
        this.type = Typeface.createFromAsset(context.getAssets(), "Roboto-MediumItalic.ttf");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_releases_tile_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.tileTitleTextView.setTypeface(type);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (albumList == null) {
            return;
        }

        SimpleAlbum album = albumList.get(position);

        holder.tileBar.setVisibility(View.VISIBLE);

        Picasso.with(context).load(album.getImages().get(0).getUrl()).into(holder.tileImageView, new Callback() {
            @Override
            public void onSuccess() {
                if (holder.tileBar != null) {
                    holder.tileBar.setVisibility(View.GONE);
                }

                Bitmap bitmap = ((BitmapDrawable) holder.tileImageView.getDrawable()).getBitmap();

                Palette.PaletteAsyncListener paletteListener = new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        // access palette colors here
                        holder.tileTitleTextView.setBackgroundColor(palette.getVibrantColor(0x000000));

                        Palette.Swatch swatch = palette.getVibrantSwatch();

                        if (swatch != null) {
                            holder.tileTitleTextView.setTextColor(swatch.getTitleTextColor());
                        } else {
                            holder.tileTitleTextView.setTextColor(Color.BLACK);
                        }
                    }
                };


                Palette.from(bitmap).generate(paletteListener);
            }

            @Override
            public void onError() {
                holder.tileImageView.setImageResource(R.drawable.placeholder);

                if (holder.tileBar != null) {
                    holder.tileBar.setVisibility(View.GONE);
                }
            }
        });

        holder.tileTitleTextView.setText(album.getName());
    }

    @Override
    public int getItemCount() {
        return albumList == null ? 0 : albumList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView tileImageView;
        public TextView tileTitleTextView;
        public ProgressBar tileBar;

        public ViewHolder(View t) {
            super(t);
            tileImageView = ButterKnife.findById(t, R.id.tile_imageView);
            tileTitleTextView = ButterKnife.findById(t, R.id.tile_titleTextView);
            tileBar = ButterKnife.findById(t, R.id.tile_imageSpinner);
        }
    }
}
