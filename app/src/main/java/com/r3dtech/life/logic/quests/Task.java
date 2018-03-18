package com.r3dtech.life.logic.quests;

import java.io.Serializable;

public interface Task extends Serializable {
    enum Difficulty {
        TRIVIAL,
        EASY,
        MEDIUM,
        HARD,
        EXTREME;

        public static String[] names() {
            String[] res = new String[values().length];
            for (int i = 0; i < values().length; i++) {
                res[i] = values()[i].name();
            }
            return res;
        }
    }
    String title();
    void setTitle(String title);
    String description();
    void setDescription(String description);
    Reward getReward();
    void setReward(Reward reward);
    Difficulty getDifficulty();
    void setDifficulty(Difficulty difficulty);
}
