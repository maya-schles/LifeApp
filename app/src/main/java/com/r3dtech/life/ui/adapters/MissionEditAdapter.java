package com.r3dtech.life.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.ui.adapters.reference_view_holders.MissionEditViewHolder;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class MissionEditAdapter<T extends MissionEditViewHolder, K extends Mission> extends RecyclerView.Adapter<T>{
    private List<K> missions;
    private Class<T> implementation;

    public MissionEditAdapter(List<K> missions, Class<T> implementation) {
        this.missions = missions;
        this.implementation = implementation;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_edit_side_mission, parent, false);
        try {
            T viewHolder = implementation.getConstructor(View.class).newInstance(view);
            viewHolder.init();
            return viewHolder;
        } catch (NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {
            throw new RuntimeException("Error creating view holder "+implementation.getName());
        }
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.setMission(missions.get(position));
    }

    @Override
    public int getItemCount() {
        return missions.size();
    }

    public void addMission(K mission) {
        int n = missions.size();
        missions.add(mission);
        notifyItemInserted(n);
    }

    public void deleteMission(int position) {
        missions.remove(position);
        notifyItemRemoved(position);
    }

    public List<K> getMissions() {
        return missions;
    }
}
