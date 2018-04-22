package com.r3dtech.life.android;

import android.app.Application;
import android.content.Intent;

import com.r3dtech.life.data_loading.SharedPrefsHelper;
import com.r3dtech.life.logic.Game;
import com.r3dtech.life.logic.LifeAppManager;
import com.r3dtech.life.logic.gui.GameGuiListener;
import com.r3dtech.life.logic.implementation.GameImplementation;

import java.io.IOException;


public class LifeApplication extends Application implements LifeAppManager {
    private Game game;

    @Override
    public void onCreate() {
        super.onCreate();
        game = new GameImplementation(new SharedPrefsHelper(SHARED_PREF_TAG, getApplicationContext()), this);
        game.start();
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void clearGameData() throws IOException {
        game.clearData();
    }

    @Override
    public void setGameGUI(GameGuiListener gameGuiListener) {
        game.setGameGui(gameGuiListener);
    }

    public void createAvatar() {
        Intent intent = new Intent(this, AvatarCreationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void newSideQuest() {
        Intent intent = new Intent(this, SideQuestEditActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void editSideQuest(int questID) {
        Intent intent = new Intent(this, SideQuestEditActivity.class);
        intent.putExtra(QUEST_ID_KEY, questID);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void newMainQuest() {
        Intent intent = new Intent(this, MainQuestEditActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void editMainQuest(int questID) {
        Intent intent = new Intent(this, MainQuestEditActivity.class);
        intent.putExtra(QUEST_ID_KEY, questID);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
