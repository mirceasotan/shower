package com.sotan.mircea.shower.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by mirceasotan on 28/10/2016.
 */

public class ThinTextView extends TextView {
    public ThinTextView(Context context) {
        super(context);
        setFontStyle();
    }

    public ThinTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFontStyle();
    }

    public ThinTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontStyle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ThinTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFontStyle();
    }

    private void setFontStyle() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Roboto-Thin.ttf"));
    }
}
