package com.sotan.mircea.shower.viewModel;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;

import com.google.gson.annotations.SerializedName;
import com.mircea.sotan.model.GsonObject;

/**
 * @author mirceasotan
 */

public class ColorViewModel extends GsonObject {
    @SerializedName("backgroundColor")
    private int backgroundColor = Color.BLACK;
    @SerializedName("textColor")
    private int textColor = Color.BLACK;
    @SerializedName("alphaTextColor")
    private int alphaTextColor;
    @SerializedName("fabBackgroundColor")
    private int fabBackgroundColor = Color.WHITE;
    @SerializedName("fabRippleColor")
    private int fabRippleColor = ColorUtils.setAlphaComponent(Color.BLACK, 0x25);
    @SerializedName("fabIconColor")
    private int fabIconColor = Color.BLACK;

    public ColorViewModel() {

    }

    private ColorViewModel(int c1, int c2, int c3, int c4, int c5) {
        this.backgroundColor = c1;
        this.textColor = c2;
        this.alphaTextColor = ColorUtils.setAlphaComponent(c2, 0x25);
        this.fabBackgroundColor = c3;
        this.fabRippleColor = c4;
        this.fabIconColor = c5;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getAlphaTextColor() {
        return alphaTextColor;
    }

    public void setAlphaTextColor(int alphaTextColor) {
        this.alphaTextColor = alphaTextColor;
    }

    public int getFabBackgroundColor() {
        return fabBackgroundColor;
    }

    public int getFabRippleColor() {
        return fabRippleColor;
    }

    public int getFabIconColor() {
        return fabIconColor;
    }

    public static class Builder {
        private int c1;
        private int c2;
        private int c3;
        private int c4;
        private int c5;

        public Builder() {
        }

        public Builder withBackgroundColor(int color) {
            this.c1 = color;
            return this;
        }


        public Builder withTextColor(int color) {
            this.c2 = color;
            return this;
        }


        public Builder withFabBackgroundColor(int color) {
            this.c3 = color;
            return this;
        }


        public Builder withFabRippleColor(int color) {
            this.c4 = color;
            return this;
        }


        public Builder withFabIconColor(int color) {
            this.c5 = color;
            return this;
        }

        public ColorViewModel build() {
            return new ColorViewModel(c1, c2, c3, c4, c5);
        }
    }
}
