package com.r3dtech.life.logic.quests.missions;

import com.r3dtech.life.logic.quests.GameDate;


public interface MainMission extends Mission {
    void setRepeat(Repeat repeat);
    Repeat getRepeat();
    boolean occursOnDay(GameDate date);
}
