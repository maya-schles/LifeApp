package com.r3dtech.life.logic.quests.missions.implementation;

import java.time.LocalDate;

public class DateRepeat extends MissionRepeat {
    static final long serialVersionUID = 14L;
    private LocalDate endDate;

    public DateRepeat(LocalDate startDate, LocalDate endDate) {
        super(startDate);
        this.endDate = endDate;
    }

    public DateRepeat(boolean[] daysOfWeek, LocalDate startDate, LocalDate endDate) {
        super(daysOfWeek, startDate);
        this.endDate = endDate;
    }

    @Override
    public boolean isComplete(LocalDate date) {
        return date.isAfter(endDate);
    }

    @Override
    public void addOccurance() {

    }

    @Override
    public void removeOccurance() {

    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
