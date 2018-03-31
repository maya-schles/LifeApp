package com.r3dtech.life.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.r3dtech.life.R;

import java.util.Locale;
import java.util.concurrent.Callable;

public class StatBarView extends RelativeLayout {
    private BarView barView;
    private TextView statName;
    private TextView progressText;
    private ImageView icon;

    private Callable<Integer> progressGetter = ()->0;
    private Callable<Integer> maxGetter = ()->100;

    public StatBarView(Context context) {
        super(context);
        init();
    }

    public StatBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StatBarView,
                0, 0);
        init(a);
    }

    public StatBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(TypedArray a) {
        init();
        barView.setColor(a.getColor(R.styleable.StatBarView_progressColor, Color.RED));
        statName.setText(a.getString(R.styleable.StatBarView_statName));
        icon.setImageDrawable(a.getDrawable(R.styleable.StatBarView_statIcon));
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_stat_bar, this, true);
        barView = findViewById(R.id.bar);
        statName = findViewById(R.id.name);
        icon = findViewById(R.id.stat_icon);
        progressText = findViewById(R.id.ratio);
        setWillNotDraw(false);
    }

    public void setValuesGetter(Callable<Integer> progress, Callable<Integer> max) {
        progressGetter = progress;
        maxGetter = max;
    }

    private void setValues(int progress, int max) {
        barView.setRatio(((float) progress)/max);
        progressText.setText(String.format(Locale.CANADA,"%d\\%d", progress, max));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            setValues(progressGetter.call(), maxGetter.call());
            barView.invalidate();
        } catch (Exception e) {
            throw new RuntimeException("Error getting stat bar values "+e.getMessage());
        }
        super.onDraw(canvas);
    }
}
