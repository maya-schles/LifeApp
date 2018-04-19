package com.r3dtech.life.android;

import android.widget.ArrayAdapter;

import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.SideMission;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.quests.implementation.GameSideQuest;
import com.r3dtech.life.ui.adapters.SideMissionAdapter;
import com.r3dtech.life.ui.dialog.SideMissionCreateDialog;

import java.util.ArrayList;
import java.util.List;

public class SideQuestEditActivity extends QuestEditActivity {
    private static final String CREATE_ACTION_BAR_TITLE = "Side Quest Creation";
    private static final String EDIT_ACTION_BAR_TITLE = "Side Quest Edit";

    private List<SideMission> convertMissionList(List<Mission> missionList) {
        List<SideMission> res = new ArrayList<>(missionList.size());
        for (Mission mission: missionList) {
            res.add((SideMission) mission);
        }
        return res;
    }

    @Override
    void newMissionDialog() {
        SideMissionCreateDialog dialog = new SideMissionCreateDialog(this, this::addMission);
        dialog.show();
    }

    @Override
    String getActionBarTitle() {
        return isEdit()?EDIT_ACTION_BAR_TITLE:CREATE_ACTION_BAR_TITLE;
    }

    @Override
    ArrayAdapter getMissionAdapter(List<Mission> missionList) {
        return new SideMissionAdapter(this, convertMissionList(missionList));
    }

    @Override
    Quest createQuest(String title, String description, Task.Difficulty difficulty, List<Mission> missionList) {
        return new GameSideQuest(title, description, difficulty, convertMissionList(missionList));
    }
}
