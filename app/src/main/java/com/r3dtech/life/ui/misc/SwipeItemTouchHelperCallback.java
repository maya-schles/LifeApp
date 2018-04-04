package com.r3dtech.life.ui.misc;


import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.r3dtech.life.ui.adapters.reference_view_holders.BasicSwipeViewHolder;

public class SwipeItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback {
    private ViewHolderSwipeHelperListener listener;

    public interface ViewHolderSwipeHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }

    public SwipeItemTouchHelperCallback(int dragDirs, int swipeDirs, ViewHolderSwipeHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (((BasicSwipeViewHolder) viewHolder).canSwipe()) {
            listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (((BasicSwipeViewHolder) viewHolder).canSwipe()) {
            getDefaultUIUtil().onDraw(c, recyclerView, ((BasicSwipeViewHolder) viewHolder).getForeground(), dX, dY, actionState, isCurrentlyActive);
        } else {
            getDefaultUIUtil().onDraw(c, recyclerView, ((BasicSwipeViewHolder) viewHolder).getForeground(), 0, dY, actionState, isCurrentlyActive);
        }
    }
}
