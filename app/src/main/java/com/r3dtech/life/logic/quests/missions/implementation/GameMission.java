package com.r3dtech.life.logic.quests.missions.implementation;

import com.r3dtech.life.logic.quests.implemetation.GameTask;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.MissionUpdateListener;

abstract class GameMission extends GameTask implements Mission {
    transient MissionUpdateListener updateListener = (Mission m)->{};

    GameMission(String title, String description, Difficulty difficulty) {
        super(title, description, difficulty);
    }

    @Override
    public void setUpdateListener(MissionUpdateListener listener) {
        updateListener = listener;
    }
}
