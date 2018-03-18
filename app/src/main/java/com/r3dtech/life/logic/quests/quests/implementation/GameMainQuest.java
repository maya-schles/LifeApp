package com.r3dtech.life.logic.quests.quests.implementation;

import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.quests.MainQuest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameMainQuest extends GameQuest<MainMission> implements MainQuest {
    public GameMainQuest(String title, String description, Difficulty difficulty, List<MainMission> missionList) {
        super(title, description, difficulty, missionList);
    }
}
