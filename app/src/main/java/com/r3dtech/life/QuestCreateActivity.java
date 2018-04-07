package com.r3dtech.life;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.r3dtech.life.data_loading.SerializableDataHelper;
import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.ui.Utils;
import com.r3dtech.life.ui.adapters.MissionEditAdapter;
import com.r3dtech.life.ui.misc.SwipeItemTouchHelperCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;

public abstract class QuestCreateActivity extends AppCompatActivity implements SwipeItemTouchHelperCallback.ViewHolderSwipeHelperListener {
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Spinner difficultySpinner;
    private RecyclerView missionRecyclerView;
    private MissionEditAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_create);

        findViews();
        Utils.populateDifficultySpinner(difficultySpinner, this);
        changeTitle();

        ViewStub stub = findViewById(R.id.extra);
        stub.setLayoutResource(getExtraLayoutResource());
        initExtra(stub.inflate());

        adapter = (MissionEditAdapter) getMissionAdapter(initMissionsList());
        Utils.initRecyclerView(this, missionRecyclerView, adapter);
        new ItemTouchHelper(new SwipeItemTouchHelperCallback(0, LEFT, this)).attachToRecyclerView(missionRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quest_create_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.import_from_string:
                importQuestFromString();
                break;
        }
        return true;
    }

    protected int getExtraLayoutResource() {
        return R.layout.header_empty;
    }

    protected void initExtra(View extra) {

    }
    private void importQuestFromString() {
        Dialog dialog = new AlertDialog.Builder(this).
                setTitle("Import Quest From String").
                setView(R.layout.dialog_import_quest_from_string).create();
        Button importButton = dialog.findViewById(R.id.import_button);
        EditText stringInput = dialog.findViewById(R.id.string_input);
        importButton.setOnClickListener((View v)-> {
            try {
                finish((Quest) SerializableDataHelper.deserialize(stringInput.getText().toString()));
            } catch (IOException|ClassNotFoundException e) {
                throw new RuntimeException("Couldn't load quest from string");
            }
        });
        dialog.show();
    }

    abstract Mission newMission();

    private List<Mission> initMissionsList() {
        List<Mission> missionList = new ArrayList<>();
        missionList.add(newMission());
        return missionList;
    }

    private void findViews() {
        titleEditText = findViewById(R.id.title);
        descriptionEditText = findViewById(R.id.description);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        missionRecyclerView = findViewById(R.id.recycler_view);
    }

    abstract String getActionBarTitle();

    private void changeTitle() {
        getSupportActionBar().setTitle(getActionBarTitle());
    }

    abstract RecyclerView.Adapter getMissionAdapter(List<Mission> missionList);

    public void addMissionCallback(View v) {
        addMission();
    }

    private void addMission() {
        adapter.addMission(newMission());
    }

    public void createQuestCallback(View v) {
        createQuest();
    }

    abstract Quest createQuest(String title, String description, Task.Difficulty difficulty, List<Mission> missionList);

    abstract String getResultTag();

    private void createQuest() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        Task.Difficulty difficulty = Task.Difficulty.valueOf(difficultySpinner.getSelectedItem().toString());
        List<Mission> missionList = adapter.getMissions();

        finish(createQuest(title, description, difficulty, missionList));
    }

    private void finish(Quest quest) {
        Intent intent = new Intent();
        intent.putExtra(getResultTag(), quest);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        missionRecyclerView.removeViewAt(position);
        adapter.deleteMission(position);
    }
}
