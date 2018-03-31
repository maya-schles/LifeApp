package com.r3dtech.life.ui.adapters.reference_view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.r3dtech.life.R;

public class BasicSwipeViewHolder extends RecyclerView.ViewHolder{
    private View foreground;

    public BasicSwipeViewHolder(View itemView) {
        super(itemView);
        foreground = itemView.findViewById(R.id.foreground);
    }

    public View getForeground() {
        return foreground;
    }

    public boolean canSwipe() {
        return true;
    }
}
