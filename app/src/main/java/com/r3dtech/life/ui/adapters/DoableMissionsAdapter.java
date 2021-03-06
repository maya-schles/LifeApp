package com.r3dtech.life.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.ui.custom_views.DoableMissionView;

import java.util.List;

public class DoableMissionsAdapter extends ArrayAdapter<Mission> {
    public DoableMissionsAdapter(List<Mission> missionList, Context context) {
        super(context, R.layout.view_mission_doable, missionList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = new DoableMissionView(getContext());
        }
        ((DoableMissionView) convertView).setMission(getItem(position));
        return convertView;
    }
}
