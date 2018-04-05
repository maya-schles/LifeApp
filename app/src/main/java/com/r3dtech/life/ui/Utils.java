package com.r3dtech.life.ui;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.quests.Task;

public class Utils {
    public static void initRecyclerView(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public static void populateDifficultySpinner(Spinner spinner, Context context) {
        ArrayAdapter<String> difficultySpinnerAdapter =
                new ArrayAdapter<>(context,
                        R.layout.support_simple_spinner_dropdown_item, Task.Difficulty.names());
        spinner.setAdapter(difficultySpinnerAdapter);
    }
}
