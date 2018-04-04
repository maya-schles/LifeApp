package com.r3dtech.life.logic.avatar.implementation;

import com.r3dtech.life.logic.avatar.Avatar;
import com.r3dtech.life.logic.avatar.AvatarRepBitmap;
import com.r3dtech.life.logic.avatar.Inventory;
import com.r3dtech.life.logic.avatar.Stats;
import com.r3dtech.life.logic.quests.Reward;


public class GameAvatar implements Avatar {
    static final long serialVersionUID = 0L;
    private Stats stats;
    private Inventory inventory;
    private AvatarRepBitmap avatarRep;
    private double weight;
    private int height;
    private String name;

    private int level = 1;
    private int currHP = DEFAULT_MAX_HP, maxHP = DEFAULT_MAX_HP;
    private int currXP, maxXP = DEFAULT_MAX_XP;

    public GameAvatar(double weight, int height, String name) {
        this.weight = weight;
        this.height = height;
        this.name = name;
        stats = GameStats.getZeroInstance();
        inventory = new GameInventory();
        avatarRep = new GameAvatarRepBitmap();
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
    public double getWeight() {
        return weight;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
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
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public int getLevel() {
        return level;
    }

    private void levelUp() {
        level++;
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
