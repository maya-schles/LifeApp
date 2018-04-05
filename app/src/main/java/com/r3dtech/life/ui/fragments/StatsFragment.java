package com.r3dtech.life.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.avatar.Stats;
import com.r3dtech.life.ui.custom_views.StatTextView;

public class StatsFragment extends Fragment {
    private Stats stats;

    public static StatsFragment newInstance(Stats stats) {
        StatsFragment fragment = new StatsFragment();
        fragment.stats = stats;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        ((StatTextView) view.findViewById(R.id.intelligence)).setValue(stats.getStats()[Stats.INTELLIGENCE].value);
        ((StatTextView) view.findViewById(R.id.strength)).setValue(stats.getStats()[Stats.STRENGTH].value);
        ((StatTextView) view.findViewById(R.id.endurance)).setValue(stats.getStats()[Stats.ENDURANCE].value);
        ((StatTextView) view.findViewById(R.id.charisma)).setValue(stats.getStats()[Stats.CHARISMA].value);
        return view;
    }
}
