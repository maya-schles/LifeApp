package com.r3dtech.life.logic.quests.missions.implementation;

import com.r3dtech.life.logic.quests.GameDate;
import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Repeat;

import java.util.HashMap;
import java.util.Map;


public class GameMainMission extends GameMission implements MainMission {
    private static final long serialVersionUID = 1L;
    private Repeat repeat;
    private Map<GameDate, Boolean> datesDone = new HashMap<>();

    public GameMainMission(String title, String description, Difficulty difficulty, Repeat repeat) {
        super(title, description, difficulty);
        this.repeat = repeat;
    }

    @Override
    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    @Override
    public boolean occursOnDay(GameDate date) {
        return repeat.occursOnDay(date);
    }

    @Override
    public boolean isComplete(GameDate date) {
        return repeat.isComplete(date);
    }

    @Override
    public boolean isDoneForDay(GameDate date) {
        if (datesDone.get(date) == null) {
            return false;
        }
        return datesDone.get(date);
    }

    @Override
    public void setDone(GameDate date) {
        if (!isDoneForDay(date)) {
            repeat.addOccurance(date);
            datesDone.put(date, true);
            updateListener.onDone(this);
        }
    }

    @Override
    public void undoDone(GameDate date) {
        if(isDoneForDay(date)) {
            repeat.removeOccurance(date);
            datesDone.put(date, false);
            updateListener.onUndone(this);
        }
    }

    @Override
    public Repeat getRepeat() {
        return repeat;
    }
}
