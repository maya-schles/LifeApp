package com.r3dtech.life.logic.avatar;

import com.r3dtech.life.logic.quests.Reward;

import java.io.Serializable;

public interface Avatar extends Serializable{
    int DEFAULT_MAX_HP = 100;
    int DEFAULT_MAX_XP = 30;

    Stats getStats();
    String name();
    Inventory getInventory();
    AvatarRepBitmap getAvatarRep();

    double getWeight();
    void setWeight(double weight);
    int getHeight();
    void setHeight(int height);

    int getXP();
    int getMaxXP();
    int getLevel();
    int getHP();
    int getMaxHP();

    void damage(int hpToDamage);
    void heal(int hpToHeal);

    void reward(Reward reward);
}
