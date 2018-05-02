package com.r3dtech.life.android;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.github.clans.fab.FloatingActionMenu;
import com.r3dtech.life.R;
import com.r3dtech.life.logic.Game;
import com.r3dtech.life.logic.LifeAppManager;
import com.r3dtech.life.logic.avatar.Avatar;
import com.r3dtech.life.logic.gui.GameGuiListener;
import com.r3dtech.life.logic.quests.GameDate;
import com.r3dtech.life.ui.custom_views.CharacterView;
import com.r3dtech.life.ui.custom_views.QuestView;
import com.r3dtech.life.ui.fragments.BossViewFragment;
import com.r3dtech.life.ui.fragments.ListViewFragment;
import com.r3dtech.life.ui.fragments.MainQuestListViewFragment;
import com.r3dtech.life.ui.fragments.MissionsViewFragment;
import com.r3dtech.life.ui.fragments.SideQuestListViewFragment;
import com.r3dtech.life.ui.fragments.ExpandableListViewFragment;
import com.r3dtech.life.ui.fragments.StatsFragment;



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

        FloatingActionMenu fam = findViewById(R.id.create_quest_menu);
        fam.setClosedOnTouchOutside(true);

        findViewById(R.id.character_view).invalidate();
        update_nav_view();

        /*NestedScrollView scrollView = findViewById(R.id.flcontent);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY && fam.getVisibility() == View.VISIBLE) {
                    fam.setVisibility(View.GONE);
                }
                if (scrollY < oldScrollY && fam.getVisibility() == View.GONE) {
                    fam.setVisibility(View.VISIBLE);
                }
            }
        });*/
    }

    private void update_nav_view() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu myMenu = navigationView.getMenu();
        MenuItem quests= myMenu.findItem(R.id.quests);
        SpannableString s = new SpannableString(quests.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.Text_category), 0, s.length(), 0);
        quests.setTitle(s);
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
                fragment = ListViewFragment.newInstance(MissionsViewFragment.class, game.getQuestDB().getMissionsForDate(GameDate.now()));
                break;
            case R.id.nav_stats:
                fragment = StatsFragment.newInstance(game.getAvatar().getStats());
                break;
            case R.id.nav_available_bosses:
                fragment = ListViewFragment.newInstance(BossViewFragment.class, game.getQuestDB().getAvailableBossFights(GameDate.now()));
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
