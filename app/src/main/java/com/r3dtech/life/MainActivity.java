package com.r3dtech.life;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;
import com.r3dtech.life.logic.Game;
import com.r3dtech.life.logic.avatar.Avatar;
import com.r3dtech.life.logic.avatar.implementation.GameAvatar;
import com.r3dtech.life.logic.quests.QuestDB;
import com.r3dtech.life.logic.quests.implemetation.GameQuestDB;
import com.r3dtech.life.logic.quests.quests.MainQuest;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.quests.SideQuest;
import com.r3dtech.life.shared_prefs_help.SharedPrefsHelper;
import com.r3dtech.life.ui.custom_views.CharacterView;
import com.r3dtech.life.ui.fragments.MissionsViewFragment;
import com.r3dtech.life.ui.fragments.QuestListViewFragment;
import com.r3dtech.life.ui.fragments.QuestViewFragment;
import com.r3dtech.life.ui.fragments.RecyclerViewFragment;

import java.io.IOException;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity implements Game, NavigationView.OnNavigationItemSelectedListener{
    private static final String AVATAR_TAG = "avatar";
    private static final String QUEST_DB_TAG = "quest_db";
    private static final String SHARED_PREF_TAG = "life";

    public static final String SIDE_QUEST_TAG = "side_quest";
    public static final String MAIN_QUEST_TAG = "main_quest";

    private static final int SIDE_QUEST_CREATE = 1;
    public static final int MAIN_QUEST_CREATE = 2;

    private Avatar avatar;
    private QuestDB questDB;
    private SharedPrefsHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefsHelper = new SharedPrefsHelper(SHARED_PREF_TAG, this);
        try {
            clearGameData();
        } catch (IOException e) {
            throw new RuntimeException("Error clearing game data");
        }//*/
        loadGameData();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ((CharacterView) findViewById(R.id.character_view)).setAvatar(avatar);
        ((FloatingActionMenu) findViewById(R.id.create_quest_menu)).setClosedOnTouchOutside(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveGameData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIDE_QUEST_CREATE && resultCode == RESULT_OK) {
            SideQuest quest = (SideQuest) data.getSerializableExtra(SIDE_QUEST_TAG);
            questDB.addSideQuest(quest);
        }

        if (requestCode == MAIN_QUEST_CREATE && resultCode == RESULT_OK) {
            MainQuest quest = (MainQuest) data.getSerializableExtra(MAIN_QUEST_TAG);
            questDB.addMainQuest(quest);
        }
    }

    private void loadAvatar() throws IOException, ClassNotFoundException{
        avatar = (Avatar) prefsHelper.read(AVATAR_TAG);
        if (avatar == null) {
            avatar = new GameAvatar(44, 168, "R3dtech");
        }
    }

    private void saveAvatar() throws IOException {
        prefsHelper.write(avatar, AVATAR_TAG);
    }

    private void loadQuestDB() throws IOException, ClassNotFoundException{
        questDB = (QuestDB) prefsHelper.read(QUEST_DB_TAG);
        if (questDB == null) {
            questDB = new GameQuestDB();
        }
    }

    private void saveQuestDB() throws IOException {
        prefsHelper.write(questDB, QUEST_DB_TAG);
    }

    private void loadGameData() {
        try {
            loadAvatar();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load avatar");
        }
        try {
            loadQuestDB();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load quest DB");
        }
    }

    private void saveGameData() {
        try {
            saveAvatar();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't save avatar");
        }
        try {
            saveQuestDB();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't save quest DB");
        }
    }

    private void clearGameData() throws IOException {
        prefsHelper.write(null, AVATAR_TAG);
        prefsHelper.write(null, QUEST_DB_TAG);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = getFragmentForNav(item.getItemId());

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flcontent, fragment).commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getFragmentForNav(int id) {
        RecyclerViewFragment fragment = null;
        switch (id) {
            case R.id.side_quests:
                fragment = RecyclerViewFragment.newInstance(QuestListViewFragment.class, questDB.getSideQuests());
                break;
            case R.id.main_quests:
                fragment = RecyclerViewFragment.newInstance(QuestListViewFragment.class, questDB.getMainQuests());
                break;
            case R.id.missions:
                fragment = RecyclerViewFragment.newInstance(MissionsViewFragment.class, questDB.getMissionsForDate(LocalDate.now()));
                break;
        }
        return fragment;
    }

    public void showQuest(Quest quest) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, QuestViewFragment.newInstance(quest)).commit();
    }


    public void openNavDrawer(View view) {
        ((DrawerLayout) findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void newSideQuestCallback(View v) {
        closeNewQuestMenu();
        newSideQuest();
    }

    private void newSideQuest() {
        Intent intent = new Intent(this, SideQuestCreateActivity.class);
        startActivityForResult(intent, SIDE_QUEST_CREATE);
    }

    public void newMainQuestCallback(View v) {
        closeNewQuestMenu();
        newMainQuest();
    }

    private void newMainQuest() {
        Intent intent = new Intent(this, MainQuestCreateActivity.class);
        startActivityForResult(intent, MAIN_QUEST_CREATE);
    }

    private void closeNewQuestMenu() {
        ((FloatingActionMenu) findViewById(R.id.create_quest_menu)).close(true);
    }
}
