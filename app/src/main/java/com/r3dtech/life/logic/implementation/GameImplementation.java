package com.r3dtech.life.logic.implementation;

import com.r3dtech.life.data_loading.SerializableDataHelper;
import com.r3dtech.life.logic.Game;
import com.r3dtech.life.logic.avatar.Avatar;
import com.r3dtech.life.logic.avatar.implementation.GameAvatar;
import com.r3dtech.life.logic.gui.AvatarGui;
import com.r3dtech.life.logic.quests.QuestDB;
import com.r3dtech.life.logic.quests.implemetation.GameQuestDB;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.quests.Quest;

import java.io.IOException;


public class GameImplementation implements Game{
    private static final String AVATAR_TAG = "avatar";
    private static final String QUEST_DB_TAG = "quest_db";

    private QuestDB questDB;
    private Avatar avatar;
    private SerializableDataHelper dataHelper;
    private transient AvatarGui avatarGui;

    public GameImplementation(SerializableDataHelper gameDataHelper) {
        dataHelper = gameDataHelper;
        init();
    }

    private void init() {
        avatarGui = (gui)->{};
    }
    @Override
    public Avatar getAvatar() {
        return avatar;
    }

    @Override
    public QuestDB getQuestDB() {
        return questDB;
    }

    @Override
    public void stop() {
        saveGameData();
    }

    @Override
    public void start() {
        clearData();
        loadGameData();
        avatarGui.update(avatar);
    }

    private void loadQuestDB() throws IOException {
        questDB = (QuestDB) dataHelper.read(QUEST_DB_TAG);
        if (questDB == null) {
            initQuestDB();
        }
        else {
            questDB.setMissionUpdateListener(this::onMissionComplete);
            questDB.setQuestUpdateListener(this::onQuestComplete);
        }
    }

    private void loadAvatar() throws IOException {
        avatar = (Avatar) dataHelper.read(AVATAR_TAG);
        if (avatar == null) {
            initAvatar();
        }
    }
    private void loadGameData() {
        try {
            loadQuestDB();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load quests "+e.getMessage());
        }
        try {
            loadAvatar();
        } catch (IOException e) {
            throw new RuntimeException(("Couldn't load avatar "+e.getMessage()));
        }
    }

    private void saveGameData() {
        try {
            dataHelper.write(questDB, QUEST_DB_TAG);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't save quests "+e.getMessage());
        }
        try {
            dataHelper.write(avatar, AVATAR_TAG);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't save avatar "+e.getMessage());
        }
    }

    private void initQuestDB() {
        questDB = new GameQuestDB();
        questDB.setMissionUpdateListener(this::onMissionComplete);
        questDB.setQuestUpdateListener(this::onQuestComplete);
    }

    private void initAvatar() {
        avatar = new GameAvatar(44, 168, "R3dtech");
    }
    public void clearData() {
        initQuestDB();
        if (avatar != null) {
            avatar = new GameAvatar(avatar.getWeight(), avatar.getHeight(), avatar.name());
        } else {
            initAvatar();
        }
    }

    private void onMissionComplete(Mission mission) {
        avatar.reward(mission.getReward());
        avatarGui.update(avatar);
    }
    private void onQuestComplete(Quest quest) {
        avatar.reward(quest.getReward());
        avatarGui.update(avatar);
    }

    @Override
    public void bindAvatarGui(AvatarGui avatarGui) {
        this.avatarGui = avatarGui;
        avatarGui.update(avatar);
    }
}
