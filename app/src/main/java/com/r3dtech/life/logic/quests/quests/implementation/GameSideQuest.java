package com.r3dtech.life.logic.quests.quests.implementation;

import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.SideMission;
import com.r3dtech.life.logic.quests.quests.SideQuest;

import java.util.List;

public class GameSideQuest extends GameQuest<SideMission> implements SideQuest {
    private static final long serialVersionUID = 1L;

    public GameSideQuest(String title, String description, Difficulty difficulty, List<SideMission> missionList) {
        super(title, description, difficulty, missionList);
    }

    @Override
    public boolean checkIsDone() {
        for (Mission mission : getMissions()) {
            if (!mission.isComplete(null)) {
                return false;
            }
        }
        return true;
    }
}
