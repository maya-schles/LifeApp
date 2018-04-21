package com.r3dtech.life.logic.quests.missions;

import android.support.annotation.Nullable;

import com.r3dtech.life.logic.quests.GameDate;


public interface BossMission extends Mission {
    @Override
    boolean isComplete(@Nullable GameDate date);
}
