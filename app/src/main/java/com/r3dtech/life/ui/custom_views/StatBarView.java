package com.r3dtech.life.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.r3dtech.life.R;

import java.util.Locale;

public class StatBarView extends RelativeLayout {
    private BarView barView;
    private TextView statName;
    private TextView progressText;
    private ImageView icon;

    public StatBarView(Context context) {
        super(context);
        init();
    }

    public StatBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StatView,
                0, 0);
        init(a);
    }

    public StatBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StatView,
                defStyleAttr, 0);
        init(a);
    }

    private void init(TypedArray a) {
        init();
        barView.setColor(a.getColor(R.styleable.StatView_statColor, Color.WHITE));
        statName.setText(a.getString(R.styleable.StatView_statName));
        icon.setImageDrawable(a.getDrawable(R.styleable.StatView_statIcon));
        icon.setColorFilter(a.getColor(R.styleable.StatView_statColor, Color.WHITE));
        invalidate();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_stat_bar, this, true);
        barView = findViewById(R.id.bar);
        statName = findViewById(R.id.name);
        icon = findViewById(R.id.stat_icon);
        progressText = findViewById(R.id.ratio);
        setWillNotDraw(false);
    }

    public void setValues(int progress, int max) {
        barView.setRatio(((float) progress)/max);
        progressText.setText(String.format(Locale.CANADA,"%d\\%d", progress, max));
    }
}
