package com.sotan.mircea.shower.view;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mirceasotan
 */

public abstract class MapDataBinderAdapter<E extends Enum> extends DataBindAdapter {
    private Map<E, DataBinder> binderMap = new HashMap<>();

    public abstract E getEnumFromPosition(int position);

    public abstract E getEnumFromOrdinal(int ordinal);

    @Override
    public int getItemCount() {
        int itemCount = 0;

        for (DataBinder binder : binderMap.values()) {
            itemCount += binder.getItemCount();
        }

        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return getEnumFromPosition(position).ordinal();
    }

    @Override
    public DataBinder getDataBinder(int viewType) {
        return getDataBinder(getEnumFromOrdinal(viewType));
    }

    @Override
    public int getBinderPosition(int position) {
        E targetViewType = getEnumFromPosition(position);

        int binderPosition = -1;

        for (int i = 0; i <= position; i++) {
            if (targetViewType == getEnumFromPosition(i)) {
                binderPosition++;
            }
        }

        if (binderPosition == -1) {
            throw new IllegalArgumentException("Invalid Argument");
        }

        return binderPosition;
    }

    protected void putBinder(E e, DataBinder binder) {
        binderMap.put(e, binder);
    }

    private DataBinder getDataBinder(E e) {
        return binderMap.get(e);
    }
}
