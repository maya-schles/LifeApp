package com.r3dtech.life;

import android.support.v7.widget.RecyclerView;

import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.implementation.GameMainMission;
import com.r3dtech.life.logic.quests.missions.implementation.TimesRepeat;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.quests.implementation.GameMainQuest;
import com.r3dtech.life.ui.adapters.MainMissionEditAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainQuestCreateActivity extends QuestCreateActivity {
    private static final String ACTION_BAR_TITLE = "Main Quest Creation";

    private List<MainMission> convertMissionList(List<Mission> missionList) {
        List<MainMission> res = new ArrayList<>(missionList.size());
        for (Mission mission: missionList) {
            res.add((MainMission) mission);
        }
        return res;
    }
    @Override
    Mission newMission() {
        return new GameMainMission("", "", Task.Difficulty.MEDIUM, new TimesRepeat(LocalDate.now()));
    }

    @Override
    String getActionBarTitle() {
        return ACTION_BAR_TITLE;
    }

    @Override
    RecyclerView.Adapter getMissionAdater(List<Mission> missionList) {
        return new MainMissionEditAdapter(convertMissionList(missionList), getSupportFragmentManager());
    }

    @Override
    Quest createQuest(String title, String description, Task.Difficulty difficulty, List<Mission> missionList) {
        return new GameMainQuest(title, description, difficulty, convertMissionList(missionList));
    }

    @Override
    String getResultTag() {
        return MainActivity.MAIN_QUEST_TAG;
    }
}
