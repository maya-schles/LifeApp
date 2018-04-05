package com.r3dtech.life.logic.quests.missions;

import android.support.annotation.Nullable;

import java.time.LocalDate;

public interface BossMission extends Mission {
    @Override
    boolean isComplete(@Nullable LocalDate date);
}
