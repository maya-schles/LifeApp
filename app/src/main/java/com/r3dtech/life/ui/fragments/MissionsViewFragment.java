package com.r3dtech.life.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.ui.adapters.DoableMissionsAdapter;

import java.util.ArrayList;
import java.util.List;


public class MissionsViewFragment extends ExpandableListViewFragment<Mission> {
    @Override
    protected int getHeaderLayoutResource() {
        return R.layout.header_title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ExpandableListView listView = view.findViewById(R.id.expandable_list_view);
        listView.setGroupIndicator(null);
        return view;
    }

    @Override
    public void initHeader(View header) {
        ((TextView) header.findViewById(R.id.title)).setText(R.string.daily_missions_tab);
    }
    @Override
    protected ExpandableListAdapter getAdapter() {
        List<Mission> missions = new ArrayList<>();
        missions.addAll(getItemList());
        return new DoableMissionsAdapter(missions, getContext());
    }
}
