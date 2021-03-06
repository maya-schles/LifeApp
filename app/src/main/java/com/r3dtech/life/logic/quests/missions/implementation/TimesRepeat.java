package com.r3dtech.life.logic.quests.missions.implementation;


import com.r3dtech.life.logic.quests.GameDate;

public class TimesRepeat extends MissionRepeat {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_REP_TIMES = 66;
    private int repeatTimes, currTimes;

    public TimesRepeat(GameDate startDate) {
        this(startDate, DEFAULT_REP_TIMES);
    }

    public TimesRepeat(GameDate startDate, int repeatTimes) {
        super(startDate);
        this.repeatTimes = repeatTimes;
    }

    public TimesRepeat(boolean[] daysOfWeek, GameDate startDate, int repeatTimes) {
        super(daysOfWeek, startDate);
        this.repeatTimes = repeatTimes;
    }

    @Override
    public boolean isComplete(GameDate date) {
        return currTimes >= repeatTimes;
    }

    @Override
    public void addOccurance(GameDate date) {
        currTimes++;
    }

    @Override
    public void removeOccurance(GameDate date) {
        currTimes--;
    }

    public int getRepTimes() {
        return repeatTimes;
    }
}
