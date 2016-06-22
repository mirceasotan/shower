package com.sotan.mircea.shower.albumdetail.view;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.mircea.sotan.model.SimpleTrack;

import java.util.List;

/**
 * @author mirceasotan
 */
public class TrackAdapter extends ArrayAdapter<SimpleTrack> {
    public TrackAdapter(Context context, int resource) {
        super(context, resource);
    }

    public TrackAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public TrackAdapter(Context context, int resource, SimpleTrack[] objects) {
        super(context, resource, objects);
    }

    public TrackAdapter(Context context, int resource, int textViewResourceId, SimpleTrack[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public TrackAdapter(Context context, int resource, List<SimpleTrack> objects) {
        super(context, resource, objects);
    }

    public TrackAdapter(Context context, int resource, int textViewResourceId, List<SimpleTrack> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
