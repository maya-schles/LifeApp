package com.r3dtech.life.logic.quests.missions;

import com.r3dtech.life.logic.quests.GameDate;

import java.io.Serializable;

public interface Repeat extends Serializable{
    boolean occursOnDay(GameDate date);
    boolean isComplete(GameDate date);
    void addOccurance(GameDate date);
    void removeOccurance(GameDate date);
    boolean[] daysOccurance();
    GameDate getStartDate();
}
