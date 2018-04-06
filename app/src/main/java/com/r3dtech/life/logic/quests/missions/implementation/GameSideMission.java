package com.r3dtech.life.logic.quests.missions.implementation;

import android.support.annotation.Nullable;

import com.r3dtech.life.logic.quests.missions.SideMission;

import java.time.LocalDate;


public class GameSideMission extends GameMission implements SideMission {
    static final long serialVersionUID = 17L;
    private boolean isComplete = false;

    public GameSideMission(String title, String description, Difficulty difficulty) {
        super(title, description, difficulty);
    }

    @Override
    public boolean isComplete(@Nullable LocalDate date) {
        return isComplete;
    }

    @Override
    public void setDone(LocalDate date) {
        if (!isComplete) {
            isComplete = true;
            updateListener.onDone(this);
        }
    }

    @Override
    public void undoDone(LocalDate date) {
        if(isComplete) {
            isComplete = false;
            updateListener.onUndone(this);
        }
    }

    @Override
    public boolean isDoneForDay(LocalDate date) {
        return isComplete;
    }
}
