package com.r3dtech.life.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.ui.adapters.MissionsViewAdapter;
import com.r3dtech.life.ui.misc.SwipeItemTouchHelperCallback;

import java.time.LocalDate;
import java.util.List;

import static android.support.v7.widget.helper.ItemTouchHelper.RIGHT;


public class MissionsViewFragment<T extends Mission> extends RecyclerViewFragment<T> implements SwipeItemTouchHelperCallback.ViewHolderSwipeHelperListener {
    private RecyclerView recyclerView;
    @Override
    protected RecyclerView.Adapter getAdapter(List<T> itemList) {
        return new MissionsViewAdapter<>(itemList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = v.findViewById(R.id.recycler_view);
        new ItemTouchHelper(new SwipeItemTouchHelperCallback(0, RIGHT, this)).attachToRecyclerView(recyclerView);
        return v;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        getItemList().get(position).setDone(LocalDate.now());
        recyclerView.getAdapter().notifyItemChanged(position);
    }
}
