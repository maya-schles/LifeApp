package com.r3dtech.life;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.r3dtech.life.data_loading.SerializableDataHelper;
import com.r3dtech.life.logic.quests.QuestDB;
import com.r3dtech.life.logic.quests.Task;
import com.r3dtech.life.logic.quests.missions.Mission;
import com.r3dtech.life.logic.quests.quests.Quest;
import com.r3dtech.life.ui.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.r3dtech.life.LifeAppManager.QUEST_ID_KEY;


public abstract class QuestCreateActivity extends AppCompatActivity{
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Spinner difficultySpinner;
    private ListView missionListView;
    private List<Mission> missions;
    private ArrayAdapter adapter;
    private int originalQuestID;
    private QuestDB questDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_create);

        questDB = ((LifeApplication) getApplication()).getGame().getQuestDB();
        originalQuestID = getIntent().getIntExtra(QUEST_ID_KEY, -1);

        findViews();
        Utils.populateDifficultySpinner(difficultySpinner, this);
        changeTitle();

        ViewStub stub = findViewById(R.id.extra);
        stub.setLayoutResource(getExtraLayoutResource());
        initExtra(stub.inflate());

        missions = new ArrayList<>();


        if (originalQuestID != -1) {
            Quest originalQuest = questDB.getQuest(originalQuestID);
            missions.addAll(originalQuest.getMissions());
            updateViews(originalQuest);
        }
        adapter = getMissionAdapter(missions);
        missionListView.setAdapter(adapter);
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

    abstract void newMissionDialog();

    private void findViews() {
        titleEditText = findViewById(R.id.title);
        descriptionEditText = findViewById(R.id.description);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        missionListView = findViewById(R.id.list_view);
    }

    private void updateViews(Quest originalQuest) {
        titleEditText.setText(originalQuest.title());
        descriptionEditText.setText(originalQuest.description());
        difficultySpinner.setSelection(originalQuest.getDifficulty().ordinal());
    }

    abstract String getActionBarTitle();

    private void changeTitle() {
        getSupportActionBar().setTitle(getActionBarTitle());
    }

    abstract ArrayAdapter getMissionAdapter(List<Mission> missionList);

    public void addMissionCallback(View v) {
        newMissionDialog();
    }

    void addMission(Mission mission) {
        missions.add(mission);
        adapter.add(mission);

    }

    public void createQuestCallback(View v) {
        createQuest();
    }

    abstract Quest createQuest(String title, String description, Task.Difficulty difficulty, List<Mission> missionList);

    private void createQuest() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        Task.Difficulty difficulty = Task.Difficulty.valueOf(difficultySpinner.getSelectedItem().toString());
        finish(createQuest(title, description, difficulty, missions));
    }

    private void finish(Quest quest) {
        questDB.removeQuest(originalQuestID);
        questDB.addQuest(quest);
        finish();
    }
}
