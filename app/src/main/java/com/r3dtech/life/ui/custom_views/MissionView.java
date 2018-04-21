package com.r3dtech.life.ui.custom_views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;

public abstract class MissionView extends RelativeLayout{
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView difficultyTextView;
    private Mission mission;

    public MissionView(Context context) {
        super(context);
        init();
    }

    public MissionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MissionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    abstract int getLayout();

    private void findViews() {
        LayoutInflater.from(getContext()).inflate(getLayout(), this, true);
        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);
        difficultyTextView = findViewById(R.id.difficulty);
    }

    private void init() {
        findViews();
    }

    public void setMission(Mission mission) {
        titleTextView.setText(mission.title());
        descriptionTextView.setText(mission.description());
        difficultyTextView.setText(mission.getDifficulty().name());
        this.mission = mission;
    }

    public Mission getMission() {
        return mission;
    }
}
