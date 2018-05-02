package com.r3dtech.life.logic.quests.missions.implementation;

import com.r3dtech.life.logic.quests.implemetation.GameTask;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.MissionUpdateListener;

import java.io.IOException;
import java.io.ObjectInputStream;

abstract class GameMission extends GameTask implements Mission {
    private static final long serialVersionUID = 1L;
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
        updateListener = new MissionUpdateListener() {
            @Override
            public void onDone(Mission mission) {

            }

            @Override
            public void onUndone(Mission mission) {

            }
        };
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        init();
    }
}
