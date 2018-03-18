package com.r3dtech.life.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.ui.adapters.reference_view_holders.QuestViewHolder;

import java.util.List;

public class QuestViewAdapter extends RecyclerView.Adapter<QuestViewHolder>{
    private List<Quest> questList;
    private ViewHolderClickListener clickListener;

    public QuestViewAdapter(List<Quest> questList, ViewHolderClickListener clickListener) {
        this.questList = questList;
        this.clickListener = clickListener;
    }

    @Override
    public QuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.viewholder_quest, parent, false);
        return new QuestViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(QuestViewHolder holder, int position) {
        holder.setQuest(questList.get(position));
    }

    @Override
    public int getItemCount() {
        return questList.size();
    }
}
