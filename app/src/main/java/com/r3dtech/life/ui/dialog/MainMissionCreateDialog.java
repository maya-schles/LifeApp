package com.r3dtech.life.ui.dialog;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.GameDate;
import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.Repeat;
import com.r3dtech.life.logic.quests.missions.implementation.DateRepeat;
import com.r3dtech.life.logic.quests.missions.implementation.GameMainMission;
import com.r3dtech.life.logic.quests.missions.implementation.TimesRepeat;
import com.r3dtech.life.ui.fragments.DatePickerFragment;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MainMissionCreateDialog extends CreateMissionDialog<MainMission>{
    private Spinner endCaseSpinner;
    private CheckBox[] days = new CheckBox[7];
    private TextView startDateTextView, endDateTextView;
    private EditText repTimesEditText;
    private FragmentManager fragmentManager;

    public MainMissionCreateDialog(@NonNull Context context, EditMissionCallback callback, FragmentManager fragmentManager, MainMission mission) {
        super(context, callback, mission);
        this.fragmentManager = fragmentManager;
    }

    private void findViews() {
        // mission timings
        endCaseSpinner = findViewById(R.id.end_case_spinner);
        startDateTextView = findViewById(R.id.start_date);
        endDateTextView = findViewById(R.id.end_date);
        repTimesEditText = findViewById(R.id.number);

        // timing days
        days[0] = findViewById(R.id.sunday);
        days[1] = findViewById(R.id.monday);
        days[2] = findViewById(R.id.tuesday);
        days[3] = findViewById(R.id.wednesday);
        days[4] = findViewById(R.id.thursday);
        days[5] = findViewById(R.id.friday);
        days[6] = findViewById(R.id.saturday);
    }

    private void updateViews() {
        startDateTextView.setText(mission.getRepeat().getStartDate().toString());

        for (int i = 0; i < days.length; i++) {
            days[i].setChecked(mission.getRepeat().daysOccurance()[i]);
        }

        if (mission.getRepeat() instanceof DateRepeat) {
            repTimesEditText.setVisibility(View.GONE);
            endDateTextView.setVisibility(View.VISIBLE);
            endDateTextView.setText(((DateRepeat) mission.getRepeat()).getEndDate().toString());
            endCaseSpinner.setSelection(0);
        }
        else if (mission.getRepeat() instanceof TimesRepeat) {
            repTimesEditText.setVisibility(View.VISIBLE);
            endDateTextView.setVisibility(View.GONE);
            repTimesEditText.setText(String.format(Locale.CANADA,"%d", ((TimesRepeat) mission.getRepeat()).getRepTimes()));
            endCaseSpinner.setSelection(1);
        }
    }

    @Override
    void init() {
        super.init();
        findViews();

        // timing dates
        endDateTextView.setText(GameDate.now().toString());
        startDateTextView.setText(GameDate.now().toString());

        populatEndCaseSpinner();
        setDatePickers();
        setEndCaseSpinnerListener();
        if (mission != null) {
            updateViews();
        }
    }

    @Override
    int getLayout() {
        return R.layout.dialog_edit_main_mission;
    }

    private Repeat getRepeat() {
        boolean[] daysOfWeek = new boolean[days.length];

        for (int i = 0; i < daysOfWeek.length; i++) {
            daysOfWeek[i] = days[i].isChecked();
        }
        try {
            GameDate startDate = GameDate.parse(startDateTextView.getText().toString());

            switch (endCaseSpinner.getSelectedItemPosition()) {
                case 0:
                    GameDate endDate = GameDate.parse(endDateTextView.getText().toString());
                    return new DateRepeat(daysOfWeek, startDate, endDate);
                case 1:
                    return new TimesRepeat(daysOfWeek, startDate,
                            Integer.parseInt(repTimesEditText.getText().toString()));
            }
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    Mission createMission() {
        return new GameMainMission(titleEditText.getText().toString(),
                descriptionEditText.getText().toString(),
                Task.Difficulty.valueOf(difficultySpinner.getSelectedItem().toString()),
                getRepeat());
    }

    private void populatEndCaseSpinner() {
        ArrayAdapter<String> endCaseSpinnerAdapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, new String[]{"date range", "repeat times"});
        endCaseSpinner.setAdapter(endCaseSpinnerAdapter);
    }

    private void setDatePickers() {
        startDateTextView.setOnClickListener((View view) -> {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setDateText(startDateTextView);
            fragment.show(fragmentManager, "datePicker");
        });

        endDateTextView.setOnClickListener((View v) -> {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setDateText(endDateTextView);
            fragment.show(fragmentManager, "datePicker");
        });
    }


    private void setTimesEndCase() {
        endCaseSpinner.setSelection(1);
        repTimesEditText.setVisibility(View.VISIBLE);
        endDateTextView.setVisibility(View.GONE);
    }

    private void setDateEndCase() {
        endCaseSpinner.setSelection(0);
        endDateTextView.setVisibility(View.VISIBLE);
        repTimesEditText.setVisibility(View.INVISIBLE);
    }

    private void setEndCaseSpinnerListener() {
        endCaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    setDateEndCase();
                }
                if (position == 1) {
                    setTimesEndCase();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
