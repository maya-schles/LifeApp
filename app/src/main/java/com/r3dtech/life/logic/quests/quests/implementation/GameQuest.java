package com.r3dtech.life.logic.quests.quests.implementation;

import com.r3dtech.life.logic.quests.implemetation.GameTask;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.quests.Quest;

import java.time.LocalDate;
import java.util.List;

public class GameQuest<T extends Mission> extends GameTask implements Quest<T> {
    private List<T> missionList;

    GameQuest(String title, String description, Difficulty difficulty, List<T> missionList) {
        super(title, description, difficulty);
        this.missionList = missionList;
    }

    @Override
    public boolean isDone(LocalDate date) {
        for (Mission mission : missionList) {
            if (!mission.isComplete(date)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<T> getMissions() {
        return missionList;
    }
}
