package com.r3dtech.life.ui.fragments;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.ui.adapters.DoableMissionsAdapter;

import java.util.ArrayList;
import java.util.List;


public class MissionsViewFragment extends ListViewFragment<Mission> {
    @Override
    protected int getHeaderLayoutResource() {
        return R.layout.header_title;
    }

    @Override
    public void initHeader(View header) {
        ((TextView) header.findViewById(R.id.title)).setText(R.string.daily_missions_tab);
    }
    @Override
    protected ListAdapter getAdapter() {
        List<Mission> missions = new ArrayList<>();
        missions.addAll(getItemList());
        return new DoableMissionsAdapter(missions, getContext());
    }
}
