package com.r3dtech.life.ui.adapters;


import android.support.transition.TransitionManager;
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

    private RecyclerView mRecyclerView;
    private int mExpandedPosition = -1;

    public QuestViewAdapter(List<Quest> questList) {
        this.questList = questList;
    }

    @Override
    public QuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.viewholder_quest, parent, false);
        return new QuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestViewHolder holder, int position) {
        holder.setQuest(questList.get(position));
        final boolean isExpanded = position==mExpandedPosition;
        holder.extendDetails(isExpanded);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                TransitionManager.beginDelayedTransition(mRecyclerView);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
    }
}
