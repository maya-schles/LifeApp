package com.r3dtech.life.logic.quests.missions.implementation;

import java.time.LocalDate;

public class TimesRepeat extends MissionRepeat {
    static final long serialVersionUID = 19L;
    private static final int DEFAULT_REP_TIMES = 66;
    private int repeatTimes, currTimes;

    public TimesRepeat(LocalDate startDate) {
        this(startDate, DEFAULT_REP_TIMES);
    }

    public TimesRepeat(LocalDate startDate, int repeatTimes) {
        super(startDate);
        this.repeatTimes = repeatTimes;
    }

    public TimesRepeat(boolean[] daysOfWeek, LocalDate startDate, int repeatTimes) {
        super(daysOfWeek, startDate);
        this.repeatTimes = repeatTimes;
    }

    @Override
    public boolean isComplete(LocalDate date) {
        return currTimes >= repeatTimes;
    }

    @Override
    public void addOccurance() {
        currTimes++;
    }

    @Override
    public void removeOccurance() {
        currTimes--;
    }

    public int getRepTimes() {
        return repeatTimes;
    }
}
