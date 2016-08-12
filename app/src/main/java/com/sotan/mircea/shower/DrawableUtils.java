package com.sotan.mircea.shower;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

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
}
