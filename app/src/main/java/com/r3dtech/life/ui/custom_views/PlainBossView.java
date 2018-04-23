package com.r3dtech.life.ui.custom_views;

import android.content.Context;
import android.util.AttributeSet;

import com.r3dtech.life.R;

public class PlainBossView extends MissionView {
    public PlainBossView(Context context) {
        super(context);
    }

    public PlainBossView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlainBossView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    int getLayout() {
        return R.layout.view_mission_boss;
    }
}
