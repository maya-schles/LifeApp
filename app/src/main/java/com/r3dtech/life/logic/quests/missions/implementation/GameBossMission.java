package com.r3dtech.life.logic.quests.missions.implementation;

import android.support.annotation.Nullable;

import com.r3dtech.life.logic.quests.GameDate;
import com.r3dtech.life.logic.quests.missions.BossMission;


public class GameBossMission extends GameMission implements BossMission {
    private static final long serialVersionUID = 1L;
    private boolean isComplete;

    public GameBossMission(String title, String description, Difficulty difficulty) {
        super(title, description, difficulty);
    }

    @Override
    public boolean isComplete(@Nullable GameDate date) {
        return isComplete;
    }

    @Override
    public void setDone(GameDate date) {
        if (!isComplete) {
            isComplete = true;
            updateListener.onDone(this);
        }
    }

    @Override
    public void undoDone(GameDate date) {
        if(isComplete) {
            isComplete = false;
            updateListener.onUndone(this);
        }
    }

    @Override
    public boolean isDoneForDay(GameDate date) {
        return isComplete;
    }
}
