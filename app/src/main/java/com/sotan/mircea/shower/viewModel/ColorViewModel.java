package com.sotan.mircea.shower.viewModel;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;

import com.google.gson.annotations.SerializedName;
import com.mircea.sotan.model.GsonObject;

/**
 * @author mirceasotan
 */

public class ColorViewModel extends GsonObject {
    @SerializedName("mainColor")
    private int mainColor = Color.WHITE;
    @SerializedName("textColor")
    private int textColor = Color.BLACK;
    @SerializedName("alphaTextColor")
    private int alphaTextColor;

    public ColorViewModel(int mainColor, int textColor) {
        this.mainColor = mainColor;
        this.textColor = textColor;
        this.alphaTextColor = ColorUtils.setAlphaComponent(textColor, 0x25);
    }

    public int getMainColor() {
        return mainColor;
    }

    public void setMainColor(int mainColor) {
        this.mainColor = mainColor;
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
}
