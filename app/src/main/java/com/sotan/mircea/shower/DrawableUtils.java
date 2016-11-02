package com.sotan.mircea.shower;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.graphics.Palette;

import com.sotan.mircea.shower.viewModel.ColorViewModel;

/**
 * @author mirceasotan
 */

public class DrawableUtils {
    public static GradientDrawable createGradientDrawableForColor(int color) {
        return new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                new int[]{color, Color.TRANSPARENT});
    }

    public static LayerDrawable createLayerDrawable(Drawable d1, Drawable d2) {
        Drawable[] drawables = new Drawable[2];
        drawables[0] = d1;
        drawables[1] = d2;
        return new LayerDrawable(drawables);
    }

    public static ShapeDrawable createDividerShapeDrawable() {
        RectShape s = new RectShape();
        ShapeDrawable shapeDrawable = new ShapeDrawable(s);
        shapeDrawable.getPaint().setColor(0x657755);
        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
        shapeDrawable.getPaint().setStrokeWidth(1);
        shapeDrawable.getPaint();
        return shapeDrawable;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static RippleDrawable createRippleDrawable(int normalColor, int pressedColor) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{android.R.attr.state_activated},
                new int[]{}
        };

        int[] colors = new int[]{
                pressedColor,
                pressedColor,
                pressedColor,
                normalColor
        };


        ColorStateList colorStateList = new ColorStateList(states, colors);

        return new RippleDrawable(colorStateList, getColorDrawableFromColor(normalColor), null);
    }

    public static ColorDrawable getColorDrawableFromColor(int color) {
        return new ColorDrawable(color);
    }

    public static ColorViewModel createFromPalette(@Nullable Palette palette) {
        if (palette == null) {
            return new ColorViewModel();
        }

        Palette.Swatch v = palette.getVibrantSwatch();
        Palette.Swatch dv = palette.getDarkVibrantSwatch();
        Palette.Swatch dm = palette.getDarkMutedSwatch();
        Palette.Swatch m = palette.getMutedSwatch();
        Palette.Swatch lv = palette.getLightVibrantSwatch();
        Palette.Swatch lm = palette.getLightMutedSwatch();

        ColorViewModel.Builder builder = new ColorViewModel.Builder();

        Palette.Swatch swatch = palette.getVibrantSwatch();

        if (swatch != null) {
            v = swatch;
        }

        swatch = palette.getDarkVibrantSwatch();

        if (swatch != null) {
            dv = swatch;
        }

        swatch = palette.getDarkMutedSwatch();

        if (swatch != null) {
            dm = swatch;
        }

        swatch = palette.getMutedSwatch();

        if (swatch != null) {
            m = swatch;
        }

        swatch = palette.getLightVibrantSwatch();

        if (swatch != null) {
            lv = swatch;
        }

        swatch = palette.getLightMutedSwatch();

        if (swatch != null) {
            lm = swatch;
        }

        boolean found = false;

        if (v != null) {
            builder.withFabBackgroundColor(palette.getVibrantColor(Color.CYAN));
            builder.withFabIconColor(v.getBodyTextColor());

            if (dv != null) {
                builder.withTextColor(dv.getBodyTextColor());
                builder.withBackgroundColor(palette.getDarkVibrantColor(Color.CYAN));
                found = true;
            }

            if (dm != null && !found) {
                builder.withTextColor(dm.getBodyTextColor());
                builder.withBackgroundColor(palette.getDarkMutedColor(Color.CYAN));
                found = true;
            }

            if (m != null && !found) {
                builder.withTextColor(m.getBodyTextColor());
                builder.withBackgroundColor(palette.getMutedColor(Color.CYAN));
                found = true;
            }

            if (lv != null && !found) {
                builder.withTextColor(lv.getBodyTextColor());
                builder.withBackgroundColor(palette.getLightVibrantColor(Color.CYAN));
                found = true;
            }

            if (lm != null && !found) {
                builder.withTextColor(lm.getBodyTextColor());
                builder.withBackgroundColor(palette.getLightMutedColor(Color.CYAN));
                found = true;
            }
        }


        if (dv != null && !found) {
            builder.withFabBackgroundColor(palette.getDarkVibrantColor(Color.CYAN));
            builder.withFabIconColor(dv.getBodyTextColor());

            if (dm != null) {
                builder.withTextColor(dm.getBodyTextColor());
                builder.withBackgroundColor(palette.getDarkMutedColor(Color.CYAN));
                found = true;
            }

            if (m != null && !found) {
                builder.withTextColor(m.getBodyTextColor());
                builder.withBackgroundColor(palette.getMutedColor(Color.CYAN));
                found = true;
            }

            if (lv != null && !found) {
                builder.withTextColor(lv.getBodyTextColor());
                builder.withBackgroundColor(palette.getLightVibrantColor(Color.CYAN));
                found = true;
            }

            if (lm != null && !found) {
                builder.withTextColor(lm.getBodyTextColor());
                builder.withBackgroundColor(palette.getLightMutedColor(Color.CYAN));
                found = true;
            }
        }

        if (dm != null && !found) {
            builder.withFabBackgroundColor(palette.getDarkMutedColor(Color.CYAN));
            builder.withFabIconColor(dm.getBodyTextColor());

            if (m != null) {
                builder.withTextColor(m.getBodyTextColor());
                builder.withBackgroundColor(palette.getMutedColor(Color.CYAN));
                found = true;
            }

            if (lv != null && !found) {
                builder.withTextColor(lv.getBodyTextColor());
                builder.withBackgroundColor(palette.getLightVibrantColor(Color.CYAN));
                found = true;
            }

            if (lm != null && !found) {
                builder.withTextColor(lm.getBodyTextColor());
                builder.withBackgroundColor(palette.getLightMutedColor(Color.CYAN));
                found = true;
            }
        }


        if (m != null && !found) {
            builder.withFabBackgroundColor(palette.getMutedColor(Color.CYAN));
            builder.withFabIconColor(m.getBodyTextColor());

            if (lv != null) {
                builder.withTextColor(lv.getBodyTextColor());
                builder.withBackgroundColor(palette.getLightVibrantColor(Color.CYAN));
                found = true;
            }

            if (lm != null && !found) {
                builder.withTextColor(lm.getBodyTextColor());
                builder.withBackgroundColor(palette.getLightMutedColor(Color.CYAN));
                found = true;
            }
        }

        if (lv != null && !found) {
            builder.withFabBackgroundColor(palette.getLightVibrantColor(Color.CYAN));
            builder.withFabIconColor(lv.getBodyTextColor());

            if (lm != null) {
                builder.withTextColor(lm.getBodyTextColor());
                builder.withBackgroundColor(palette.getLightMutedColor(Color.CYAN));
                found = true;
            }
        }


        if (lm != null && !found) {
            builder.withFabBackgroundColor(palette.getLightMutedColor(Color.CYAN));
            builder.withFabIconColor(lm.getBodyTextColor());
        }

        return builder.build();
    }

    public static Drawable getDrawable(@DrawableRes int drawableId, @NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(drawableId);
        } else {
            return context.getResources().getDrawable(drawableId);
        }
    }
}
