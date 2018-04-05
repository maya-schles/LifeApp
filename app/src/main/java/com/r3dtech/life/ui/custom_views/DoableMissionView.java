package com.r3dtech.life.ui.custom_views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;

public class DoableMissionView extends RelativeLayout {
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView difficultyTextView;

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
