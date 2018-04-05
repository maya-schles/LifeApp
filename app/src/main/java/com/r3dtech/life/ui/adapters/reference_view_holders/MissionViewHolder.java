package com.r3dtech.life.ui.adapters.reference_view_holders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Mission;

import java.time.LocalDate;

public class MissionViewHolder<T extends Mission> extends RecyclerView.ViewHolder implements BasicSwipeViewHolder{
    private TextView title;
    private TextView description;
    private TextView difficulty;
    private T mission;
    private View foreground;

    public MissionViewHolder(View itemView) {
        super(itemView);
        findViews();
    }

    private void findViews() {
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        difficulty = itemView.findViewById(R.id.difficulty);
        foreground = itemView.findViewById(R.id.foreground);
    }

    public void setMission(T mission) {
        this.mission = mission;
        title.setText(mission.title());
        description.setText(mission.description());
        difficulty.setText(mission.getDifficulty().name());
        if (mission instanceof MainMission && ((MainMission) mission).isDoneForDay(LocalDate.now())) {
            getForeground().setBackgroundColor(Color.LTGRAY);
        }

        if (mission.isComplete(LocalDate.now())) {
            getForeground().setBackgroundColor(Color.rgb(144, 238, 144));
        }
    }

    @Override
    public View getForeground() {
        return foreground;
    }

    public boolean canSwipe() {
        if (mission instanceof MainMission) {
            return !((MainMission) mission).isDoneForDay(LocalDate.now());
        }
        return !mission.isComplete(LocalDate.now());
    }
}
