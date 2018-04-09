package com.r3dtech.life.logic.gui;

import com.r3dtech.life.logic.avatar.Avatar;

public interface GameGuiListener {
    void updateAvatar(Avatar avatar);
    void createAvatar();
}
