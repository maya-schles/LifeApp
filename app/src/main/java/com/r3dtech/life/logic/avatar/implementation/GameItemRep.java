package com.r3dtech.life.logic.avatar.implementation;

import com.r3dtech.life.logic.avatar.ItemBitmap;


public class GameItemRep implements ItemBitmap {
    private String id;
    private int ver;

    public GameItemRep(String id, int ver) {
        this.id = id;
        this.ver = ver;
    }

    @Override
    public Color getColor(int x, int y) {
        return ItemBitmapDB.getColor(id, ver, x, y);
    }
}
