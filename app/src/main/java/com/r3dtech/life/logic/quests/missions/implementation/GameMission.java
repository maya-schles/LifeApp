package com.r3dtech.life.logic.quests.missions.implementation;

import com.r3dtech.life.logic.quests.implemetation.GameTask;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.MissionUpdateListener;

import java.io.IOException;
import java.io.ObjectInputStream;

abstract class GameMission extends GameTask implements Mission {
    transient MissionUpdateListener updateListener;

    GameMission(String title, String description, Difficulty difficulty) {
        super(title, description, difficulty);
        init();
    }

    @Override
    public void setUpdateListener(MissionUpdateListener listener) {
        updateListener = listener;
    }

    private void init() {
        updateListener = (Mission m)->{};
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        init();
    }
}
