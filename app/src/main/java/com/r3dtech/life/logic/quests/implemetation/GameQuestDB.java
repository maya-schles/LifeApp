package com.r3dtech.life.logic.quests.implemetation;

import com.r3dtech.life.logic.quests.GameDate;
import com.r3dtech.life.logic.quests.missions.BossMission;
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
import java.util.ArrayList;
import java.util.List;

public class GameQuestDB implements QuestDB {
    private static final long serialVersionUID = 1L;
    private static int maxID = 0;

    private List<Quest> quests = new ArrayList<>();
    private List<Quest> doneQuests = new ArrayList<>();

    private transient QuestUpdateListener updateListener;
    private transient QuestUpdateListener innerQuestUpdateListener;
    private transient MissionUpdateListener missionUpdateListener;
    private transient MissionUpdateListener innerMissionUpdateListener;

    public GameQuestDB() {
        init();
    }

    @Override
    public List<MainMission> getMissionsForDate(GameDate date) {
        List<MainMission> res = new ArrayList<>();

        for (MainQuest quest : getMainQuests()) {
            for (MainMission mission : quest.getMissions()) {
                if (mission.occursOnDay(date) && !mission.isDoneForDay(date)) {
                    res.add(mission);
                }
            }
        }
        return res;
    }

    @Override
    public List<BossMission> getAvailableBossFights(GameDate date) {
        List<BossMission> res = new ArrayList<>();

        for (MainQuest quest : getMainQuests()) {
            if (quest.isBossReady(date)) {
                res.add(quest.getBoss());
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


        if (doneQuests == null) {
            doneQuests = new ArrayList<>();
        }

        if (quests == null) {
            quests = new ArrayList<>();
        }

        for(Quest quest: quests) {
            quest.setID(maxID++);
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
    public void addQuest(Quest quest) {
        quests.add(quest);
        quest.setID(maxID++);
        quest.setUpdateListener(innerQuestUpdateListener);
        quest.setMissionUpdateListener(innerMissionUpdateListener);
    }

    @Override
    public void removeQuest(int questID) {
        quests.remove(getQuest(questID));
    }

    @Override
    public Quest getQuest(int questID) {
        for (Quest quest: quests) {
            if (quest.getID() == questID) {
                return quest;
            }
        }
        return null;
    }

    @Override
    public List<MainQuest> getMainQuests() {
        List<MainQuest> res = new ArrayList<>();
        for (Quest quest : quests) {
            if (quest instanceof MainQuest) {
                res.add((MainQuest) quest);
            }
        }
        return res;
    }

    @Override
    public List<SideQuest> getSideQuests() {
        List<SideQuest> res = new ArrayList<>();
        for (Quest quest : quests) {
            if (quest instanceof SideQuest) {
                res.add((SideQuest) quest);
            }
        }
        return res;
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
        quests.remove(quest);
        doneQuests.add(quest);
        updateListener.onComplete(quest);
    }

    private void onQuestUnDone(Quest quest) {
        quests.add(quest);
        doneQuests.remove(quest);
        updateListener.onUnComplete(quest);
    }

    private void onMissionDone(Mission mission) {
        missionUpdateListener.onDone(mission);
    }

    private void onMissionUnDone(Mission mission) {
        missionUpdateListener.onUndone(mission);
    }
}
