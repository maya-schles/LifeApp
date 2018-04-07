package com.r3dtech.life.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.ui.Utils;

public abstract class CreateMissionDialog extends Dialog {
    EditText titleEditText;
    EditText descriptionEditText;
    Spinner difficultySpinner;

    private CreateMissionCallback callback;

    CreateMissionDialog(@NonNull Context context, CreateMissionCallback callback) {
        super(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        this.callback = callback;
    }

    abstract int getLayout();
    abstract Mission createMission();

    private void findViews() {
        titleEditText = findViewById(R.id.title);
        descriptionEditText = findViewById(R.id.description);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
    }

    void init() {
        findViews();
        findViewById(R.id.create_mission_button).setOnClickListener(this::createMissionCallback);
        Utils.populateDifficultySpinner(difficultySpinner, getContext());
        setTitle("Add a mission");
    }

    private void createMissionCallback(View v) {
        callback.onMissionCreated(createMission());
        dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        init();
    }
}
