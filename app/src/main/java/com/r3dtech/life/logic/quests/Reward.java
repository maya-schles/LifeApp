package com.r3dtech.life.logic.quests;

import com.r3dtech.life.logic.avatar.Stats;

import java.io.Serializable;

public interface Reward extends Serializable {
    int getXP();
    int getMoney();
    Stats getStats();
}
