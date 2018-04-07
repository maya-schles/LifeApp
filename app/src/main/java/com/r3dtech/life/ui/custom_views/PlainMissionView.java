package com.r3dtech.life.ui.custom_views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;

import java.time.LocalDate;

public class PlainMissionView extends RelativeLayout{
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView difficultyTextView;

    public PlainMissionView(Context context) {
        super(context);
        init();
    }

    public PlainMissionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlainMissionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_mission_plain, this, true);
        findViews();
    }

    private void findViews() {
        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);
        difficultyTextView = findViewById(R.id.difficulty);
    }

    public void setMission(Mission mission) {
        titleTextView.setText(mission.title());
        descriptionTextView.setText(mission.description());
        difficultyTextView.setText(mission.getDifficulty().name());
    }

}
