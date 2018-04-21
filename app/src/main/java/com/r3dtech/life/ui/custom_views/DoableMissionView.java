package com.r3dtech.life.ui.custom_views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;

import java.time.LocalDate;

public class DoableMissionView extends MissionView {
    private CheckBox checkBox;
    private Mission mission;

    public DoableMissionView(Context context) {
        super(context);
        init();
    }

    public DoableMissionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DoableMissionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    int getLayout() {
        return R.layout.view_mission_doable;
    }

    private void init() {
        findViews();
        checkBox.setOnClickListener((View v) -> {
                if (checkBox.isChecked()) {
                    mission.setDone(LocalDate.now());
                }
                else {
                    mission.undoDone(LocalDate.now());
                }
        });
    }

    private void findViews() {
        checkBox = findViewById(R.id.checkbox);
    }

    public void setMission(Mission mission) {
        super.setMission(mission);
        checkBox.setChecked(mission.isDoneForDay(LocalDate.now()));
        this.mission = mission;
    }
}
