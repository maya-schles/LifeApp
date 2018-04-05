package com.r3dtech.life.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.ui.adapters.reference_view_holders.MissionViewHolder;

import java.util.List;

public class MissionsViewAdapter<T extends Mission> extends RecyclerView.Adapter<MissionViewHolder<T>>{
    private List<T> missionList;

    public MissionsViewAdapter(List<T> missionList) {
        this.missionList = missionList;
    }

    @Override
    public MissionViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_mission_doable, parent, false);
        return new MissionViewHolder<T>(view);
    }

    @Override
    public void onBindViewHolder(MissionViewHolder<T> holder, int position) {
        holder.setMission(missionList.get(position));
    }

    @Override
    public int getItemCount() {
        return missionList.size();
    }
}
