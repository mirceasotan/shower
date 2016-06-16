package com.sotan.mircea.shower.misc;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author mirceasotan
 */
public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int offset;

    public VerticalSpaceItemDecoration(int offset) {
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        outRect.bottom = offset;

        int position = parent.getChildAdapterPosition(view);
        int positionBefore = position - 1;

        if (position % 3 == 0) {
            outRect.left = offset;
            outRect.right = offset;
        } else {
            if (positionBefore >= 0) {
                if (positionBefore % 3 == 0) {
                    outRect.right = offset / 2;
                    outRect.left = offset;
                } else {
                    outRect.left = offset / 2;
                    outRect.right = offset;
                }
            }
        }

        if (position == 0) {
            outRect.top = offset;
        }
    }
}
