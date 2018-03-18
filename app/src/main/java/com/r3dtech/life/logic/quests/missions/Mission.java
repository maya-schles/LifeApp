package com.r3dtech.life.logic.quests.missions;

import com.r3dtech.life.logic.quests.Task;

import java.time.LocalDate;

public interface Mission extends Task {
    boolean isComplete(LocalDate date);
    boolean isDoneForDay(LocalDate date);
    void setDone(LocalDate date);
}
