package com.sotan.mircea.shower;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author mirceasotan
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private GradientDrawable divider;

    public DividerItemDecoration(Context context, int color) {
        divider = (GradientDrawable) ContextCompat.getDrawable(context, R.drawable.divider);
        divider.setColor(ColorUtils.setAlphaComponent(color, 0x22));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }
}
