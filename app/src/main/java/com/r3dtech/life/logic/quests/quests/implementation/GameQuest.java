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
    private transient QuestUpdateListener updateListener = new QuestUpdateListener() {
        @Override
        public void onComplete(Quest quest) {

        }

        @Override
        public void onUnComplete(Quest quest) {

        }
    };
    private transient MissionUpdateListener missionUpdateListener = new MissionUpdateListener() {
        @Override
        public void onDone(Mission mission) {

        }

        @Override
        public void onUndone(Mission mission) {

        }
    };
    private boolean isDone;

    GameQuest(String title, String description, Difficulty difficulty, List<T> missionList) {
        super(title, description, difficulty);
        this.missionList = missionList;
        init();
    }

    private void init() {
        for (Mission mission: missionList) {
            mission.setUpdateListener(new MissionUpdateListener() {
                @Override
                public void onDone(Mission mission) {
                    onMissionDone(mission);
                }

                @Override
                public void onUndone(Mission mission) {
                    onMissionUndone(mission);
                }
            });
        }
    }

    abstract boolean checkIsDone();

    @Override
    public boolean isDone() {
        return isDone;
    }

    private void onMissionDone(Mission mission) {
        if (checkIsDone() && !isDone) {
            isDone = true;
            updateListener.onComplete(this);
        }
        missionUpdateListener.onDone(mission);
    }

    private void onMissionUndone(Mission mission) {
        if (!checkIsDone() && isDone) {
            isDone = false;
            updateListener.onUnComplete(this);
        }
        missionUpdateListener.onUndone(mission);
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
