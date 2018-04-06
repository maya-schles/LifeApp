package com.r3dtech.life.logic.quests.quests;



public interface QuestUpdateListener{
    void onComplete(Quest quest);
    void onUnComplete(Quest quest);
}
