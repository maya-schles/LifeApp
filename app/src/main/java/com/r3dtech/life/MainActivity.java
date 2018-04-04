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
import com.r3dtech.life.logic.implementation.GameImplementation;
import com.r3dtech.life.logic.quests.quests.MainQuest;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.logic.quests.quests.SideQuest;
import com.r3dtech.life.data_loading.SharedPrefsHelper;
import com.r3dtech.life.ui.fragments.MissionsViewFragment;
import com.r3dtech.life.ui.fragments.QuestListViewFragment;
import com.r3dtech.life.ui.fragments.QuestViewFragment;
import com.r3dtech.life.ui.fragments.RecyclerViewFragment;

import java.io.IOException;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final String SHARED_PREF_TAG = "life";

    public static final String SIDE_QUEST_TAG = "side_quest";
    public static final String MAIN_QUEST_TAG = "main_quest";

    private static final int SIDE_QUEST_CREATE = 1;
    public static final int MAIN_QUEST_CREATE = 2;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new GameImplementation(new SharedPrefsHelper(SHARED_PREF_TAG, this));
        game.start();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        game.bindAvatarGui(findViewById(R.id.character_view));
        ((FloatingActionMenu) findViewById(R.id.create_quest_menu)).setClosedOnTouchOutside(true);

        findViewById(R.id.character_view).invalidate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        game.stop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIDE_QUEST_CREATE && resultCode == RESULT_OK) {
            SideQuest quest = (SideQuest) data.getSerializableExtra(SIDE_QUEST_TAG);
            game.getQuestDB().addSideQuest(quest);
        }

        if (requestCode == MAIN_QUEST_CREATE && resultCode == RESULT_OK) {
            MainQuest quest = (MainQuest) data.getSerializableExtra(MAIN_QUEST_TAG);
            game.getQuestDB().addMainQuest(quest);
        }
    }


    private void clearGameData() throws IOException {
        game.clearData();
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
            case R.id.nav_side_quests:
                fragment = RecyclerViewFragment.newInstance(QuestListViewFragment.class, game.getQuestDB().getSideQuests());
                break;
            case R.id.nav_main_quests:
                fragment = RecyclerViewFragment.newInstance(QuestListViewFragment.class, game.getQuestDB().getMainQuests());
                break;
            case R.id.nav_missions:
                fragment = RecyclerViewFragment.newInstance(MissionsViewFragment.class, game.getQuestDB().getMissionsForDate(LocalDate.now()));
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
