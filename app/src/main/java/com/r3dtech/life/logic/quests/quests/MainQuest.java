package com.r3dtech.life.logic.quests.quests;

import com.r3dtech.life.logic.quests.missions.BossMission;
import com.r3dtech.life.logic.quests.missions.MainMission;

import java.time.LocalDate;


public interface MainQuest extends Quest<MainMission> {
    BossMission getBoss();
    boolean isBossReady(LocalDate date);
}
