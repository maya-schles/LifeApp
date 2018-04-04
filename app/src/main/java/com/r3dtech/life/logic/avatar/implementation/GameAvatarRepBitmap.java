package com.r3dtech.life.logic.avatar.implementation;


import com.r3dtech.life.logic.avatar.AvatarRepBitmap;
import com.r3dtech.life.logic.avatar.ItemBitmap;

import static com.r3dtech.life.logic.avatar.implementation.ItemBitmapDB.BACKGROUND_ID;
import static com.r3dtech.life.logic.avatar.implementation.ItemBitmapDB.BODY_ID;
import static com.r3dtech.life.logic.avatar.implementation.ItemBitmapDB.HAIR_ID;
import static com.r3dtech.life.logic.avatar.implementation.ItemBitmapDB.PANTS_ID;
import static com.r3dtech.life.logic.avatar.implementation.ItemBitmapDB.SHIRT_ID;
import static com.r3dtech.life.logic.avatar.implementation.ItemBitmapDB.SHOES_ID;

public class GameAvatarRepBitmap implements AvatarRepBitmap {
    static final long serialVersionUID = 1L;
    private ItemBitmap[] viewLayers = new GameItemRep[VIEW_LAYERS_NUM];

    //TODO make package
    public GameAvatarRepBitmap() {
        viewLayers[BACKGROUND] = new GameItemRep(BACKGROUND_ID, 0);
        viewLayers[BODY] = new GameItemRep(BODY_ID, 0);
        viewLayers[PANTS] = new GameItemRep(PANTS_ID, 0);
        viewLayers[SHIRT] = new GameItemRep(SHIRT_ID, 0);
        viewLayers[SHOES] = new GameItemRep(SHOES_ID, 0);
        viewLayers[HAIR] = new GameItemRep(HAIR_ID, 0);
    }

    @Override
    public void setBackground(ItemBitmap background) {
        viewLayers[BACKGROUND] = background;
    }

    @Override
    public void setBody(ItemBitmap body) {
        viewLayers[BODY] = body;
    }

    @Override
    public void setPants(ItemBitmap pants) {
        viewLayers[PANTS] = pants;
    }

    @Override
    public void setShirt(ItemBitmap shirt) {
        viewLayers[SHIRT] = shirt;
    }

    @Override
    public void setShoes(ItemBitmap shoes) {
        viewLayers[SHOES] = shoes;
    }

    @Override
    public void setHair(ItemBitmap hair) {
        viewLayers[HAIR] = hair;
    }

    @Override
    public Color getColor(int x, int y) {
        for (ItemBitmap layer: viewLayers) {
            if (layer != null && layer.getColor(x, y) != Color.TRANSPARENT) {
                return layer.getColor(x, y);
            }
        }
        return viewLayers[BACKGROUND].getColor(x, y);
    }
}
