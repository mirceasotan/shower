package com.sotan.mircea.shower.albums;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.view.DataBinder;

/**
 * @author mirceasotan
 */

public class CopyrightBinder extends DataBinder<CopyrightBinder.CopyRightViewHolder> {

    private String copyright;
    private int textColor;

    public CopyrightBinder(String copyright, int textColor) {
        this.copyright = copyright;
        this.textColor = textColor;
    }

    @Override
    public CopyRightViewHolder onCreateNewViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.copyrights_footer_layout, parent, false);

        return new CopyRightViewHolder(v, textColor);

    }

    @Override
    public void onBindViewHolder(CopyRightViewHolder holder, int position) {
        holder.copyrightTextView.setText(copyright);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class CopyRightViewHolder extends RecyclerView.ViewHolder {
        TextView copyrightTextView;

        CopyRightViewHolder(View itemView, int textColor) {
            super(itemView);
            this.copyrightTextView = (TextView) itemView;
            copyrightTextView.setTextColor(textColor);
        }
    }
}
