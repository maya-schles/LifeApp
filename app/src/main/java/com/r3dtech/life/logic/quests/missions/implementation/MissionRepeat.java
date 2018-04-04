package com.r3dtech.life.logic.quests.missions.implementation;

import com.r3dtech.life.logic.quests.missions.Repeat;

import java.time.LocalDate;

abstract class MissionRepeat implements Repeat{
    static final long serialVersionUID = 18L;
    private boolean[] daysOfWeek;
    private LocalDate startDate;

    MissionRepeat(LocalDate startDate) {
        this(new boolean[] {true, true, true, true, true, true, true}, startDate);
    }

    MissionRepeat(boolean[] daysOfWeek, LocalDate startDate) {
        this.daysOfWeek = daysOfWeek;
        this.startDate = startDate;
    }

    @Override
    public boolean occursOnDay(LocalDate date) {
        return !date.isBefore(startDate)&&daysOfWeek[date.getDayOfWeek().getValue()%7];
    }

    @Override
    public boolean[] daysOccurance() {
        return daysOfWeek;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }
}
