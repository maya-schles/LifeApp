package com.r3dtech.life.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.ui.misc.SwipeItemTouchHelperCallback;

import java.time.LocalDate;

import static android.support.v7.widget.helper.ItemTouchHelper.RIGHT;

public class QuestViewFragment<T extends Mission> extends MissionsViewFragment<T> {
    private Quest<T> quest;
    private RecyclerView recyclerView;

    public static QuestViewFragment newInstance(Quest quest) {
        QuestViewFragment res = (QuestViewFragment)RecyclerViewFragment.newInstance(QuestViewFragment.class, quest.getMissions());
        res.quest = quest;
        return res;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.viewholder_quest;
    }

    @Override
    public void initHeader(View header) {
        ((TextView) header.findViewById(R.id.title)).setText(quest.title());
        ((TextView) header.findViewById(R.id.description)).setText(quest.description());
        ((TextView) header.findViewById(R.id.difficulty)).setText(quest.getDifficulty().name());
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
