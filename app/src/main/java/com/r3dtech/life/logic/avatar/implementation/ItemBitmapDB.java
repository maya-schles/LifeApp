package com.r3dtech.life.logic.avatar.implementation;

import com.r3dtech.life.logic.avatar.ItemBitmap;
import com.r3dtech.life.logic.avatar.implementation.itembitmaps.BackgroundBitmap;
import com.r3dtech.life.logic.avatar.implementation.itembitmaps.BodyBitmap;
import com.r3dtech.life.logic.avatar.implementation.itembitmaps.HairBitmap;
import com.r3dtech.life.logic.avatar.implementation.itembitmaps.PantsBitmap;
import com.r3dtech.life.logic.avatar.implementation.itembitmaps.ShirtBitmap;
import com.r3dtech.life.logic.avatar.implementation.itembitmaps.ShoesBitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBitmapDB {
    static final String BACKGROUND_ID = "background";
    static final String BODY_ID = "body";
    static final String PANTS_ID = "pants";
    static final String SHIRT_ID = "shirt";
    static final String SHOES_ID = "shoes";
    static final String HAIR_ID = "hair";

    public static class DataItemBitmap implements ItemBitmap {
        private static final long serialVersionUID = 1L;
        private int[][] bitmap = new int[SIZE][SIZE];
        private List<Color> colors = new ArrayList<>();

        public DataItemBitmap(int[][] bitmap, Color[] colors) {
            this.bitmap = bitmap;
            this.colors.add(Color.TRANSPARENT);
            this.colors.addAll(Arrays.asList(colors));
        }

        @Override
        public Color getColor(int x, int y) {
            return colors.get(bitmap[y][x]);
        }
    }

    private static DataItemBitmap getItemBitmap(String id, int ver) {
        switch (id) {
            case BACKGROUND_ID:
                return new BackgroundBitmap();
            case BODY_ID:
                return new BodyBitmap();
            case PANTS_ID:
                return new PantsBitmap();
            case SHIRT_ID:
                return new ShirtBitmap();
            case SHOES_ID:
                return new ShoesBitmap();
            case HAIR_ID:
                return new HairBitmap();
        }
        return null;
    }

    static ItemBitmap.Color getColor(String id, int ver, int x, int y) {
        return getItemBitmap(id, ver).getColor(x, y);
    }
}
