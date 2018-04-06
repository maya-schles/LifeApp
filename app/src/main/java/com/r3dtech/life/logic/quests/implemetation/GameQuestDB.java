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
    static final long serialVersionUID = 11L;
    private List<MainQuest> mainQuests = new ArrayList<>();
    private List<SideQuest> sideQuests = new ArrayList<>();
    private List<MainQuest> doneMainQuests = new ArrayList<>();
    private List<SideQuest> doneSideQuests = new ArrayList<>();

    private transient QuestUpdateListener updateListener;
    private transient QuestUpdateListener innerQuestUpdateListener;
    private transient MissionUpdateListener missionUpdateListener;
    private transient MissionUpdateListener innerMissionUpdateListener;

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
        innerQuestUpdateListener = new QuestUpdateListener() {
            @Override
            public void onComplete(Quest quest) {
                onQuestDone(quest);
            }

            @Override
            public void onUnComplete(Quest quest) {
                onQuestUnDone(quest);
            }
        };
        innerMissionUpdateListener = new MissionUpdateListener() {
            @Override
            public void onDone(Mission mission) {
                onMissionDone(mission);
            }

            @Override
            public void onUndone(Mission mission) {
                onMissionUnDone(mission);
            }
        };

        for(Quest quest:sideQuests) {
            quest.setUpdateListener(innerQuestUpdateListener);
            quest.setMissionUpdateListener(innerMissionUpdateListener);
        }
        for(Quest quest:mainQuests) {
            quest.setUpdateListener(innerQuestUpdateListener);
            quest.setMissionUpdateListener(innerMissionUpdateListener);
        }

        updateListener = new QuestUpdateListener() {
            @Override
            public void onComplete(Quest quest) {

            }

            @Override
            public void onUnComplete(Quest quest) {

            }
        };
        missionUpdateListener = new MissionUpdateListener() {
            @Override
            public void onDone(Mission mission) {

            }

            @Override
            public void onUndone(Mission mission) {

            }
        };
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        init();
    }

    @Override
    public void addMainQuest(MainQuest quest) {
        mainQuests.add(quest);
        quest.setUpdateListener(innerQuestUpdateListener);
        quest.setMissionUpdateListener(innerMissionUpdateListener);
    }

    @Override
    public void addSideQuest(SideQuest quest) {
        sideQuests.add(quest);
        quest.setUpdateListener(innerQuestUpdateListener);
        quest.setMissionUpdateListener(innerMissionUpdateListener);
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
        if (quest instanceof MainQuest) {
            mainQuests.remove(quest);
            doneMainQuests.add((MainQuest) quest);
        }
        if (quest instanceof SideQuest) {
            sideQuests.remove(quest);
            doneSideQuests.add((SideQuest) quest);
        }
        updateListener.onComplete(quest);
    }

    private void onQuestUnDone(Quest quest) {
        if (quest instanceof MainQuest) {
            mainQuests.add((MainQuest) quest);
            doneMainQuests.remove(quest);
        }
        if (quest instanceof SideQuest) {
            sideQuests.add((SideQuest) quest);
            doneSideQuests.remove(quest);
        }
        updateListener.onUnComplete(quest);
    }

    private void onMissionDone(Mission mission) {
        missionUpdateListener.onDone(mission);
    }

    private void onMissionUnDone(Mission mission) {
        missionUpdateListener.onUndone(mission);
    }
}
