package com.r3dtech.life.logic.quests.quests.implementation;

import com.r3dtech.life.logic.quests.missions.BossMission;
import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.quests.MainQuest;

import java.time.LocalDate;
import java.util.List;

public class GameMainQuest extends GameQuest<MainMission> implements MainQuest {
    static final long serialVersionUID = 20L;
    private BossMission bossMission;

    public GameMainQuest(String title, String description, Difficulty difficulty, List<MainMission> missionList, BossMission bossMission) {
        super(title, description, difficulty, missionList);
        this.bossMission = bossMission;
    }

    @Override
    public BossMission getBoss() {
        return bossMission;
    }

    @Override
    public boolean isBossReady(LocalDate date) {
        for (Mission mission : getMissions()) {
            if (!mission.isComplete(date)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkIsDone() {
        return bossMission.isComplete(null);
    }
}
