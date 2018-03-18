package com.r3dtech.life.logic.quests.implemetation;

import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.quests.MainQuest;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.QuestDB;
import com.r3dtech.life.logic.quests.quests.SideQuest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameQuestDB implements QuestDB {
    private List<MainQuest> mainQuests = new ArrayList<>();
    private List<SideQuest> sideQuests = new ArrayList<>();

    @Override
    public List<MainMission> getMissionsForDate(LocalDate date) {
        List<MainMission> res = new ArrayList<>();

        for (MainQuest quest : mainQuests) {
            for (MainMission mission : quest.getMissions()) {
                if (mission.occursOnDay(date) && !mission.isDoneForDay(date)) {
                    res.add(mission);
                }
            }
        }

        return res;
    }

    @Override
    public void addMainQuest(MainQuest quest) {
        mainQuests.add(quest);
    }

    @Override
    public void addSideQuest(SideQuest quest) {
        sideQuests.add(quest);
    }

    @Override
    public Quest getParentQuest(Mission mission) {
        // Search for parent in main quests
        for (Quest quest : mainQuests) {
            if(quest.getMissions().contains(mission)) {
                return quest;
            }
        }

        // Search for parent in side quests
        for (Quest quest : sideQuests) {
            if (quest.getMissions().contains(mission)) {
                return quest;
            }
        }

        return null;
    }

    @Override
    public List<MainQuest> getMainQuests() {
        return mainQuests;
    }

    @Override
    public List<SideQuest> getSideQuests() {
        return sideQuests;
    }

    @Override
    public void setMissionAsDone(Mission mission, LocalDate date) {
        mission.setDone(date);
    }

    @Override
    public void dismissMission(MainMission mission, LocalDate date) {
        mission.dismissForDay(date);
    }
}
