package com.r3dtech.life.ui.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BarView extends View {
    private static final int RX = 5;
    private static final int RY = 5;

    private RectF barRect;
    private Paint barPaint;
    private Paint progressPaint;

    private float ratio;

    public BarView(Context context) {
        super(context);
        init();
    }

    public BarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        barRect = new RectF();
        barPaint = new Paint();
        barPaint.setColor(Color.BLACK);

        progressPaint = new Paint();
        progressPaint.setColor(Color.RED);
        ratio = 1/3f;

        setWillNotDraw(false);
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public void setColor(int color) {
        progressPaint.setColor(color);
    }

    private float getRatio() {
        return ratio;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        barRect.set(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(barRect, RX, RY, barPaint);

        barRect.set(0, 0, getWidth()*getRatio(), getHeight());
        canvas.drawRoundRect(barRect, RX, RY, progressPaint);
    }
}
