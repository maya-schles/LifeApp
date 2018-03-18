package com.r3dtech.life.logic.quests.quests.implementation;

import com.r3dtech.life.logic.quests.missions.SideMission;
import com.r3dtech.life.logic.quests.quests.SideQuest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameSideQuest extends GameQuest<SideMission> implements SideQuest {
    public GameSideQuest(String title, String description, Difficulty difficulty, List<SideMission> missionList) {
        super(title, description, difficulty, missionList);
    }
}
