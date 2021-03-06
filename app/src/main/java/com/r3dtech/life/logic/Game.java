package com.r3dtech.life.logic;

import com.r3dtech.life.logic.avatar.Avatar;
import com.r3dtech.life.logic.gui.GameGuiListener;
import com.r3dtech.life.logic.quests.QuestDB;


public interface Game{
    Avatar getAvatar();
    QuestDB getQuestDB();

    void clearData();

    void stop();
    void start();

    void setAvatar(Avatar avatar);
    void setGameGui(GameGuiListener gameGuiListener);
}
