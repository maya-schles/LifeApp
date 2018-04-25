package com.r3dtech.life.ui.custom_views;


import android.content.Context;
import android.util.AttributeSet;

import com.r3dtech.life.R;

public class DoableBossView extends DoableMissionView{
    public DoableBossView(Context context) {
        super(context);
    }

    public DoableBossView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoableBossView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    int getLayout() {
        return R.layout.view_mission_doable_boss;
    }
}
