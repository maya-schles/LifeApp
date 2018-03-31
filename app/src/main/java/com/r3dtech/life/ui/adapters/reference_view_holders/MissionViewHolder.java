package com.r3dtech.life.ui.adapters.reference_view_holders;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;

import java.time.LocalDate;

public class MissionViewHolder<T extends Mission> extends BasicSwipeViewHolder{
    private TextView title;
    private TextView description;
    private TextView difficulty;
    private T mission;

    public MissionViewHolder(View itemView) {
        super(itemView);
        findViews();
    }

    private void findViews() {
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        difficulty = itemView.findViewById(R.id.difficulty);
    }

    public void setMission(T mission) {
        this.mission = mission;
        title.setText(mission.title());
        description.setText(mission.description());
        difficulty.setText(mission.getDifficulty().name());
        if (mission.isDoneForDay(LocalDate.now())) {
            getForeground().setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public boolean canSwipe() {
        return !mission.isDoneForDay(LocalDate.now());
    }
}
