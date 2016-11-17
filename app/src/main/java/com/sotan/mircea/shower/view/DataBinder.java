package com.sotan.mircea.shower.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author mirceasotan
 */

public interface DataBinder<T extends RecyclerView.ViewHolder> {

    T onCreateNewViewHolder(ViewGroup parent);

    void onBindViewHolder(T holder, int position);

    int getItemCount();
}
