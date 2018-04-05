package com.r3dtech.life.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.r3dtech.life.R;

import java.util.Locale;

public class StatTextView extends AppCompatTextView {
    private String statName = "stat";
    public StatTextView(Context context) {
        super(context);
        init();
    }

    public StatTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StatView,
                0, 0);
        init(a);
    }

    public StatTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StatView,
                defStyleAttr, 0);
        init(a);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, (int) getContext().getResources().getDimension(R.dimen.stat_view_height));
    }

    private void init(TypedArray a) {
        init();
        setTextColor(a.getColor(R.styleable.StatView_statColor, Color.BLACK));
        statName = a.getString(R.styleable.StatView_statName);
        setText(statName);
        Drawable drawable = a.getDrawable(R.styleable.StatView_statIcon);
        drawable.setTint(a.getColor(R.styleable.StatView_statColor, Color.BLACK));
        int size = (int) getContext().getResources().getDimension(R.dimen.stat_view_height);
        drawable.setBounds( 0, 0, size, size);
        setCompoundDrawablesRelative(drawable, null, null, null);
    }

    private void init() {
        setTextAppearance(R.style.Text_Stat_Style);
        setCompoundDrawablePadding((int) getContext().getResources().getDimension(R.dimen.small_horizontal_margin));
    }

    public void setValue(Object value) {
        setText(String.format(Locale.CANADA, "%s %s", statName, value));
    }
}
