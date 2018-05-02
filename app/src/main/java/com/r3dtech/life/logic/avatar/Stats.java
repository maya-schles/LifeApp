package com.r3dtech.life.logic.avatar;

import java.io.Serializable;

public interface Stats extends Serializable{
    class Stat implements Serializable{
        private static final long serialVersionUID = 1L;
        public String statName;
        public String statAbbr;
        public double value;

        public Stat(String name, String abbr, double value) {
            statName = name;
            statAbbr = abbr;
            this.value = value;
        }
    }

    int STATS_NUM = 4;

    int INTELLIGENCE = 0;
    int STRENGTH = 1;
    int ENDURANCE = 2;
    int CHARISMA = 3;

    int DEF_INTELLIGENCE = 0;
    int DEF_STRENGTH = 0;
    int DEF_ENDURANCE = 0;
    int DEF_CHARISMA = 0;

    Stat[] getStats();
    void addStats(Stats statsToAdd);
}
