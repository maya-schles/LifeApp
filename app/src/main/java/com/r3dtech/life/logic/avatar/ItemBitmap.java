package com.r3dtech.life.logic.avatar;


import java.io.Serializable;

public interface ItemBitmap extends Serializable {
    int SIZE = 16;
    class Color implements Serializable{
        private static final long serialVersionUID = 1L;
        public static final Color TRANSPARENT = null;
        public static final Color BLACK = new Color(0x00, 0x00, 0x00);
        public static final Color RED = new Color(0xff, 0x00, 0x00);
        public static final Color NAVY = new Color(0x00, 0x00, 0x80);
        public static final Color BROWN = new Color(0xa5, 0x2a, 0x2a);

        public int r;
        public int g;
        public int b;

        public Color(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }
    Color getColor(int x, int y);
}
