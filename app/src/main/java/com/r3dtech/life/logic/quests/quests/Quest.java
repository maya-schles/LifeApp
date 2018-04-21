package com.r3dtech.life.logic.quests.quests;

import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.MissionUpdateListener;

import java.util.List;


public interface Quest<T extends Mission> extends Task {
    boolean isDone();
    List<T> getMissions();
    void setUpdateListener(QuestUpdateListener listener);
    void setMissionUpdateListener(MissionUpdateListener listener);
    int getID();
    void setID(int id);
}
