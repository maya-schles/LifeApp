package com.r3dtech.life.ui.adapters.reference_view_holders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.ui.adapters.ViewHolderClickListener;

public class QuestViewHolder extends RecyclerView.ViewHolder {
    private TextView titleView;
    private TextView descriptionView;
    private TextView difficultyView;
    private Quest quest;

    public QuestViewHolder(View itemView, ViewHolderClickListener clickListener) {
        super(itemView);
        findViews();
        itemView.setOnClickListener((View v) -> {
            clickListener.onClick(this);
        });
    }

    private void findViews() {
        titleView = itemView.findViewById(R.id.title);
        descriptionView = itemView.findViewById(R.id.description);
        difficultyView = itemView.findViewById(R.id.difficulty);
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
        titleView.setText(quest.title());
        descriptionView.setText(quest.description());
        difficultyView.setText(quest.getDifficulty().name());
    }

    public Quest getQuest() {
        return quest;
    }
}
