package com.r3dtech.life.logic.quests.missions.implementation;


import com.r3dtech.life.logic.quests.GameDate;

public class DateRepeat extends MissionRepeat {
    static final long serialVersionUID = 14L;
    private GameDate endDate;

    public DateRepeat(GameDate startDate, GameDate endDate) {
        super(startDate);
        this.endDate = endDate;
    }

    public DateRepeat(boolean[] daysOfWeek, GameDate startDate, GameDate endDate) {
        super(daysOfWeek, startDate);
        this.endDate = endDate;
    }

    @Override
    public boolean isComplete(GameDate date) {
        return date.isAfter(endDate);
    }

    @Override
    public void addOccurance() {

    }

    @Override
    public void removeOccurance() {

    }

    public GameDate getEndDate() {
        return endDate;
    }
}
