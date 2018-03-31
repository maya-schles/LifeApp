package com.r3dtech.life.logic.quests.implemetation;

import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.MissionUpdateListener;
import com.r3dtech.life.logic.quests.quests.MainQuest;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.QuestDB;
import com.r3dtech.life.logic.quests.quests.QuestUpdateListener;
import com.r3dtech.life.logic.quests.quests.SideQuest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameQuestDB implements QuestDB {
    private List<MainQuest> mainQuests = new ArrayList<>();
    private List<SideQuest> sideQuests = new ArrayList<>();
    private transient QuestUpdateListener updateListener;
    private transient MissionUpdateListener missionUpdateListener;

    public GameQuestDB() {
        init();
    }

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

    private void init() {
        for(Quest quest:sideQuests) {
            quest.setUpdateListener(this::onQuestDone);
            quest.setMissionUpdateListener(this::onMissionDone);
        }
        for(Quest quest:mainQuests) {
            quest.setUpdateListener(this::onQuestDone);
            quest.setMissionUpdateListener(this::onMissionDone);
        }
        updateListener = (Quest q)->{};
        missionUpdateListener = (Mission m)->{};
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        init();
    }
    @Override
    public void addMainQuest(MainQuest quest) {
        mainQuests.add(quest);
        quest.setUpdateListener(this::onQuestDone);
        quest.setMissionUpdateListener(this::onMissionDone);
    }

    @Override
    public void addSideQuest(SideQuest quest) {
        sideQuests.add(quest);
        quest.setUpdateListener(this::onQuestDone);
        quest.setMissionUpdateListener(this::onMissionDone);
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

    @Override
    public void setQuestUpdateListener(QuestUpdateListener listener) {
        updateListener = listener;
    }

    @Override
    public void setMissionUpdateListener(MissionUpdateListener listener) {
        missionUpdateListener = listener;
    }

    private void onQuestDone(Quest quest) {
        updateListener.onComplete(quest);
    }

    private void onMissionDone(Mission mission) {
        missionUpdateListener.onComplete(mission);
    }
}
