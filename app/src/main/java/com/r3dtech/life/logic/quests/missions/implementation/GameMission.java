package com.r3dtech.life.logic.quests.missions.implementation;

import com.r3dtech.life.logic.quests.implemetation.GameTask;
import com.r3dtech.life.logic.quests.missions.Mission;

abstract class GameMission extends GameTask implements Mission {
    GameMission(String title, String description, Difficulty difficulty) {
        super(title, description, difficulty);
    }
}
