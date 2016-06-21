package com.sotan.mircea.shower.misc;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author mirceasotan
 */
public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private boolean loading = true;
    private final GridLayoutManager layoutManager;
    private ScrollEndListener listener;

    public RecyclerViewScrollListener(GridLayoutManager manager, ScrollEndListener listener) {
        this.layoutManager = manager;
        this.listener = listener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) {
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    loading = false;
                    if (listener != null) {
                        listener.onScrollEnded();
                    }
                }
            }
        }
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
