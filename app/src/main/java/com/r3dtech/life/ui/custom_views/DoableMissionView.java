package com.r3dtech.life.ui.custom_views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;

import java.time.LocalDate;

public class DoableMissionView extends RelativeLayout {
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView difficultyTextView;
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

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_mission_doable, this, true);
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
        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);
        difficultyTextView = findViewById(R.id.difficulty);
        checkBox = findViewById(R.id.checkbox);
    }

    public void setMission(Mission mission) {
        titleTextView.setText(mission.title());
        descriptionTextView.setText(mission.description());
        difficultyTextView.setText(mission.getDifficulty().name());
        checkBox.setChecked(mission.isDoneForDay(LocalDate.now()));
        this.mission = mission;
    }
}
