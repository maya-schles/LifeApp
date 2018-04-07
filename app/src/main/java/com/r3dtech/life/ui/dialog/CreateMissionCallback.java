package com.r3dtech.life.ui.dialog;


import com.r3dtech.life.logic.quests.missions.Mission;

public interface CreateMissionCallback {
    void onMissionCreated(Mission mission);
}
