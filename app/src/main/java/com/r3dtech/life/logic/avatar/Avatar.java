package com.r3dtech.life.logic.avatar;

import com.r3dtech.life.logic.quests.Reward;

import java.io.Serializable;

public interface Avatar extends Serializable{
    int DEFAULT_MAX_HP = 100;

    Stats getStats();
    String name();
    Inventory getInventory();
    AvatarRepBitmap getAvatarRep();


    int getXP();
    int getMaxXP();
    int getLevel();
    int getHP();
    int getMaxHP();

    void damage(int hpToDamage);
    void heal(int hpToHeal);

    void reward(Reward reward);
    void undoReward(Reward reward);
}
