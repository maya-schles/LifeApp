package com.r3dtech.life.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.ui.Utils;

public abstract class CreateMissionDialog<T extends Mission> extends Dialog {
    private static final String CREATE_TITLE = "Create a mission";
    private static final String EDIT_TITLE = "Edit mission";
    EditText titleEditText;
    EditText descriptionEditText;
    Spinner difficultySpinner;
    T mission;

    private EditMissionCallback callback;

    CreateMissionDialog(@NonNull Context context, EditMissionCallback callback, T mission) {
        super(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        this.callback = callback;
        this.mission = mission;
    }

    abstract int getLayout();
    abstract Mission createMission();

    private void findViews() {
        titleEditText = findViewById(R.id.title);
        descriptionEditText = findViewById(R.id.description);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
    }

    private void updateViews() {
        titleEditText.setText(mission.title());
        descriptionEditText.setText(mission.description());
        difficultySpinner.setSelection(mission.getDifficulty().ordinal());
        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setVisibility(View.VISIBLE);
        deleteButton.setOnClickListener(this::deleteCallback);
    }
    void init() {
        findViews();
        findViewById(R.id.confirm_mission_button).setOnClickListener(this::createMissionCallback);
        Utils.populateDifficultySpinner(difficultySpinner, getContext());
        setTitle(mission == null?CREATE_TITLE:EDIT_TITLE);
        if (mission != null) {
            updateViews();
        }
    }

    private void createMissionCallback(View v) {
        callback.onMissionCreated(mission, createMission());
        dismiss();
    }

    private void deleteCallback(View v) {
        callback.onMissionCreated(mission, null);
        dismiss();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        init();
    }
}
