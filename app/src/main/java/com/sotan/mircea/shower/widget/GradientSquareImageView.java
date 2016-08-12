package com.sotan.mircea.shower.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;

import com.sotan.mircea.shower.viewModel.ColorViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * @author mirceasotan
 */

public class GradientSquareImageView extends SquareImageView implements Target {

    private Callback callback;

    public interface Callback {
        void onImageAvailable();

        void onColorsAvailable(ColorViewModel colorViewModel);
    }

    public GradientSquareImageView(Context context) {
        super(context);
    }

    public GradientSquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientSquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GradientSquareImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
        if (callback != null) {
            callback.onImageAvailable();
        }

        Palette.PaletteAsyncListener paletteListener = new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int colorMain = palette.getDarkMutedColor(Color.WHITE);

                Drawable[] layers = new Drawable[2];
                GradientDrawable g = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                        new int[]{colorMain, Color.TRANSPARENT});
                BitmapDrawable d = new BitmapDrawable(getResources(), bitmap);

                layers[0] = d;
                layers[1] = g;

                setImageDrawable(new LayerDrawable(layers));

                Palette.Swatch swatch = palette.getDarkMutedSwatch();

                int textColor = Color.BLACK;

                if (swatch != null) {
                    textColor = swatch.getTitleTextColor();
                }

                if (callback != null) {
                    callback.onColorsAvailable(new ColorViewModel(colorMain, textColor));
                }
            }
        };


        Palette.from(bitmap).generate(paletteListener);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
