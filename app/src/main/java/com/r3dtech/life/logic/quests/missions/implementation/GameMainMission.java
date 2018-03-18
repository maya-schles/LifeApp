package com.r3dtech.life.logic.quests.missions.implementation;

import com.r3dtech.life.logic.quests.missions.MainMission;
import com.r3dtech.life.logic.quests.missions.Repeat;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class GameMainMission extends GameMission implements MainMission {
    private Repeat repeat;
    private Map<LocalDate, Boolean> datesDone = new HashMap<>();
    private Map<LocalDate, Boolean> datesDismissed = new HashMap<>();

    public GameMainMission(String title, String description, Difficulty difficulty, Repeat repeat) {
        super(title, description, difficulty);
        this.repeat = repeat;
    }

    @Override
    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    @Override
    public boolean occursOnDay(LocalDate date) {
        return repeat.occursOnDay(date) && !isDismissedForDay(date);
    }

    @Override
    public boolean isComplete(LocalDate date) {
        return repeat.isComplete(date);
    }

    @Override
    public boolean isDoneForDay(LocalDate date) {
        if (datesDone.get(date) == null) {
            return false;
        }
        return datesDone.get(date);
    }

    @Override
    public boolean isDismissedForDay(LocalDate date) {
        if (datesDismissed.get(date) == null) {
            return false;
        }
        return datesDone.get(date);
    }

    @Override
    public void setDone(LocalDate date) {
        if (!isDoneForDay(date)) {
            repeat.addOccurance();
            datesDone.put(date, true);
        }
    }

    @Override
    public void dismissForDay(LocalDate date) {
        datesDismissed.put(date, true);
    }

    @Override
    public Repeat getRepeat() {
        return repeat;
    }
}
