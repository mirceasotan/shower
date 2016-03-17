package com.mircea.sotan.model;

import com.google.gson.annotations.SerializedName;
import com.sun.tools.javac.util.List;

/**
 * Created by mircea
 */
public class CursorBasedPaging extends BasePaging {
    @SerializedName("Cursors")
    List<Cursor> cursors;

    public List<Cursor> getCursors() {
        return cursors;
    }

    public void setCursors(List<Cursor> cursors) {
        this.cursors = cursors;
    }
}
