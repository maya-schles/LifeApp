package com.r3dtech.life.ui.adapters.reference_view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.Mission;

public class MissionEditViewHolder extends RecyclerView.ViewHolder implements BasicSwipeViewHolder {
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Spinner difficultySpinner;

    protected Mission mission;

    private View foreground;

    public MissionEditViewHolder(View itemView) {
        super(itemView);
    }

    public void init() {
        findViews();
        populateDifficultySpinner();
    }

    protected void findViews() {
        foreground = itemView.findViewById(R.id.foreground);
        titleEditText = itemView.findViewById(R.id.title);
        descriptionEditText = itemView.findViewById(R.id.description);
        difficultySpinner = itemView.findViewById(R.id.difficulty_spinner);
    }

    private void populateDifficultySpinner() {
        ArrayAdapter<String> difficultySpinnerAdapter = new ArrayAdapter<>(itemView.getContext(),
                R.layout.support_simple_spinner_dropdown_item, Task.Difficulty.names());
        difficultySpinner.setAdapter(difficultySpinnerAdapter);
    }

    public void setMission(Mission mission) {
        this.mission = mission;
        titleEditText.setText(mission.title());
        descriptionEditText.setText(mission.description());
        difficultySpinner.setSelection(mission.getDifficulty().ordinal());
    }

    public void updateMission() {
        mission.setTitle(titleEditText.getText().toString());
        mission.setDescription(descriptionEditText.getText().toString());
        mission.setDifficulty(Task.Difficulty.valueOf(difficultySpinner.getSelectedItem().toString()));
    }

    @Override
    public View getForeground() {
        return foreground;
    }

    @Override
    public boolean canSwipe() {
        return true;
    }
}
