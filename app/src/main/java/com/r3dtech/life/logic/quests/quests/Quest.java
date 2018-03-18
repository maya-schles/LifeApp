package com.r3dtech.life.logic.quests.quests;

import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.Mission;

import java.time.LocalDate;
import java.util.List;


public interface Quest<T extends Mission> extends Task {
    boolean isDone(LocalDate date);
    List<T> getMissions();
}
