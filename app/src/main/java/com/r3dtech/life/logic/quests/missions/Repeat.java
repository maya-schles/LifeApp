package com.r3dtech.life.logic.quests.missions;

import java.io.Serializable;
import java.time.LocalDate;

public interface Repeat extends Serializable{
    boolean occursOnDay(LocalDate date);
    boolean isComplete(LocalDate date);
    void addOccurance();
    void removeOccurance();
    boolean[] daysOccurance();
    LocalDate getStartDate();
}
