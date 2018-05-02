package com.r3dtech.life.logic.quests.missions.implementation;

import com.r3dtech.life.logic.quests.GameDate;
import com.r3dtech.life.logic.quests.missions.Repeat;


abstract class MissionRepeat implements Repeat{
    private static final long serialVersionUID = 1L;
    private boolean[] daysOfWeek;
    private GameDate startDate;

    MissionRepeat(GameDate startDate) {
        this(new boolean[] {true, true, true, true, true, true, true}, startDate);
    }

    MissionRepeat(boolean[] daysOfWeek, GameDate startDate) {
        this.daysOfWeek = daysOfWeek;
        this.startDate = startDate;
    }

    @Override
    public boolean occursOnDay(GameDate date) {
        return !date.isBefore(startDate)&&daysOfWeek[date.getDayOfWeek()];
    }

    @Override
    public boolean[] daysOccurance() {
        return daysOfWeek;
    }

    @Override
    public GameDate getStartDate() {
        return startDate;
    }
}
