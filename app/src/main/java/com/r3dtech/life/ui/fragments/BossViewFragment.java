package com.r3dtech.life.ui.fragments;


import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.BossMission;
import com.r3dtech.life.ui.adapters.DoableBossAdapter;

import java.util.ArrayList;
import java.util.List;

public class BossViewFragment extends ListViewFragment<BossMission> {
    @Override
    protected int getHeaderLayoutResource() {
        return R.layout.header_title;
    }

    @Override
    public void initHeader(View header) {
        ((TextView) header.findViewById(R.id.title)).setText(R.string.boss_fights_tab);
    }
    @Override
    protected ListAdapter getAdapter() {
        List<BossMission> bosses = new ArrayList<>();
        bosses.addAll(getItemList());
        return new DoableBossAdapter(bosses, getContext());
    }
}
