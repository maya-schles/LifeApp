package com.r3dtech.life;

import com.r3dtech.life.logic.Game;
import com.r3dtech.life.logic.gui.GameGuiListener;

import java.io.IOException;

public interface LifeAppManager {
    String SHARED_PREF_TAG = "life";
    String QUEST_ID_KEY = "quest_id_key";

    Game getGame();
    void clearGameData() throws IOException;
    void setGameGUI(GameGuiListener gameGuiListener);

    void newSideQuest();
    void newMainQuest();
    void editSideQuest(int questID);
    void editMainQuest(int questID);
    void createAvatar();
}
