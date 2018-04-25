package com.r3dtech.life.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.BossMission;
import com.r3dtech.life.ui.custom_views.DoableBossView;

import java.util.List;

public class DoableBossAdapter extends ArrayAdapter<BossMission> {
    public DoableBossAdapter(List<BossMission> bossList, Context context) {
        super(context, R.layout.view_mission_doable_boss, bossList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = new DoableBossView(getContext());
        }
        ((DoableBossView) convertView).setMission(getItem(position));
        return convertView;
    }
}
