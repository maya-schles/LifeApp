package com.r3dtech.life.ui.custom_views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.implementation.DateRepeat;
import com.r3dtech.life.logic.quests.missions.implementation.TimesRepeat;

import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class MainMissionView extends RelativeLayout{
    private TextView titleTextView, descriptionTextView, difficultyTextView;
    private TextView startDateTextView, endDateTextView;
    private TextView endCaseTextView, repTimesTextView;
    private CheckBox[] days = new CheckBox[7];

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MainMissionView(Context context) {
        super(context);
        init();
    }

    public MainMissionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MainMissionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_main_mission, this, true);
        findViews();
    }

    private void findViews() {
        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);
        difficultyTextView = findViewById(R.id.difficulty);
        endCaseTextView = findViewById(R.id.end_case);

        days[0] = findViewById(R.id.sunday);
        days[1] = findViewById(R.id.monday);
        days[2] = findViewById(R.id.tuesday);
        days[3] = findViewById(R.id.wednesday);
        days[4] = findViewById(R.id.thursday);
        days[5] = findViewById(R.id.friday);
        days[6] = findViewById(R.id.saturday);

        startDateTextView = findViewById(R.id.start_date);
        endDateTextView = findViewById(R.id.end_date);
        repTimesTextView = findViewById(R.id.number);
    }

    public void setMission(MainMission mission) {
        titleTextView.setText(mission.title());
        descriptionTextView.setText(mission.description());
        difficultyTextView.setText(mission.getDifficulty().name());

        startDateTextView.setText(dateFormat.format(mission.getRepeat().getStartDate()));

        for (int i = 0; i < days.length; i++) {
            days[i].setChecked(mission.getRepeat().daysOccurance()[i]);
        }

        if (mission.getRepeat() instanceof DateRepeat) {
            repTimesTextView.setVisibility(View.GONE);
            endDateTextView.setVisibility(View.VISIBLE);
            endDateTextView.setText(dateFormat.format(((DateRepeat) mission.getRepeat()).getEndDate()));
            endCaseTextView.setText("date range");
        }
        else if (mission.getRepeat() instanceof TimesRepeat) {
            repTimesTextView.setVisibility(View.VISIBLE);
            endDateTextView.setVisibility(View.GONE);
            repTimesTextView.setText(String.format(Locale.CANADA,"%d", ((TimesRepeat) mission.getRepeat()).getRepTimes()));
            endCaseTextView.setText("repeat times");
        }
    }
}
