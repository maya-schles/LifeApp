package com.r3dtech.life.ui.fragments;

import android.widget.ExpandableListAdapter;

import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.ui.adapters.DoableMissionsAdapter;

import java.util.ArrayList;
import java.util.List;


public class MissionsViewFragment extends ExpandableListViewFragment<Mission> {
    @Override
    protected ExpandableListAdapter getAdapter() {
        List<Mission> missions = new ArrayList<>();
        missions.addAll(getItemList());
        return new DoableMissionsAdapter(missions, getContext());
    }
}
