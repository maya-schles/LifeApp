package com.r3dtech.life.ui.adapters;


import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.Repeat;
import com.r3dtech.life.logic.quests.missions.implementation.DateRepeat;
import com.r3dtech.life.logic.quests.missions.implementation.TimesRepeat;
import com.r3dtech.life.ui.adapters.reference_view_holders.MissionEditViewHolder;
import com.r3dtech.life.ui.fragments.DatePickerFragment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class MainMissionEditAdapter extends MissionEditAdapter<MainMissionEditAdapter.MainMissionEditViewHolder, MainMission> {
    private FragmentManager fragmentManager;

    public class MainMissionEditViewHolder extends MissionEditViewHolder {
        private Spinner endCaseSpinner;
        private CheckBox[] days = new CheckBox[7];
        private TextView startDateTextView, endDateTextView;
        private EditText repTimesEditText;

        private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        MainMissionEditViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void init() {
            super.init();
            populatEndCaseSpinner();
            setDatePickers();
            setEndCaseSpinnerListener();
        }

        @Override
        protected void findViews() {
            super.findViews();
            // mission timings
            endCaseSpinner = itemView.findViewById(R.id.end_case_spinner);
            startDateTextView = itemView.findViewById(R.id.start_date);
            endDateTextView = itemView.findViewById(R.id.end_date);
            repTimesEditText = itemView.findViewById(R.id.number);

            // timing days
            days[0] = itemView.findViewById(R.id.sunday);
            days[1] = itemView.findViewById(R.id.monday);
            days[2] = itemView.findViewById(R.id.tuesday);
            days[3] = itemView.findViewById(R.id.wednesday);
            days[4] = itemView.findViewById(R.id.thursday);
            days[5] = itemView.findViewById(R.id.friday);
            days[6] = itemView.findViewById(R.id.saturday);

            // timing dates
            endDateTextView.setText(dateFormat.format(LocalDate.now()));
            startDateTextView.setText(dateFormat.format(LocalDate.now()));
        }

        private void populatEndCaseSpinner() {
            ArrayAdapter<String> endCaseSpinnerAdapter = new ArrayAdapter<>(itemView.getContext(),
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

        @Override
        public void setMission(Mission mission) {
            super.setMission(mission);

            MainMission mainMission = (MainMission) mission;
            // repeat stuff
            startDateTextView.setText(dateFormat.format(mainMission.getRepeat().getStartDate()));
            Repeat repeat = mainMission.getRepeat();
            if (repeat instanceof TimesRepeat) {
                setTimesEndCase();
                repTimesEditText.setText(String.format(Locale.CANADA,
                        "%d", ((TimesRepeat) mainMission.getRepeat()).getRepTimes()));
            } else {
                setDateEndCase();
                endDateTextView.setText(dateFormat.format(((DateRepeat) mainMission.getRepeat()).getEndDate()));
            }

            // repeat days
            for (int i = 0; i < days.length; i++) {
                if (repeat.daysOccurance()[i]) {
                    if (!days[i].isChecked()) {
                        days[i].toggle();
                    }
                } else {
                    if (days[i].isChecked()) {
                        days[i].toggle();
                    }
                }
            }
        }

        private Repeat getRepeat() {
            boolean[] daysOfWeek = new boolean[days.length];

            for (int i = 0; i < daysOfWeek.length; i++) {
                daysOfWeek[i] = days[i].isChecked();
            }
            LocalDate startDate = LocalDate.parse(startDateTextView.getText(), dateFormat);

            switch (endCaseSpinner.getSelectedItemPosition()) {
                case 0:
                    LocalDate endDate = LocalDate.parse(endDateTextView.getText(), dateFormat);
                    return new DateRepeat(daysOfWeek, startDate, endDate);
                case 1:
                    return new TimesRepeat(daysOfWeek, startDate,
                            Integer.parseInt(repTimesEditText.getText().toString()));
            }
            return null;
        }

        @Override
        public void updateMission() {
            super.updateMission();
            ((MainMission) mission).setRepeat(getRepeat());
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

    public MainMissionEditAdapter(List<MainMission> missionList, FragmentManager fragmentManager) {
        super(missionList, MainMissionEditViewHolder.class);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public MainMissionEditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_edit_main_mission, parent, false);
        MainMissionEditViewHolder viewHolder = new MainMissionEditViewHolder(view);
        viewHolder.init();
        return viewHolder;
    }

}
