package com.r3dtech.life.android;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.BossMission;
import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.implementation.GameBossMission;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.quests.implementation.GameMainQuest;
import com.r3dtech.life.ui.Utils;
import com.r3dtech.life.ui.adapters.MainMissionAdapter;
import com.r3dtech.life.ui.dialog.MainMissionCreateDialog;

import java.util.ArrayList;
import java.util.List;

public class MainQuestEditActivity extends QuestEditActivity {
    private static final String CREATE_ACTION_BAR_TITLE = "Main Quest Creation";
    private static final String EDIT_ACTION_BAR_TITLE = "Main Quest Edit";

    private EditText titleEditText;
    private EditText descriptionEditText;
    private Spinner difficultySpinner;

    private List<MainMission> convertMissionList(List<Mission> missionList) {
        List<MainMission> res = new ArrayList<>(missionList.size());
        for (Mission mission: missionList) {
            res.add((MainMission) mission);
        }
        return res;
    }

    @Override
    void newMissionDialog() {
        MainMissionCreateDialog dialog = new MainMissionCreateDialog(this, this::addMission, getSupportFragmentManager(), null);
        dialog.show();
    }

    @Override
    void editMissionDialog(Mission mission) {
        MainMissionCreateDialog dialog = new MainMissionCreateDialog(this, this::addMission, getSupportFragmentManager(),(MainMission) mission);
        dialog.show();
    }

    @Override
    String getActionBarTitle() {
        return isEdit()?EDIT_ACTION_BAR_TITLE:CREATE_ACTION_BAR_TITLE;
    }

    @Override
    ArrayAdapter getMissionAdapter(List<Mission> missionList) {
        return new MainMissionAdapter(this, convertMissionList(missionList));
    }

    @Override
    protected int getExtraLayoutResource() {
        return R.layout.extra_boss_mission_edit;
    }

    @Override
    protected void initExtra(View extra) {
        titleEditText = extra.findViewById(R.id.title);
        descriptionEditText = extra.findViewById(R.id.description);
        difficultySpinner = extra.findViewById(R.id.difficulty_spinner);
        Utils.populateDifficultySpinner(difficultySpinner, this);
    }

    private BossMission getBossMission() {
        return new GameBossMission(titleEditText.getText().toString(), descriptionEditText.getText().toString(), Task.Difficulty.valueOf(difficultySpinner.getSelectedItem().toString()));
    }

    @Override
    Quest createQuest(String title, String description, Task.Difficulty difficulty, List<Mission> missionList) {
        return new GameMainQuest(title, description, difficulty, convertMissionList(missionList), getBossMission());
    }
}
