package com.sotan.mircea.shower.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

/**
 * @author mirceasotan
 */

public abstract class DataBindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("msg", holder.getClass().getSimpleName() + "+ position" + position + " binder pos" + getBinderPosition(position));
        getDataBinder(holder.getItemViewType())
                .onBindViewHolder(holder, getBinderPosition(position));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getDataBinder(viewType).onCreateNewViewHolder(parent);
    }

    @Override
    public abstract int getItemCount();

    @Override
    public abstract int getItemViewType(int position);

    public abstract <T extends DataBinder> T getDataBinder(int viewType);

    public abstract int getBinderPosition(int position);
}
