package com.r3dtech.life.logic.quests;

import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.MissionUpdateListener;
import com.r3dtech.life.logic.quests.quests.MainQuest;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.quests.QuestUpdateListener;
import com.r3dtech.life.logic.quests.quests.SideQuest;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface QuestDB extends Serializable {
    List<MainMission> getMissionsForDate(LocalDate date);
    void addQuest(Quest quest);
    void removeQuest(int questID);
    Quest getQuest(int questID);
    List<MainQuest> getMainQuests();
    List<SideQuest> getSideQuests();

    void setQuestUpdateListener(QuestUpdateListener listener);
    void setMissionUpdateListener(MissionUpdateListener listener);
}
