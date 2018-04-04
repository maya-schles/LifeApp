package com.r3dtech.life.ui.adapters.reference_view_holders;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.ui.Utils;
import com.r3dtech.life.ui.adapters.MissionsViewAdapter;
import com.r3dtech.life.ui.misc.SwipeItemTouchHelperCallback;

import java.time.LocalDate;

import static android.support.v7.widget.helper.ItemTouchHelper.RIGHT;

public class QuestViewHolder extends RecyclerView.ViewHolder implements BasicExtendViewHolder, SwipeItemTouchHelperCallback.ViewHolderSwipeHelperListener{
    private TextView titleView;
    private TextView descriptionView;
    private TextView difficultyView;
    private Quest quest;
    private RecyclerView details;

    public QuestViewHolder(View itemView) {
        super(itemView);
        findViews();
    }

    private void findViews() {
        titleView = itemView.findViewById(R.id.title);
        descriptionView = itemView.findViewById(R.id.description);
        difficultyView = itemView.findViewById(R.id.difficulty);
        details = itemView.findViewById(R.id.recycler_view);
        new ItemTouchHelper(new SwipeItemTouchHelperCallback(0, RIGHT, this)).attachToRecyclerView(details);
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
        titleView.setText(quest.title());
        descriptionView.setText(quest.description());
        difficultyView.setText(quest.getDifficulty().name());
        Utils.initRecyclerView(itemView.getContext(), details, new MissionsViewAdapter<Mission>(quest.getMissions()));
    }

    public Quest getQuest() {
        return quest;
    }

    @Override
    public void extendDetails(boolean extend) {
        details.setVisibility(extend?View.VISIBLE:View.GONE);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        ((Mission) quest.getMissions().get(position)).setDone(LocalDate.now());
        details.getAdapter().notifyItemChanged(position);
    }
}
