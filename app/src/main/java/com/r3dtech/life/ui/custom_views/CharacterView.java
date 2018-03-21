package com.r3dtech.life.ui.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.r3dtech.life.R;
import com.r3dtech.life.logic.avatar.Avatar;

public class CharacterView extends RelativeLayout {
    private AvatarDisplayView avatarView;
    private StatBarView hp;
    private StatBarView xp;

    public CharacterView(Context context) {
        super(context);
        init();
    }

    public CharacterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CharacterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_character, this, true);
        avatarView = findViewById(R.id.avatar_view);
        hp = findViewById(R.id.hp);
        xp = findViewById(R.id.xp);
    }

    public void setAvatar(Avatar avatar) {
        avatarView.setAvatar(avatar);
        hp.setValuesGetter(avatar::getHP, avatar::getMaxHP);
        xp.setValuesGetter(avatar::getXP, avatar::getMaxXP);
    }
}
