package com.r3dtech.life.android;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.github.clans.fab.FloatingActionMenu;
import com.r3dtech.life.R;
import com.r3dtech.life.logic.Game;
import com.r3dtech.life.logic.LifeAppManager;
import com.r3dtech.life.logic.avatar.Avatar;
import com.r3dtech.life.logic.gui.GameGuiListener;
import com.r3dtech.life.ui.custom_views.CharacterView;
import com.r3dtech.life.ui.custom_views.QuestView;
import com.r3dtech.life.ui.fragments.MainQuestListViewFragment;
import com.r3dtech.life.ui.fragments.MissionsViewFragment;
import com.r3dtech.life.ui.fragments.SideQuestListViewFragment;
import com.r3dtech.life.ui.fragments.ExpandableListViewFragment;
import com.r3dtech.life.ui.fragments.StatsFragment;

import java.time.LocalDate;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GameGuiListener{
    private int selectedNavID = R.id.nav_missions;
    private Game game;
    private LifeAppManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = ((LifeApplication) getApplication());
        game = manager.getGame();
        manager.setGameGUI(this);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ((FloatingActionMenu) findViewById(R.id.create_quest_menu)).setClosedOnTouchOutside(true);

        findViewById(R.id.character_view).invalidate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateFragment();
    }

    @Override
    protected void onStop() {
        super.onStop();
        game.stop();
    }

    private void updateFragment() {
        Fragment fragment = getFragmentForNav(selectedNavID);
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flcontent, fragment).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = getFragmentForNav(item.getItemId());
        selectedNavID = item.getItemId();

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flcontent, fragment).commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getFragmentForNav(int navId) {
        Fragment fragment = null;
        switch (navId) {
            case R.id.nav_side_quests:
                fragment = ExpandableListViewFragment.newInstance(SideQuestListViewFragment.class, game.getQuestDB().getSideQuests());
                ((ExpandableListViewFragment) fragment).setOnGroupClickListener(
                        (ExpandableListView parent, View v, int groupPosition, long id)->{
                        manager.editSideQuest(((QuestView) v).getQuest().getID());
                        return false;
                    });
                break;
            case R.id.nav_main_quests:
                fragment = ExpandableListViewFragment.newInstance(MainQuestListViewFragment.class, game.getQuestDB().getMainQuests());
                ((ExpandableListViewFragment) fragment).setOnGroupClickListener(
                        (ExpandableListView parent, View v, int groupPosition, long id)->{
                            Log.d("Clicked on Group", ""+groupPosition);
                            manager.editMainQuest(((QuestView) v).getQuest().getID());
                            return true;
                        });
                break;
            case R.id.nav_missions:
                fragment = ExpandableListViewFragment.newInstance(MissionsViewFragment.class, game.getQuestDB().getMissionsForDate(LocalDate.now()));
                break;
            case R.id.nav_stats:
                fragment = StatsFragment.newInstance(game.getAvatar().getStats());
        }
        return fragment;
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
        manager.newSideQuest();
    }

    public void newMainQuestCallback(View v) {
        closeNewQuestMenu();
        manager.newMainQuest();
    }

    @Override
    public void updateAvatar(Avatar avatar) {
        ((CharacterView) findViewById(R.id.character_view)).update(avatar);
    }

    private void closeNewQuestMenu() {
        ((FloatingActionMenu) findViewById(R.id.create_quest_menu)).close(true);
    }
}
