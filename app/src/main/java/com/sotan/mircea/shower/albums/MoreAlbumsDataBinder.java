package com.sotan.mircea.shower.albums;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.view.DataBinder;

import static android.R.attr.textColor;

/**
 * @author mirceasotan
 */

public class MoreAlbumsDataBinder implements DataBinder<MoreAlbumsDataBinder.MoreViewHolder> {

    @Override public MoreViewHolder onCreateNewViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.more_layout, parent, false);

        return new MoreViewHolder(v, textColor);
    }

    @Override public void onBindViewHolder(MoreViewHolder holder, int position) {

    }

    @Override public int getItemCount() {
        return 1;
    }

    static class MoreViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ViewPager viewPager;

        MoreViewHolder(View itemView, int textColor) {
            super(itemView);
        }
    }
}
