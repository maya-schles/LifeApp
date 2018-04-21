package com.r3dtech.life.ui.dialog;


import com.r3dtech.life.logic.quests.missions.Mission;

public interface EditMissionCallback {
    void onMissionCreated(Mission originalMission, Mission newMission);
}
