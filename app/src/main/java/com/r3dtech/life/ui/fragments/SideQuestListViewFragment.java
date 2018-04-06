package com.r3dtech.life.ui.fragments;

import android.widget.ExpandableListAdapter;

import com.r3dtech.life.logic.quests.quests.SideQuest;
import com.r3dtech.life.ui.adapters.SideQuestsAdapter;

public class SideQuestListViewFragment extends ExpandableListViewFragment<SideQuest> {
    @Override
    protected ExpandableListAdapter getAdapter() {
        return new SideQuestsAdapter(getItemList(), getContext());
    }
}
