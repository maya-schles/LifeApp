package com.r3dtech.life.ui.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.quests.Quest;

public class QuestView extends RelativeLayout {
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView difficultyTextView;

    public QuestView(Context context) {
        super(context);
        init();
    }

    public QuestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_quest, this, true);
        findViews();
    }

    private void findViews() {
        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);
        difficultyTextView = findViewById(R.id.difficulty);
    }

    public void setQuest(Quest quest) {
        titleTextView.setText(quest.title());
        descriptionTextView.setText(quest.description());
        difficultyTextView.setText(quest.getDifficulty().name());
    }
}
