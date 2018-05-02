package com.r3dtech.life.logic.quests.implemetation;

import com.r3dtech.life.logic.quests.Reward;
import com.r3dtech.life.logic.quests.Task;

public class GameTask implements Task {
    private static final long serialVersionUID = 1L;
    private String title;
    private String description;
    private Reward reward;
    private Difficulty difficulty;

    public GameTask(String title, String description, Difficulty difficulty) {
        this.title = title;
        this.description = description;
        this.reward = new GameReward(difficulty);
        this.difficulty = difficulty;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Reward getReward() {
        return reward;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setReward(Reward reward) {
        this.reward = reward;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        reward = new GameReward(difficulty);
    }
}
