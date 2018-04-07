package com.r3dtech.life.ui.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.r3dtech.life.logic.quests.missions.SideMission;
import com.r3dtech.life.ui.custom_views.PlainMissionView;

import java.util.List;

public class SideMissionAdapter extends ArrayAdapter<SideMission>{
    public SideMissionAdapter(@NonNull Context context, @NonNull List<SideMission> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PlainMissionView view = new PlainMissionView(getContext());
        view.setMission(getItem(position));
        return view;
    }
}
