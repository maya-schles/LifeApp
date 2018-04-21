package com.r3dtech.life.logic.quests.missions;

import com.r3dtech.life.logic.quests.GameDate;
import com.r3dtech.life.logic.quests.Task;

public interface Mission extends Task {
    boolean isComplete(GameDate date);
    void setDone(GameDate date);
    void undoDone(GameDate date);
    void setUpdateListener(MissionUpdateListener listener);
    boolean isDoneForDay(GameDate date);
}
