package com.r3dtech.life.ui.fragments;

import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.quests.MainQuest;
import com.r3dtech.life.ui.adapters.MainQuestsAdapter;

public class MainQuestListViewFragment extends ExpandableListViewFragment<MainQuest> {
    @Override
    protected int getHeaderLayoutResource() {
        return R.layout.header_title;
    }

    @Override
    public void initHeader(View header) {
        ((TextView) header.findViewById(R.id.title)).setText(R.string.main_quests_tab);
    }

    @Override
    protected ExpandableListAdapter getAdapter() {
        return new MainQuestsAdapter(getItemList(), getContext());
    }
}
