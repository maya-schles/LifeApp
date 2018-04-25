package com.r3dtech.life.logic.quests.missions.implementation;


import com.r3dtech.life.logic.quests.GameDate;

import java.util.HashMap;

public class DateRepeat extends MissionRepeat {
    static final long serialVersionUID = 14L;
    private GameDate endDate;
    private HashMap<GameDate, Boolean> occurances = new HashMap<>();

    public DateRepeat(GameDate startDate, GameDate endDate) {
        super(startDate);
        this.endDate = endDate;
        while (!occursOnDay(endDate)) {
            endDate.dec();
        }
    }

    public DateRepeat(boolean[] daysOfWeek, GameDate startDate, GameDate endDate) {
        super(daysOfWeek, startDate);
        this.endDate = endDate;
    }

    @Override
    public boolean isComplete(GameDate date) {
        return date.isAfter(endDate) || (endDate.equals(date) && occurances.get(date));
    }

    @Override
    public void addOccurance(GameDate date) {
        occurances.put(date, true);
    }

    @Override
    public void removeOccurance(GameDate date) {
        occurances.put(date, false);
    }

    public GameDate getEndDate() {
        return endDate;
    }
}
