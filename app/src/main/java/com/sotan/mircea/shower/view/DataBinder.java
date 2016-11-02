package com.sotan.mircea.shower.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author mirceasotan
 */

public abstract class DataBinder<T extends RecyclerView.ViewHolder> {

    public abstract T onCreateNewViewHolder(ViewGroup parent);

    public abstract void onBindViewHolder(T holder, int position);

    public abstract int getItemCount();
}
