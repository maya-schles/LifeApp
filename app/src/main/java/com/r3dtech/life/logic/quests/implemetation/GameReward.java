package com.r3dtech.life.logic.quests.implemetation;

import com.r3dtech.life.logic.avatar.Stats;
import com.r3dtech.life.logic.avatar.implementation.GameStats;
import com.r3dtech.life.logic.quests.Reward;
import com.r3dtech.life.logic.quests.Task;

public class GameReward implements Reward {
    private static final long serialVersionUID = 1L;
    private int TRIVIAL_XP_REWARD = 3, TRIVIAL_MONEY_REWARD = 1;
    private int EASY_XP_REWARD = 8, EASY_MONEY_REWARD = 3;
    private int MEDIUM_XP_REWARD = 25, MEDIUM_MONEY_REWARD = 8;
    private int HARD_XP_REWARD = 75, HARD_MONEY_REWARD = 25;
    private int EXTREME_XP_REWARD = 200, EXTREME_MONEY_REWARD = 70;

    private int xp;
    private int money;
    private Stats stats;

    public GameReward(Task.Difficulty difficulty) {
        switch (difficulty) {
            case TRIVIAL:
                xp = TRIVIAL_XP_REWARD;
                money = TRIVIAL_MONEY_REWARD;
                break;
            case EASY:
                xp = EASY_XP_REWARD;
                money = EASY_MONEY_REWARD;
                break;
            case MEDIUM:
                xp = MEDIUM_XP_REWARD;
                money = MEDIUM_MONEY_REWARD;
                break;
            case HARD:
                xp = HARD_XP_REWARD;
                money = HARD_MONEY_REWARD;
                break;
            case EXTREME:
                xp = EXTREME_XP_REWARD;
                money = EXTREME_MONEY_REWARD;
                break;
        }
        stats = GameStats.getZeroInstance();
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public int getXP() {
        return xp;
    }

    @Override
    public Stats getStats() {
        return stats;
    }
}
