package com.r3dtech.life.logic.avatar.implementation;

import com.r3dtech.life.logic.avatar.Stats;

public class GameStats implements Stats {
    private static final long serialVersionUID = 1L;;
    private Stat[] stats = new Stat[STATS_NUM];

    private GameStats() {
        stats[INTELLIGENCE] = new Stat("Intelligence","INT", DEF_INTELLIGENCE);
        stats[STRENGTH] = new Stat("Strength", "STR", DEF_STRENGTH);
        stats[ENDURANCE] = new Stat("Endurance","END", DEF_ENDURANCE);
        stats[CHARISMA] = new Stat("Charisma", "CHR", DEF_CHARISMA);
    }

    public static GameStats getZeroInstance() {
        GameStats stats = new GameStats();
        stats.stats[INTELLIGENCE] = new Stat("Intelligence", "INT", 0);
        stats.stats[STRENGTH] = new Stat("Strength","STR", 0);
        stats.stats[ENDURANCE] = new Stat("Endurance","END", 0);
        stats.stats[CHARISMA] = new Stat("Charisma", "CHR", 0);
        return stats;
    }

    @Override
    public Stat[] getStats() {
        return stats;
    }

    @Override
    public void addStats(Stats statsToAdd) {
        for (int i = 0; i < stats.length; i++) {
            stats[i].value += statsToAdd.getStats()[i].value;
        }
    }
}
