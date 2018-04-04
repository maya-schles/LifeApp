package com.r3dtech.life.ui.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.r3dtech.life.logic.avatar.Avatar;
import com.r3dtech.life.logic.avatar.AvatarRepBitmap;
import com.r3dtech.life.logic.avatar.ItemBitmap;
import com.r3dtech.life.logic.avatar.implementation.GameAvatarRepBitmap;

public class AvatarDisplayView extends View {
    private AvatarRepBitmap avatarRep = new GameAvatarRepBitmap();
    private Paint paint;
    private Rect rect;

    public AvatarDisplayView(Context context) {
        super(context);
        init();
    }

    public AvatarDisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AvatarDisplayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setAvatar(Avatar avatar) {
        avatarRep = avatar.getAvatarRep();
    }

    private void init() {
        paint = new Paint();
        rect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
        int heigthWithoutPadding = height - getPaddingTop() - getPaddingBottom();

        if (widthWithoutPadding > heigthWithoutPadding) {
            size = heigthWithoutPadding;
        } else {
            size = widthWithoutPadding;
        }
        setMeasuredDimension(size+getPaddingLeft()+getPaddingRight(),
                size+getPaddingTop()+getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int pixelSize = getHeight() / ItemBitmap.SIZE;
        for (int i = 0; i < ItemBitmap.SIZE; i++) {
            for (int j = 0; j < ItemBitmap.SIZE; j++) {
                rect.set(i * pixelSize, j * pixelSize,
                        (i + 1) * pixelSize, (j + 1) * pixelSize);
                ItemBitmap.Color color = avatarRep.getColor(i, j);
                paint.setColor(Color.rgb(color.r, color.g, color.b));
                canvas.drawRect(rect, paint);
            }
        }

    }
}
