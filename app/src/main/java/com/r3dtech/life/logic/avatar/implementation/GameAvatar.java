package com.r3dtech.life.logic.avatar.implementation;

import com.r3dtech.life.logic.avatar.Avatar;
import com.r3dtech.life.logic.avatar.AvatarRepBitmap;
import com.r3dtech.life.logic.avatar.Inventory;
import com.r3dtech.life.logic.avatar.Stats;
import com.r3dtech.life.logic.quests.Reward;


public class GameAvatar implements Avatar {
    static final long serialVersionUID = 0L;
    private static final int MAX_XP_CONST = 3;
    private Stats stats;
    private Inventory inventory;
    private AvatarRepBitmap avatarRep;
    private String name;

    private int level = 1;
    private int currHP = DEFAULT_MAX_HP, maxHP = DEFAULT_MAX_HP;
    private int currXP, maxXP;

    public GameAvatar(String name) {
        this.name = name;
        stats = GameStats.getZeroInstance();
        inventory = new GameInventory();
        avatarRep = new GameAvatarRepBitmap();
        updateMaxXP();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    @Override
    public AvatarRepBitmap getAvatarRep() {
        return avatarRep;
    }

    @Override
    public int getXP() {
        return currXP;
    }

    @Override
    public int getMaxXP() {
        return maxXP;
    }

    @Override
    public int getHP() {
        return currHP;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public void reward(Reward reward) {
        currXP += reward.getXP();
        while (currXP >= maxXP) {
            currXP -= maxXP;
            levelUp();
        }
        inventory.addMoney(reward.getMoney());
    }

    @Override
    public void undoReward(Reward reward) {
        currXP -= reward.getXP();
        while (currXP < 0) {
            levelDown();
            currXP += maxXP;
        }
        inventory.payMoney(reward.getMoney());
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public int getLevel() {
        return level;
    }

    private void updateMaxXP() {
        if (level <= 15) {
            maxXP = 2*level+7;
        }
        else if (level <= 30) {
            maxXP = 5*level-38;
        }
        else {
            maxXP = 9*level-158;
        }
        maxXP*= MAX_XP_CONST;
    }

    private void levelUp() {
        level++;
        updateMaxXP();
    }

    private void levelDown() {
        level--;
        updateMaxXP();
    }
    @Override
    public void damage(int hpToDamage) {
        currHP -= hpToDamage;
        currHP = Math.max(0, currHP);
    }

    @Override
    public void heal(int hpToHeal) {
        currHP += hpToHeal;
        currHP = Math.min(maxHP, currHP);
    }


}
