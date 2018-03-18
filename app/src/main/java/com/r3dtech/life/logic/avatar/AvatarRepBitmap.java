package com.r3dtech.life.logic.avatar;

public interface AvatarRepBitmap extends ItemBitmap {
    int VIEW_LAYERS_NUM = 6;

    int HAIR = 0;
    int SHOES = 1;
    int SHIRT = 2;
    int PANTS = 3;
    int BODY = 4;
    int BACKGROUND = 5;


    void setBackground(ItemBitmap background);
    void setBody(ItemBitmap body);
    void setShirt(ItemBitmap shirt);
    void setPants(ItemBitmap pants);
    void setShoes(ItemBitmap shoes);
    void setHair(ItemBitmap hair);
}
