package com.r3dtech.life.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.ui.custom_views.MainMissionView;

import java.util.List;

public class MainMissionAdapter extends ArrayAdapter<MainMission>{
    public MainMissionAdapter(@NonNull Context context, @NonNull List<MainMission> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MainMissionView view = new MainMissionView(getContext());
        view.setMission(getItem(position));
        return view;
    }
}
