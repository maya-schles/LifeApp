package com.r3dtech.life.logic.quests.missions.implementation;

import com.r3dtech.life.logic.quests.missions.SideMission;

import java.time.LocalDate;


public class GameSideMission extends GameMission implements SideMission {
    static final long serialVersionUID = 17L;
    private boolean isComplete = false;

    public GameSideMission(String title, String description, Difficulty difficulty) {
        super(title, description, difficulty);
    }

    @Override
    public boolean isComplete(LocalDate date) {
        return isComplete;
    }

    @Override
    public void setDone(LocalDate date) {
        isComplete = true;
        updateListener.onDone(this);
    }

    @Override
    public boolean isDoneForDay(LocalDate date) {
        return isComplete;
    }
}
