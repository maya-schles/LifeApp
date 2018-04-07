package com.r3dtech.life.ui.fragments;

import android.widget.ExpandableListAdapter;

import com.r3dtech.life.logic.quests.quests.MainQuest;
import com.r3dtech.life.ui.adapters.MainQuestsAdapter;

public class MainQuestListViewFragment extends ExpandableListViewFragment<MainQuest> {
    @Override
    protected ExpandableListAdapter getAdapter() {
        return new MainQuestsAdapter(getItemList(), getContext());
    }
}
