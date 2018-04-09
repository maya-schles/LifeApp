package com.r3dtech.life.ui.fragments;

import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.quests.SideQuest;
import com.r3dtech.life.ui.adapters.SideQuestsAdapter;

public class SideQuestListViewFragment extends ExpandableListViewFragment<SideQuest> {
    @Override
    protected int getHeaderLayoutResource() {
        return R.layout.header_title;
    }

    @Override
    public void initHeader(View header) {
        ((TextView) header.findViewById(R.id.title)).setText(R.string.side_quests_tab);
    }
    @Override
    protected ExpandableListAdapter getAdapter() {
        return new SideQuestsAdapter(getItemList(), getContext());
    }
}
