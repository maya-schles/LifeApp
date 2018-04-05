package com.r3dtech.life;

import android.support.v7.widget.RecyclerView;

import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.SideMission;
import com.r3dtech.life.logic.quests.missions.implementation.GameSideMission;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.quests.implementation.GameSideQuest;
import com.r3dtech.life.ui.adapters.MissionEditAdapter;
import com.r3dtech.life.ui.adapters.reference_view_holders.MissionEditViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SideQuestCreateActivity extends QuestCreateActivity {
    private static final String ACTION_BAR_TITLE = "Side Quest Creation";

    private List<SideMission> convertMissionList(List<Mission> missionList) {
        List<SideMission> res = new ArrayList<>(missionList.size());
        for (Mission mission: missionList) {
            res.add((SideMission) mission);
        }
        return res;
    }

    @Override
    Mission newMission() {
        return new GameSideMission("", "", Task.Difficulty.MEDIUM);
    }

    @Override
    String getActionBarTitle() {
        return ACTION_BAR_TITLE;
    }

    @Override
    RecyclerView.Adapter getMissionAdapter(List<Mission> missionList) {
        return new MissionEditAdapter<>(convertMissionList(missionList), MissionEditViewHolder.class);
    }

    @Override
    Quest createQuest(String title, String description, Task.Difficulty difficulty, List<Mission> missionList) {
        return new GameSideQuest(title, description, difficulty, convertMissionList(missionList));
    }

    @Override
    String getResultTag() {
        return MainActivity.SIDE_QUEST_TAG;
    }
}
