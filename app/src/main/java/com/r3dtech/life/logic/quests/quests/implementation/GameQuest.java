package com.r3dtech.life.logic.quests.quests.implementation;

import com.r3dtech.life.logic.quests.implemetation.GameTask;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.missions.MissionUpdateListener;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.quests.QuestUpdateListener;

import java.time.LocalDate;
import java.util.List;

public class GameQuest<T extends Mission> extends GameTask implements Quest<T> {
    private List<T> missionList;
    private transient QuestUpdateListener updateListener = (Quest q)->{};
    private transient MissionUpdateListener missionUpdateListener = (Mission m)->{};

    GameQuest(String title, String description, Difficulty difficulty, List<T> missionList) {
        super(title, description, difficulty);
        this.missionList = missionList;
        for (Mission mission: missionList) {
            mission.setUpdateListener((this::onMissionComplete));
        }
    }

    private void onMissionComplete(Mission mission) {
        if (isDone(LocalDate.now())) {
            updateListener.onComplete(this);
        }
        missionUpdateListener.onComplete(mission);
    }
    @Override
    public boolean isDone(LocalDate date) {
        for (Mission mission : missionList) {
            if (!mission.isComplete(date)) {
                return false;
            }
        }
        return true;
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
}
