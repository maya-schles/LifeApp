package com.r3dtech.life.logic.quests.quests.implementation;

import com.r3dtech.life.logic.quests.implemetation.GameTask;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.MissionUpdateListener;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.quests.QuestUpdateListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public abstract class GameQuest<T extends Mission> extends GameTask implements Quest<T> {
    static final long serialVersionUID = 21L;

    private List<T> missionList;
    private transient QuestUpdateListener updateListener = (Quest q)->{};
    private transient MissionUpdateListener missionUpdateListener = (Mission m)->{};

    GameQuest(String title, String description, Difficulty difficulty, List<T> missionList) {
        super(title, description, difficulty);
        this.missionList = missionList;
        init();
    }

    private void init() {
        for (Mission mission: missionList) {
            mission.setUpdateListener((this::onMissionComplete));
        }
    }

    private void onMissionComplete(Mission mission) {
        if (isDone()) {
            updateListener.onComplete(this);
        }
        missionUpdateListener.onDone(mission);
    }

    @Override
    public List<T> getMissions() {
        return missionList;
    }

    @Override
    public void setUpdateListener(QuestUpdateListener listener) {
        updateListener = listener;
    }

    @Override
    public void setMissionUpdateListener(MissionUpdateListener listener) {
        this.missionUpdateListener = listener;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        init();
    }


}
