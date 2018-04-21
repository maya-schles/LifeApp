package com.r3dtech.life.ui.dialog;


import android.content.Context;
import android.support.annotation.NonNull;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.SideMission;
import com.r3dtech.life.logic.quests.missions.implementation.GameSideMission;

public class SideMissionCreateDialog extends CreateMissionDialog<SideMission>{

    public SideMissionCreateDialog(@NonNull Context context, EditMissionCallback callback, SideMission mission) {
        super(context, callback, mission);
    }

    @Override
    int getLayout() {
        return R.layout.dialog_edit_side_mission;
    }

    @Override
    Mission createMission() {
        return new GameSideMission(titleEditText.getText().toString(),
                descriptionEditText.getText().toString(),
                Task.Difficulty.valueOf(difficultySpinner.getSelectedItem().toString()));
    }
}
