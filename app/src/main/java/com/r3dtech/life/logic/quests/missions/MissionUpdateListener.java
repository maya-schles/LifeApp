package com.r3dtech.life.logic.quests.missions;


public interface MissionUpdateListener {
    void onDone(Mission mission);
    void onUndone(Mission mission);
}
