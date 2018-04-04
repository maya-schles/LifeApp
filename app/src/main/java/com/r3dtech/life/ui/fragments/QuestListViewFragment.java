package com.r3dtech.life.ui.fragments;


import android.support.v7.widget.RecyclerView;

import com.r3dtech.life.MainActivity;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.ui.adapters.QuestViewAdapter;
import com.r3dtech.life.ui.adapters.reference_view_holders.QuestViewHolder;

import java.util.List;

public class QuestListViewFragment extends RecyclerViewFragment<Quest> {
    @Override
    protected RecyclerView.Adapter getAdapter(List<Quest> itemList) {
        return new QuestViewAdapter(itemList);
    }
}
