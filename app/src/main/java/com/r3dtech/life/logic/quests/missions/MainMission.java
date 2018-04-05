package com.r3dtech.life.logic.quests.missions;

import java.time.LocalDate;
import java.util.Date;

public interface MainMission extends Mission {
    void setRepeat(Repeat repeat);
    Repeat getRepeat();
    boolean occursOnDay(LocalDate date);
    boolean isDismissedForDay(LocalDate date);
    void dismissForDay(LocalDate date);

    boolean isDoneForDay(LocalDate date);
}
