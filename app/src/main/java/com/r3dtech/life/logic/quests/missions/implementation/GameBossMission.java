package com.r3dtech.life.logic.quests.missions.implementation;

import android.support.annotation.Nullable;

import com.r3dtech.life.logic.quests.missions.BossMission;

import java.time.LocalDate;

public class GameBossMission extends GameMission implements BossMission {
    static final long serialVersionUID = 23L;
    private boolean isComplete;

    public GameBossMission(String title, String description, Difficulty difficulty) {
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
